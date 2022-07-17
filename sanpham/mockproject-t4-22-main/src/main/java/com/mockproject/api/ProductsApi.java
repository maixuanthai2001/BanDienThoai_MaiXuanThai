package com.mockproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.entity.Products;
import com.mockproject.service.ProductsService;

@RestController
@RequestMapping("/api/products")
public class ProductsApi {
	
	@Autowired
	private ProductsService productService;
	
	// ENDPOINT: localhost:8080/api/products/
	@GetMapping("/")
	public ResponseEntity<?> doGetAll() {
		List<Products> products = productService.findAll();
		return ResponseEntity.ok(products);
	}
}
