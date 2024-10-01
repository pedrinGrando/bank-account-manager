package com.pedro.accountsservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtUtil {

    private final String secret  = "e758f7dc66f79900a72797e5fa2d34c2599676f7";

    public String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("BankTransactionSystem")
                    .withSubject(username)
                    .withExpiresAt(this.getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Failed to generate token", exception);
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
    }

    public String extractUsernameFromToken(String token) {
        return JWT.decode(token).getClaim("sub").asString();
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
             JWT.require(algorithm)
                    .withIssuer("BankTransactionSystem")
                    .build()
                    .verify(token)
                    .getSubject();
             return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }
}
