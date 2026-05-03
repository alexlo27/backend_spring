package com.alexlo.msvc_user.security.filters;

import com.alexlo.msvc_user.security.jwt.JwtUtils;
import com.alexlo.msvc_user.security.JwtAuthEntryPoint;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = tokenHeader.substring(7);

        try {
            Claims claims = jwtUtils.parseAndValidateClaims(token);
            String username = claims.getSubject();
            if (username == null || username.isBlank()) {
                SecurityContextHolder.clearContext();
                jwtAuthEntryPoint.commence(request, response, new BadCredentialsException("Token sin subject"));
                return;
            }

            List<SimpleGrantedAuthority> authorities = extractAuthoritiesFromClaims(claims);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Alternativa (mas segura, menos stateless): revalidar permisos/estado en BD.
            // - Requiere inyectar UserDetailsService y usar userDetails.getAuthorities().
            // - Ventaja: revocacion inmediata ante cambios.
            // - Desventaja: hit a BD (o cache) por request.
            //
            // UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // UsernamePasswordAuthenticationToken authentication =
            //         new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            jwtAuthEntryPoint.commence(request, response, e);
        } catch (ExpiredJwtException e) {
            SecurityContextHolder.clearContext();
            jwtAuthEntryPoint.commence(request, response, new CredentialsExpiredException("Token expirado", e));
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            jwtAuthEntryPoint.commence(request, response, new BadCredentialsException("Token invalido"));
        }

    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaims(Claims claims) {
        Object permissionsClaim = claims.get("permissions");
        if (permissionsClaim == null) return List.of();
        if (!(permissionsClaim instanceof List<?> list)) {
            throw new BadCredentialsException("Claim permissions invalido");
        }
        return list.stream()
                .map(String::valueOf)
                .filter(p -> p != null && !p.isBlank())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
