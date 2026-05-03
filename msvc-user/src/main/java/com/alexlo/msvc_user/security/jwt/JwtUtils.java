package com.alexlo.msvc_user.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Pattern;

@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.time.expiration}")
    private String timeExpiration;

    @Value("${jwt.issuer:msvc-user}")
    private String issuer;

    @Value("${jwt.audience:msvc-user-api}")
    private String audience;

    private static final Pattern HEX_PATTERN = Pattern.compile("^[0-9a-fA-F]+$");

    public String generateAccessToken(User user){
        if (!user.isEnabled()) throw new RuntimeException("El usuario está inactivo");

        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        long now = System.currentTimeMillis();
        long expMillis = Long.parseLong(timeExpiration);

        return Jwts.builder()
                .subject(user.getUsername())
                .issuer(issuer)
                .audience().add(audience).and()
                .claim("permissions", roles)
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date(now))
                .expiration(new Date(now + expMillis))
                .signWith(getSignatureKey())
                .compact();
    }

    // Backward-compatible name (typo)
    public String generateAccesToken(User user) {
        return generateAccessToken(user);
    }

    public boolean isTokenValid(String token){
        try {
            parseAndValidateClaims(token);
            return true;
        }catch (Exception e){
            log.error("Token invalido, error:".concat(e.getMessage()));
            return false;
        }
    }

    public Claims parseAndValidateClaims(String token) {
        try {
            Claims claims = extractAllClaims(token);
            validateRegisteredClaims(claims);
            return claims;
        } catch (JwtException e) {
            throw e;
        } catch (Exception e) {
            throw new JwtException("Token invalido", e);
        }
    }

    // Backward-compatible name (typo)
    public boolean istokenValid(String token) {
        return isTokenValid(token);
    }

    // obtener el username del token
    public String getUsernameFromToken(String token){
        return getClaim(token, Claims::getSubject);
    }

    // obtener un solo claim
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    // obtener los claims del token
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //obtener firma del token
    public Key getSignatureKey(){
        byte[] keyBytes = decodeSecretKey(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private byte[] decodeSecretKey(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalStateException("jwt.secret.key no esta configurado");
        }
        String value = raw.trim();

        // Accept HEX (common in local configs) or BASE64 (recommended)
        if (HEX_PATTERN.matcher(value).matches() && (value.length() % 2 == 0)) {
            return java.util.HexFormat.of().parseHex(value);
        }
        return Decoders.BASE64.decode(value);
    }

    private void validateRegisteredClaims(Claims claims) {
        if (claims == null) throw new IllegalArgumentException("Claims vacios");

        if (issuer != null && !issuer.isBlank()) {
            String tokenIssuer = claims.getIssuer();
            if (tokenIssuer == null || !issuer.equals(tokenIssuer)) {
                throw new IllegalArgumentException("Issuer invalido");
            }
        }

        if (audience != null && !audience.isBlank()) {
            java.util.Set<String> aud = claims.getAudience();
            if (aud == null || aud.isEmpty() || !aud.contains(audience)) {
                throw new IllegalArgumentException("Audience invalida");
            }
        }
    }
}
