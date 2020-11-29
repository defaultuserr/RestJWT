package com.Internetx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		SpringApplication.run(DemoApplication.class, args);
	}


}
