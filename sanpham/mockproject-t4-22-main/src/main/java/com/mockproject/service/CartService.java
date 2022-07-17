package com.mockproject.service;

import com.mockproject.dto.CartDto;

public interface CartService {
	
	CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace);
}
