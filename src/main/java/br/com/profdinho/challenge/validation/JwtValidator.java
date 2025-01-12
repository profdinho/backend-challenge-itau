package br.com.profdinho.challenge.validation;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtValidator {
    @Value("${jwt.secret}")
    private String secretKey;

    public boolean validateJwt(String jwt) {
        try {
            //Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(jwt);
            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();

            if (claims.size() != 3) {
                return false;
            }

            String name = claims.get("Name", String.class);
            String role = claims.get("Role", String.class);
            Integer seed = claims.get("Seed", Integer.class);

            if (name == null || !name.matches("^[^\\d]+$")) {
                return false;
            }

            if (name.length() > 256) {
                return false;
            }

            List<String> validRoles = Arrays.asList("Admin", "Member", "External");
            if (!validRoles.contains(role)) {
                return false;
            }

            if (seed == null || !isPrime(seed)) {
                return false;
            }
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String createJWT(String role, Integer seed, String name) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Jwts
                .builder()
                .claim("Role", role)
                .claim("Seed", seed)
                .claim("Name", name)
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}