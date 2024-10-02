package com.pedro.accountsservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pedro.accountsservice.dto.UserDTO;
import com.pedro.accountsservice.model.User;
import com.pedro.accountsservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secret  = "e758f7dc66f79900a72797e5fa2d34c2599676f7";
    private final UserRepository userRepository;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> authenticate(String username){
        Map<String, Object> response = new HashMap<>();
        String token = generateToken(username);
        User authenticatedUser = userRepository.findByUsername(username).get();
        UserDTO userDto = new UserDTO(authenticatedUser);
        response.put("usuario", userDto);
        response.put("expire", this.getExpirationDate());
        response.put("username", this.extractUsernameFromToken(token));
        response.put("token", token);
        return response;
    }

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
