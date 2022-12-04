/**
 * @Copyright issac.rajan@gmail.com
 * 
 */
package com.issac.idp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author issac
 *
 */
@Entity
@Table(name = "oauth2_authorization_consent")
public class Oauth2AuthorizationConsent {

	@Id
	private String registeredClientId;
	private String principalName;
	private String authorities;

	public String getRegisteredClientId() {
		return registeredClientId;
	}

	public void setRegisteredClientId(String registeredClientId) {
		this.registeredClientId = registeredClientId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "Oauth2AuthorizationConsent [registeredClientId=" + registeredClientId + ", principalName="
				+ principalName + ", authorities=" + authorities + "]";
	}

}
