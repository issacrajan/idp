package com.issac.idp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@SpringBootApplication
public class IdpApplication {

	public static void main(String[] args) {
		BasicAuthenticationFilter f;
		SpringApplication.run(IdpApplication.class, args);

		/*
		 * http://localhost:8080/oauth2/authorize?response_type=code&client_id=client1&
		 * scope=openid&redirect_uri=http%3A%2F%2F%2F127.0.0.1%3A8080%2Fauth%2Fcallback
		 * 
		 * 
		 */
	}

}
