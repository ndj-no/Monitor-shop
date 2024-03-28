package com.mshop.service.impl;
import org.springframework.stereotype.Service;
import com.mshop.service.CartService;
import com.mshop.entity.Cart;
import com.mshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
    private CartRepository cartRepository;
	
	public Cart getCartUser(Long id) {
        return cartRepository.getCartUser(id);
    }
	
	@Transactional
	public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
	
	public Boolean existsById(Long id) {
		return cartRepository.existsById(id);
	}
}
