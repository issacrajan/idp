package com.issac.idp.dto;

import java.time.LocalDateTime;

import com.issac.idp.model.UserInfo;
import com.issac.idp.util.Util;

public class UserInfoDTO {
	private String id;

	private String name;
	private String lastname;
	private String email;
	private String password;
	private String location;
	private LocalDateTime createdTs;
	private LocalDateTime updatedTs;
	private String createdBy;
	private String updatedBy;
	
	public  UserInfo buildNewEntity() {
		UserInfo e = new UserInfo();
		
		if (Util.hasContent(id)) {
			e.setId(id);
		}
		e.setName(name);
		e.setLastname(lastname);
		e.setEmail(email);
		e.setLocation(location);
		
		return e;
	}
	
	public  UserInfo updateEntity(UserInfo e ) {
		e.setName(name);
		e.setLastname(lastname);
		e.setEmail(email);
		e.setLocation(location);
		e.setCreatedBy(createdBy);
		e.setCreatedTs(createdTs);
		
		return e;
	}
	public static UserInfoDTO buildFromEntity(UserInfo u) {
		UserInfoDTO dto = new UserInfoDTO();
		dto.setId(u.getId());
		dto.setName(u.getName());
		dto.setLastname(u.getLastname());
		dto.setEmail(u.getEmail());
		dto.setLocation(u.getLocation());
		dto.setUpdatedBy(u.getUpdatedBy());
		dto.setUpdatedTs(u.getUpdatedTs());
		dto.setCreatedBy(u.getCreatedBy());
		dto.setCreatedTs(u.getCreatedTs());
		
		return dto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public LocalDateTime getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}
	public LocalDateTime getUpdatedTs() {
		return updatedTs;
	}
	public void setUpdatedTs(LocalDateTime updatedTs) {
		this.updatedTs = updatedTs;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email
				+ ", password=" + password + ", location=" + location + ", createdTs=" + createdTs + ", updatedTs="
				+ updatedTs + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}
	
	
}
