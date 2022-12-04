/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author issac
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		org.springframework.security.crypto.password.PasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

		System.out.println(encoder.encode("password"));
		System.out.println(UUID.randomUUID().toString());
		// $2a$10$sqBNbgOZQINsSViKTpXK9uMatuylQUHeTrJnOpPWJXmip4bobLJ1W

		encodeURL("http://127.0.0.1:9000/auth/callback");
	}

	private static void encodeURL(String url) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode(url, "UTF-8"));
	}
}
