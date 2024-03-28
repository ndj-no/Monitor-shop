package com.mshop.service;
import com.mshop.entity.Cart;

public interface CartService {
	public Cart getCartUser(Long id);
	
	public Cart save(Cart cart);
	
	public Boolean existsById(Long id);
}
