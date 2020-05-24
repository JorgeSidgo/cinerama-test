package com.sidgo.cinerama.auth;

import org.springframework.stereotype.Component;

@Component
public class JWTConstants {

    public static final String SECRET_KEY = "112358";
    public static final String ISSUER = "Cinerama";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_ROLE = "role";

}
