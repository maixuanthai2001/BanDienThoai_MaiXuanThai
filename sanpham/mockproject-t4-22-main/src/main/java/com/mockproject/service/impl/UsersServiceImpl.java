package com.mockproject.service.impl;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Roles;
import com.mockproject.entity.Users;
import com.mockproject.repository.UsersRepo;
import com.mockproject.service.RolesService;
import com.mockproject.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo repo;
	
	@Autowired
	private RolesService roleService;

	@Override
	public Users doLogin(String username, String password) {
		Users user = repo.findByUsername(username);
		
		if (user != null) {
			boolean checkPassword = bcrypt.matches(password, user.getHashPassword());
			return checkPassword ? user : null;
		} else {
			return null;
		}
	}

	@Transactional
	@Override
	public Users save(Users user) throws SQLException {
		Roles role = roleService.findByDescription("user");
		user.setRole(role);
		user.setIsDeleted(Boolean.FALSE);
		String hashPassword = bcrypt.encode(user.getHashPassword());
		user.setHashPassword(hashPassword);
		return repo.saveAndFlush(user);
	}
}
