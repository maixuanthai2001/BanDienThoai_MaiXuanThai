package com.mockproject.service;

import java.sql.SQLException;

import com.mockproject.entity.Users;

public interface UsersService {

	Users doLogin(String username, String password);
	Users save(Users user) throws SQLException;
}
