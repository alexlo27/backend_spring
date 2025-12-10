package com.alexlo.msvc_user.security.filters;

import com.alexlo.msvc_user.security.jwt.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");

        /*if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);

            if (jwtUtils.istokenValid(token)) {
                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }*/
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = tokenHeader.substring(7);

        try {
            if (jwtUtils.istokenValid(token)) {
                Claims claims = jwtUtils.extractAllClaims(token);
                String username = claims.getSubject();

                // Obtenemos los roles directamente del token
                List<String> roles = claims.get("roles", List.class);

                // Convertimos los roles a SimpleGrantedAuthority
                List<SimpleGrantedAuthority> authorities = roles == null ? List.of() :
                        roles.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());

                // Creamos el Authentication sin tocar la BD
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);

    }

}
