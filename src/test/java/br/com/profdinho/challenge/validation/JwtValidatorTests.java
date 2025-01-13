package br.com.profdinho.challenge.validation;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"jwt.secret=561491AE98362741F722202EED3288E8FF2508B35315ADBF75EEB3195A926B51"})
public class JwtValidatorTests {

    @Value("${jwt.secret}")
    private String secretKey;

    @InjectMocks
    private JwtValidator jwtValidator;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(jwtValidator, "secretKey", secretKey);
    }

    @Test
    public void testValidateJwtSuccess() {
        String jwt = Jwts.builder()
                .claim("Name", "TestUser")
                .claim("Role", "Admin")
                .claim("Seed", 7)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
                .compact();

        assertTrue(jwtValidator.validateJwt(jwt));
    }

    @Test
    public void testValidateJwtFailureInvalidToken() {
        String jwt = "invalid.jwt.token";

        assertFalse(jwtValidator.validateJwt(jwt));
    }

    @Test
    public void testValidateJwtFailureInvalidClaims() {
        String jwt = Jwts.builder()
                .claim("Name", "TestUser123")
                .claim("Role", "InvalidRole")
                .claim("Seed", 4)
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)), SignatureAlgorithm.HS256)
                .compact();

        assertFalse(jwtValidator.validateJwt(jwt));
    }

    @Test
    public void testCreateJWT() {
        String name = "TestUser";
        String role = "Admin";
        int seed = 7;

        String jwt = jwtValidator.createJWT(role, seed, name);
        Claims claims = Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))).build().parseClaimsJws(jwt).getBody();

        assertEquals(name, claims.get("Name"));
        assertEquals(role, claims.get("Role"));
        assertEquals(seed, claims.get("Seed"));
    }

}
