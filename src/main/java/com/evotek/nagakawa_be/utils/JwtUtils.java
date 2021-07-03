package com.evotek.nagakawa_be.utils;



import com.evotek.nagakawa_be.service.UserDetailImpl;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger _log = LogManager.getLogger(JwtUtils.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value(("${app.jwtExpirationInMs}"))
    private int jwtExpirationInMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userDetail.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException e) {
            _log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            _log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println(e);
            _log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            _log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            _log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
