package com.mockproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Roles;
import com.mockproject.repository.RolesRepo;
import com.mockproject.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService {
	
	@Autowired
	private RolesRepo repo;

	@Override
	public Roles findByDescription(String description) {
		return repo.findByDescription(description);
	}
}
