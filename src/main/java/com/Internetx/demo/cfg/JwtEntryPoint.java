package com.Internetx.demo.cfg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {
    //used in case of an exception
    // if someone is unauthorized and the token is not accepted
    //commence is called when exception is thrown
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        final Exception exception = (Exception) httpServletRequest.getAttribute("exception");

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String message;

        if (exception != null) {

            if (exception.getCause() != null) {
                message = exception.getCause().toString() + " " + exception.getMessage();
            } else {
                message = exception.getMessage();
            }
            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
            httpServletResponse.getOutputStream().write(body);

        } else {

            if (e.getCause() != null) {
                message = e.getCause().toString() + " " + e.getMessage();
            } else {
                message = e.getMessage();
            }
            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
            httpServletResponse.getOutputStream().write(body);

        }

    }
}
