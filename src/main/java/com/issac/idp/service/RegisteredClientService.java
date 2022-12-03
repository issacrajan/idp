/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issac.idp.dto.ClientSettingDTO;
import com.issac.idp.dto.TokenSettingDTO;
import com.issac.idp.model.RegisteredClientEntity;
import com.issac.idp.repo.RegisterClientRepo;

/**
 * @author issac
 *
 */
public class RegisteredClientService implements RegisteredClientRepository {

	private RegisterClientRepo registerClientRepo;
	private ObjectMapper objectMapper;

	public RegisteredClientService(RegisterClientRepo registerClientRepo, ObjectMapper objectMapper) {
		this.registerClientRepo = registerClientRepo;
		this.objectMapper = objectMapper;
	}

	@Override
	public void save(RegisteredClient c) {
		RegisteredClientEntity client = new RegisteredClientEntity();

		client.setClientId(c.getClientId());
		client.setClientName(c.getClientName());
		client.setClientSecret(c.getClientSecret());
		client.setClientIdIssuedAt(c.getClientIdIssuedAt());
		client.setClientSecretExpiresAt(c.getClientSecretExpiresAt());

		client.setClientAuthenticationMethods(
				StringUtils.collectionToCommaDelimitedString(c.getClientAuthenticationMethods()));
		client.setAuthorizationGrantTypes(
				StringUtils.collectionToCommaDelimitedString(c.getAuthorizationGrantTypes()));
		client.setRedirect_uris(StringUtils.collectionToCommaDelimitedString(c.getRedirectUris()));
		client.setScopes(StringUtils.collectionToCommaDelimitedString(c.getScopes()));
		client.setClientSettings(writeMap(c.getClientSettings().getSettings()));
		client.setTokenSettings(writeMap(c.getTokenSettings().getSettings()));

		registerClientRepo.save(client);
	}

	@Override
	public RegisteredClient findById(String id) {
		Assert.hasText(id, " ID cannot be empty");
		Optional<RegisteredClientEntity> clientEntity = registerClientRepo.findById(id);
		if (clientEntity.isEmpty()) {
			throw new RuntimeException("client with ID " + id + " not found");
		}
		return buildClientFromEntity(clientEntity.get());
	}

	@Override
	public RegisteredClient findByClientId(String clientId) {
		Assert.hasText(clientId, "client ID cannot be empty");
		RegisteredClientEntity clientEntity = registerClientRepo.findByClientId(clientId);
		if (clientEntity == null) {
			throw new RuntimeException("client with Client ID " + clientId + " not found");
		}
		return buildClientFromEntity(clientEntity);
	}

	private RegisteredClient buildClientFromEntity(RegisteredClientEntity e) {
		Set<String> clientAuthenticationMethods = StringUtils
				.commaDelimitedListToSet(e.getClientAuthenticationMethods());
		Set<String> authorizationGrantTypes = StringUtils
				.commaDelimitedListToSet(e.getAuthorizationGrantTypes());
		Set<String> redirectUris = StringUtils.commaDelimitedListToSet(e.getRedirect_uris());
		Set<String> clientScopes = StringUtils.commaDelimitedListToSet(e.getScopes());

		// @formatter:off
		RegisteredClient.Builder builder = RegisteredClient.withId(e.getId())
				.clientId(e.getClientId())
				.clientName(e.getClientName())
				.clientSecret(e.getClientSecret())
				.clientIdIssuedAt(e.getClientIdIssuedAt())
				.clientSecretExpiresAt(e.getClientSecretExpiresAt())
				.clientAuthenticationMethods( (authMethods) -> 
					clientAuthenticationMethods.forEach(authMethod -> 
					authMethods.add(resolveClientAuthMethod(authMethod))) )
				.authorizationGrantTypes( (grantTypes) -> 
					authorizationGrantTypes.forEach(t -> grantTypes.add(resolveGrantType(t))))
				.redirectUris( (URIs) ->URIs.addAll(redirectUris))
				.scopes( (scopes) -> scopes.addAll(clientScopes));
		// @formatter:on

		Map<String, Object> clientSettings = readAsClientSettingMap(e.getClientSettings());
		if (clientSettings != null) {
			builder.clientSettings(ClientSettings.withSettings(clientSettings).build());
		}

		Map<String, Object> tokenSettings = readAsTokenSettingMap(e.getTokenSettings());
		if (tokenSettings != null) {
			builder.tokenSettings(TokenSettings.withSettings(tokenSettings).build());
		}

		return builder.build();
	}

	private String writeMap(Map<String, Object> data) {
		if (data == null) {
			return "";
		}
		try {
			return this.objectMapper.writeValueAsString(data);
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	private ClientAuthenticationMethod resolveClientAuthMethod(String m) {
		if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equals(m)) {
			return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
		} else if (ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equals(m)) {
			return ClientAuthenticationMethod.CLIENT_SECRET_POST;
		} else if (ClientAuthenticationMethod.CLIENT_SECRET_JWT.getValue().equals(m)) {
			return ClientAuthenticationMethod.CLIENT_SECRET_JWT;
		} else if (ClientAuthenticationMethod.PRIVATE_KEY_JWT.getValue().equals(m)) {
			return ClientAuthenticationMethod.PRIVATE_KEY_JWT;
		}

		return new ClientAuthenticationMethod(m);
	}

	private AuthorizationGrantType resolveGrantType(String grantType) {
		if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(grantType)) {
			return AuthorizationGrantType.AUTHORIZATION_CODE;
		} else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equals(grantType)) {
			return AuthorizationGrantType.CLIENT_CREDENTIALS;
		} else if (AuthorizationGrantType.JWT_BEARER.getValue().equals(grantType)) {
			return AuthorizationGrantType.JWT_BEARER;
		} else if (AuthorizationGrantType.REFRESH_TOKEN.getValue().equals(grantType)) {
			return AuthorizationGrantType.REFRESH_TOKEN;
		}

		return new AuthorizationGrantType(grantType);
	}

	private Map<String, Object> readAsClientSettingMap(String data) {
		if (!StringUtils.hasText(data)) {
			return null;
		}

		try {
			ClientSettingDTO clientSettings = objectMapper.readValue(data, ClientSettingDTO.class);
			return clientSettings.getAsMap();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	private Map<String, Object> readAsTokenSettingMap(String data) {
		if (!StringUtils.hasText(data)) {
			return null;
		}

		try {
			TokenSettingDTO tokensettings = objectMapper.readValue(data, TokenSettingDTO.class);
			return tokensettings.getAsMap();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}
}
