package com.issac.idp.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.issac.idp.dto.UserInfoDTO;
import com.issac.idp.exception.RecordNotFoundException;
import com.issac.idp.model.UserInfo;
import com.issac.idp.repo.UserRepo;
import com.issac.idp.util.JwtUtil;
import com.issac.idp.util.Util;

/**
 * 
 * @author issac
 *
 */
@Service
public class UserService {

	private UserRepo userRepo;
	private PasswordEncoder passwordEncoder;
	private JwtUtil jwtUtil;

	public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public String validateLoginAndCreateJwt(String email, String password) {
		UserInfo userInfo = userRepo.findByEmail(email);
		if (userInfo == null) {
			throw new RecordNotFoundException("invalid email/password");
		}
		String userDBPwd = userInfo.getPassword();
		if (!passwordEncoder.matches(password, userDBPwd)) {
			throw new RecordNotFoundException("invalid email/password");
		}
		return jwtUtil.createJWT(email);
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
			userInfo.setPassword(passwordEncoder.encode(dto.getPassword()));
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
