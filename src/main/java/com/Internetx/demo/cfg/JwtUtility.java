package com.Internetx.demo.cfg;

import com.Internetx.demo.Repository.JDBCHandling;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.lang.model.util.SimpleElementVisitor6;
import java.security.Signature;
import java.util.*;

@Service
public class JwtUtility {


    private String secret;
    private int jwtExpirationinMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${jwt.expirationDate}")
    public void setJwtExpirationinMs(int jwtExpirationinMs) {
        this.jwtExpirationinMs = jwtExpirationinMs;
    }

    private String dogenerateToke(Map<String, Object> claims, String name) {
        System.out.println(jwtExpirationinMs);
        System.out.println(secret);


        return Jwts.builder().
                setClaims(claims).
                setSubject(name).
                setIssuedAt(new Date(System.currentTimeMillis()))
                //Signature Algorithm needs a long secret..
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationinMs)).signWith(SignatureAlgorithm.HS256, secret).
                        compact();


    }

    //hier machen wri das token, user und token sind nicht in map
    public String generateToke(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //bekomme ich die rolen
        System.out.println(userDetails.getAuthorities());
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();

        for (JDBCHandling.Roles role : JDBCHandling.Roles.values()) {
            String temp = role.toString();
            if(roles.contains(new SimpleGrantedAuthority(temp))){
                claims.put(temp, true);
            }

            }

        /*if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            claims.put("isAdmin", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            claims.put("isUser", true);
        }*/
        return dogenerateToke(claims, userDetails.getUsername());


    }
    public boolean validateJWTToken(String toky){
        try{


            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(toky);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw new BadCredentialsException("Token expired");
        }

    }
    public  String getUserNameFromToken(String token){
        //deprecated
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    public List<SimpleGrantedAuthority> getRolesFromToke(String token){

        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
       // Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        //Boolean isUser = claims.get("isUser", Boolean.class);
        for (JDBCHandling.Roles role : JDBCHandling.Roles.values()) {
            String temp = role.toString();
           if(claims.get(role.toString(), Boolean.class) != null){
               roles.add(new SimpleGrantedAuthority(role.toString()));


           }


        }



/*
        if( isAdmin != null && isAdmin){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if( isUser != null && isUser == true){
            roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }*/
        return roles;
    }

}
