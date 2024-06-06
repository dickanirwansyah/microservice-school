package com.school.app.backoffice_service.helper;

import com.school.app.backoffice_service.dto.response.AccountsResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class SecurityHelper {

    @Value("${jwt.expirationInMs}")
    private Integer jwtExpirationTime;

    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(AccountsResponse response){
        String token = Jwts.builder()
                .setSubject(response.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationTime))
                .signWith(getSignInKey())
                .compact();
        return token;
    }

    public String getAccountUsernameFromToken(String token){
        String userInformation = Jwts.parser().setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userInformation;
    }

    public boolean validateJwtToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecretKey)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException e){
            log.error("SignatureException error : [{}]",e.getMessage());
        }catch (MalformedJwtException e){
            log.error("MalformedJwtException error : [{}]",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("ExpiredJwtException error : [{}]",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("UnsupportedJwtException error : [{}]",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("IllegalArgumentException error : [{}]",e.getMessage());
        }
        return false;
    }
}
