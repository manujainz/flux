package com.flux.streaming.user;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtUserExtractor {

    private static final String CLAIM_SUB = "sub";
    private static final String CLAIM_EMAIL = "email";

    public String extractAuth0Id(Jwt jwt) {
        if (jwt == null) {
            return null;
        }
        return jwt.getClaimAsString(CLAIM_SUB);
    }

    public String extractEmail(Jwt jwt) {
        if (jwt == null) {
            return null;
        }
        return jwt.getClaimAsString(CLAIM_EMAIL);
    }
}

