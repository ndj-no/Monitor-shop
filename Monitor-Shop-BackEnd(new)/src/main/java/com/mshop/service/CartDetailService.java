package com.mshop.service;
import java.util.List;
import java.util.Optional;
import com.mshop.entity.CartDetail;

public interface CartDetailService {
	
	public List<CartDetail> getByCartId(Long id);
	
	public Long getCount(Long id);
	
	public CartDetail save(CartDetail cartDetail);
	
	public Boolean existsById(Long id);
	
	public Optional<CartDetail> findById(Long id);
	
	public void deleteById(Long id);
}
