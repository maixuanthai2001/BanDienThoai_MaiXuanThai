package com.mockproject.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockproject.dto.CartDetailDto;
import com.mockproject.dto.CartDto;
import com.mockproject.entity.Products;
import com.mockproject.service.CartService;
import com.mockproject.service.ProductsService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private ProductsService productService;

	@Override
	public CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace) {
		Products product = productService.findById(productId);
			
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		
		// 1 - them moi sp
		// 2 - update: 
		//            2-1. cong don
		//            2.2. replace
		// 3 - delete: update quantity ve 0

		if (!details.containsKey(productId)) {
			// them moi
			CartDetailDto newDetail = createNewCartDetail(product, quantity);
			details.put(productId, newDetail);
		} else if (quantity > 0) {
			if (isReplace) {
				details.get(productId).setQuantity(quantity);
			} else {
				Integer oldQuantity = details.get(productId).getQuantity();
				Integer newQuantity = oldQuantity + quantity;
				details.get(productId).setQuantity(newQuantity);
			}
		} else {
			details.remove(productId);
		}
		
		// update totalQuantity
		// update totalPrice
		return cart;
	}
	
	private CartDetailDto createNewCartDetail(Products product, Integer quantity) {
		CartDetailDto cartDetail = new CartDetailDto();
		cartDetail.setProductId(product.getId());
		cartDetail.setPrice(product.getPrice());
		cartDetail.setQuantity(quantity);
		cartDetail.setSlug(product.getSlug());
		cartDetail.setName(product.getName());
		cartDetail.setImgUrl(product.getImgUrl());
		return cartDetail;
	}
}
