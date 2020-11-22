package com.Internetx.demo.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class CustomJwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtility jwtUtility;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        String jwtToken = extractJwtFromRequest(httpServletRequest);
        if(StringUtils.hasText(jwtToken) && jwtUtility.validateJWTToken(jwtToken)){
            //daten aus dem Token hoeln und create enw user
            UserDetails userDetails = new User(jwtUtility.getUserNameFromToken(jwtToken), "", jwtUtility.getRolesFromToke(jwtToken));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", jwtUtility.getRolesFromToke(jwtToken));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }else {
            System.out.println("Cant no set the sec context");

        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
    private String extractJwtFromRequest(HttpServletRequest request){
        String bear = request.getHeader("Authorization");
        if(StringUtils.hasText(bear) && bear.startsWith("Bearer ")){
            //get the token of the response
            System.out.println(bear.substring(7));
            return bear.substring(7, bear.length());

        }
        return null;
    }
}
