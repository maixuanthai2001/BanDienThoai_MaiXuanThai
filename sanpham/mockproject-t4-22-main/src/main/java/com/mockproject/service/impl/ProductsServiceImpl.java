package com.mockproject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Products;
import com.mockproject.repository.ProductsRepo;
import com.mockproject.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	private ProductsRepo repo;

	@Override
	public List<Products> findAll() {
		return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0);
	}

	@Override
	public Products findBySlug(String slug) {
		return repo.findBySlug(slug);
	}

	@Override
	public Products findById(Long id) {
		Optional<Products> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
}
