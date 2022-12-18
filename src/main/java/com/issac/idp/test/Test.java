/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * @author issac
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
//		org.springframework.security.crypto.password.PasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
//
//		System.out.println(encoder.encode("secret1"));
//		System.out.println(UUID.randomUUID().toString());
//		// $2a$10$sqBNbgOZQINsSViKTpXK9uMatuylQUHeTrJnOpPWJXmip4bobLJ1W
//
//		encodeURL("http://127.0.0.1:9000/auth/callback");

		generateKey();
		// oDe0m7-GW77nHUMqbEquQpOrVXk8doimkeQLyQU3k84

	}

	private static void encodeURL(String url) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode(url, "UTF-8"));
	}

	private static void generateKey() {
		Key key = MacProvider.generateKey(SignatureAlgorithm.HS256);

		// Get the key data
		byte keyData[] = key.getEncoded();
		System.out.println(encode(keyData));
	}

	private static String encode(byte[] bytes) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}

	private static void testJWT() {
		String jws = Jwts.builder().setIssuer("Stormpath").setSubject("msilverman")
				.claim("name", "Micah Silverman").claim("scope", "admins")
				// Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
				.setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
				// Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
				.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
				.signWith(SignatureAlgorithm.HS256,
						TextCodec.BASE64.decode("Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E="))
				.compact();
	}
}
