package com.issac.idp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.issac.idp.model.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, String> {

	UserInfo findByEmail(String email);
}
