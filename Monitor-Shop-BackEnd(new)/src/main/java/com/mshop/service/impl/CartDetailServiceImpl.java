package com.mshop.service.impl;
import org.springframework.stereotype.Service;
import com.mshop.service.CartDetailService;
import com.mshop.entity.CartDetail;
import com.mshop.repository.CartDetailRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartDetailServiceImpl implements CartDetailService {
	
	@Autowired
    private CartDetailRepository cartDetailRepository;
	
	public List<CartDetail> getByCartId(Long id) {
        return cartDetailRepository.getByCartId(id);
    }
	
	public Long getCount(Long id) {
		return cartDetailRepository.getCount(id);
	}
	
	@Transactional
	public CartDetail save(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }
	
	public Boolean existsById(Long id) {
		return cartDetailRepository.existsById(id);
	}
	
	public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }
	
	@Transactional
	public void deleteById(Long id) {
        cartDetailRepository.deleteById(id);;
    }
}
