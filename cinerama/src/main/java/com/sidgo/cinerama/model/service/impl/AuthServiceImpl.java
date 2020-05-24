package com.sidgo.cinerama.model.service.impl;

import com.sidgo.cinerama.auth.JWTConstants;
import com.sidgo.cinerama.model.dto.AuthenticationRequestDTO;
import com.sidgo.cinerama.model.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * This method is used to perform a Custom Spring Security Authentication
     *
     * @param credentials Login Data
     * @return String returns JWT String in case of successfulAuthentication
     */
    @Override
    public String authenticate(AuthenticationRequestDTO credentials) {
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPwd(),
                            Collections.emptyList()
                    )
            );

            return this.successfulAuthentication(auth);
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            throw  ex;
        }
    }

    protected String successfulAuthentication(Authentication auth) {

        final String authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String token = Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setIssuer(JWTConstants.ISSUER)
                .setSubject(((User)auth.getPrincipal()).getUsername())
                .claim(JWTConstants.TOKEN_ROLE, authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1800000))
                .signWith(SignatureAlgorithm.HS512, JWTConstants.SECRET_KEY.getBytes()).compact();
        return JWTConstants.TOKEN_PREFIX + token;
    }
}
