package com.crm.operis_app.security.jwt;

import com.crm.operis_app.services.authUser.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${operis.app.jwtSecret}")
    private String jwtSecret;

    @Value("${operis.app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        System.out.println(new Date(System.currentTimeMillis()));
        System.out.println(new Date(System.currentTimeMillis() + jwtExpiration));
        return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

    }

    public String generateJwtWithoutPassword(String username) {

        return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Signature JWT non valide  ", e);
        } catch (MalformedJwtException e) {
            logger.error("JWT token non valide ", e);
        } catch (ExpiredJwtException e) {
            logger.error(" JWT token est expiré ", e);

        } catch (UnsupportedJwtException e) {
            logger.error(" JWT token non supporté ", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string est vide ", e);
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }


}