/**
 * @Copyright issac.rajan@gmail.com
 */
package com.issac.idp.dto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.server.authorization.settings.ConfigurationSettingNames;

/**
 * @author issac
 *
 */
public class ClientSettingDTO {
	private boolean requireProofKey;
	private boolean requireAuthorizationConsent;

	public boolean isRequireProofKey() {
		return requireProofKey;
	}

	public void setRequireProofKey(boolean requireProofKey) {
		this.requireProofKey = requireProofKey;
	}

	public boolean isRequireAuthorizationConsent() {
		return requireAuthorizationConsent;
	}

	public void setRequireAuthorizationConsent(boolean requireAuthorizationConsent) {
		this.requireAuthorizationConsent = requireAuthorizationConsent;
	}

	public Map<String, Object> getAsMap() {
		Map<String, Object> settings = new HashMap<>();
		settings.put(ConfigurationSettingNames.Client.REQUIRE_PROOF_KEY, requireProofKey);
		settings.put(ConfigurationSettingNames.Client.REQUIRE_AUTHORIZATION_CONSENT,
				requireAuthorizationConsent);

		return settings;
	}

	@Override
	public String toString() {
		return "ClientSettingDTO [requireProofKey=" + requireProofKey + ", requireAuthorizationConsent="
				+ requireAuthorizationConsent + "]";
	}
	
	
}
