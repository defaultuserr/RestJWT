package com.Internetx.demo.controller;


import com.Internetx.demo.cfg.GetUserDetailsClass;
import com.Internetx.demo.cfg.JwtUtility;
import com.Internetx.demo.model.AuthRequest;
import com.Internetx.demo.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private GetUserDetailsClass getUserDetailsClass;
    @Autowired
    private JwtUtility jwtUtility;


@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        //wirft exception wenn etwas nicht stimm oder chain unterbrochen wurde
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (DisabledException error){
            throw new Exception("USER_DISABLED" , error);

        }catch (BadCredentialsException error){
            throw new Exception("BAD CREDENTIALS", error);
        }
        final UserDetails userdetails = getUserDetailsClass.loadUserByUsername(authRequest.getUsername());
        final String token = jwtUtility.generateToke(userdetails);
        return ResponseEntity.ok(new AuthResponse(token));

    }


}
