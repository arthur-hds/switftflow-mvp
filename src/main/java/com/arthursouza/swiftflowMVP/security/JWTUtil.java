package com.arthursouza.swiftflowMVP.security;

import java.util.Date;
import java.util.Objects;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTUtil {
    



    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;



    //Creates a key, which is needed to create the token
    private SecretKey getKeyBySecret(){

        SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());

        return key;
    }


    //a token is built, along with its data being passed on
    public String generateToken(String username){

        SecretKey key = getKeyBySecret();

        return Jwts.builder()
            .setSubject(username)
            .setExpiration(new Date(System.currentTimeMillis() + this.expiration))
            .signWith(key)
            .compact();
    }



    //Gets information of the created token

    //Method used to retrieve information from a token, via key and token. 
	//This information is used to be able to identify and validate the data in the future
    private Claims getClaims(String token){
        
        SecretKey key = getKeyBySecret();

        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }


    }




    //Use information of the token to validate it

    //Method used to validate the token's main data 
    //(User name, whether the expiration date has passed, etc.) 
    public boolean isValidToken(String token){
        Claims claims = getClaims(token);

        if (Objects.nonNull(claims)) {
            
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if (Objects.nonNull(username) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
                
                return true;

            }


        }

        return false;
    }



    public String getUsername(String token){
        
        Claims claims = getClaims(token);
        if (Objects.nonNull(claims)) {
            return claims.getSubject();
        }
        return null;
    }



}
