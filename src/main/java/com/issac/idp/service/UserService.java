package com.issac.idp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.issac.idp.dto.UserInfoDTO;
import com.issac.idp.exception.RecordNotFoundException;
import com.issac.idp.model.UserInfo;
import com.issac.idp.repo.UserRepo;
import com.issac.idp.util.Util;

@Service
public class UserService {

	private UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	public UserInfoDTO getUser(String id) {
		Optional<UserInfo> userInfo = userRepo.findById(id);
		if (userInfo.isPresent()) {
			return UserInfoDTO.buildFromEntity(userInfo.get());
		}
		throw new RecordNotFoundException("user not found " + id);
	}

	public UserInfoDTO save(UserInfoDTO dto) {
		Optional<UserInfo> userInfoRec = null;
		UserInfo userInfo;
		if (Util.isEmpty(dto.getId())) {
			userInfo = dto.buildNewEntity();
		} else {
			// update mode
			userInfoRec = userRepo.findById(dto.getId());
			if (userInfoRec.isEmpty()) {
				throw new RecordNotFoundException("user not found " + dto.getId());
			}
			userInfo = userInfoRec.get();
			dto.updateEntity(userInfo);
		}

		userRepo.save(userInfo);

		return UserInfoDTO.buildFromEntity(userInfo);
	}

}
