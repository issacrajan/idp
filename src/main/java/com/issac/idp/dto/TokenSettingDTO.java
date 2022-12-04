/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.dto;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.server.authorization.settings.ConfigurationSettingNames;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.issac.idp.ser.CustomDurationDeserializer;
import com.issac.idp.ser.CustomDurationSerializer;

/**
 * @author issac
 *
 */
public class TokenSettingDTO {

	@JsonSerialize(using = CustomDurationSerializer.class)
	@JsonDeserialize(using = CustomDurationDeserializer.class)
	private Duration authorizationCodeTimeToLive;

	@JsonSerialize(using = CustomDurationSerializer.class)
	@JsonDeserialize(using = CustomDurationDeserializer.class)
	private Duration accessTokenTimeToLive;

	private boolean reuseRefreshTokens;

	@JsonSerialize(using = CustomDurationSerializer.class)
	@JsonDeserialize(using = CustomDurationDeserializer.class)
	private Duration refreshTokenTimeToLive;

	private String idTokenSignatureAlgorithm;
	private OAuth2TokenFormat accessTokenFormat;

	public Duration getAccessTokenTimeToLive() {
		return accessTokenTimeToLive;
	}

	public void setAccessTokenTimeToLive(Duration accessTokenTimeToLive) {
		this.accessTokenTimeToLive = accessTokenTimeToLive;
	}

	public boolean isReuseRefreshTokens() {
		return reuseRefreshTokens;
	}

	public void setReuseRefreshTokens(boolean reuseRefreshTokens) {
		this.reuseRefreshTokens = reuseRefreshTokens;
	}

	public Duration getRefreshTokenTimeToLive() {
		return refreshTokenTimeToLive;
	}

	public void setRefreshTokenTimeToLive(Duration refreshTokenTimeToLive) {
		this.refreshTokenTimeToLive = refreshTokenTimeToLive;
	}

	public String getIdTokenSignatureAlgorithm() {
		return idTokenSignatureAlgorithm;
	}

	public void setIdTokenSignatureAlgorithm(String idTokenSignatureAlgorithm) {
		this.idTokenSignatureAlgorithm = idTokenSignatureAlgorithm;
	}

	public OAuth2TokenFormat getAccessTokenFormat() {
		return accessTokenFormat;
	}

	public void setAccessTokenFormat(OAuth2TokenFormat accessTokenFormat) {
		this.accessTokenFormat = accessTokenFormat;
	}

	public Duration getAuthorizationCodeTimeToLive() {
		return authorizationCodeTimeToLive;
	}

	public void setAuthorizationCodeTimeToLive(Duration authorizationCodeTimeToLive) {
		this.authorizationCodeTimeToLive = authorizationCodeTimeToLive;
	}

	public Map<String, Object> getAsMap() {
		Map<String, Object> settings = new HashMap<>();
		settings.put(ConfigurationSettingNames.Token.AUTHORIZATION_CODE_TIME_TO_LIVE,
				authorizationCodeTimeToLive);
		settings.put(ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE, accessTokenTimeToLive);
		settings.put(ConfigurationSettingNames.Token.REUSE_REFRESH_TOKENS, reuseRefreshTokens);
		settings.put(ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE, refreshTokenTimeToLive);
		settings.put(ConfigurationSettingNames.Token.ID_TOKEN_SIGNATURE_ALGORITHM,
				SignatureAlgorithm.from(idTokenSignatureAlgorithm));
		settings.put(ConfigurationSettingNames.Token.ACCESS_TOKEN_FORMAT, accessTokenFormat);

		return settings;
	}

	@Override
	public String toString() {
		return "TokenSettingDTO [accessTokenTimeToLive=" + accessTokenTimeToLive + ", reuseRefreshTokens="
				+ reuseRefreshTokens + ", refreshTokenTimeToLive=" + refreshTokenTimeToLive
				+ ", idTokenSignatureAlgorithm=" + idTokenSignatureAlgorithm + ", accessTokenFormat="
				+ accessTokenFormat + "]";
	}

}
