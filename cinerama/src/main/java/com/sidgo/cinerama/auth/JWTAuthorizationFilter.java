package com.sidgo.cinerama.auth;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        try {
            String header = req.getHeader(JWTConstants.AUTH_HEADER);
            if (header == null || !header.startsWith(JWTConstants.TOKEN_PREFIX)) {
                chain.doFilter(req, res);
                return;
            }
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (SignatureException | ExpiredJwtException | UnsupportedJwtException | MalformedJwtException ex) {
            chain.doFilter(req, res);
        }


    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

        try {
            String token = request.getHeader("Authorization");
            if (token != null) {

                Claims claims = Jwts
                        .parser()
                        .setSigningKey(JWTConstants.SECRET_KEY.getBytes())
                        .parseClaimsJws(token.replace(JWTConstants.TOKEN_PREFIX, ""))
                        .getBody();


                String user = claims.getSubject();
                Collection<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(JWTConstants.TOKEN_ROLE).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            authorities);
                }
                return null;
            }
            return null;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
