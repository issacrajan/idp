package com.issac.idp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.issac.idp.model.RegisteredClientEntity;

@Repository
public interface RegisterClientRepo extends JpaRepository<RegisteredClientEntity, String>{

	RegisteredClientEntity findByClientId(String clientId);
}
