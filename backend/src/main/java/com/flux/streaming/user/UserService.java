package com.flux.streaming.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUserExtractor jwtUserExtractor;

    public UserService(UserRepository userRepository, JwtUserExtractor jwtUserExtractor) {
        this.userRepository = userRepository;
        this.jwtUserExtractor = jwtUserExtractor;
    }

    @Transactional
    public User syncUser(Jwt jwt) {
        String auth0Id = jwtUserExtractor.extractAuth0Id(jwt);
        if (auth0Id == null || auth0Id.isBlank()) {
            throw new IllegalArgumentException("JWT does not contain a valid 'sub' claim");
        }

        return userRepository.findByAuth0Id(auth0Id)
                .orElseGet(() -> createUser(auth0Id, jwtUserExtractor.extractEmail(jwt)));
    }

    private User createUser(String auth0Id, String email) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setAuth0Id(auth0Id);
        user.setEmail(email);
        user.setCreatedAt(Instant.now());
        return userRepository.save(user);
    }
}

