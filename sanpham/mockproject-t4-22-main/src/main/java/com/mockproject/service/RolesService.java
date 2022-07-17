package com.mockproject.service;

import com.mockproject.entity.Roles;

public interface RolesService {

	Roles findByDescription(String description);
}
