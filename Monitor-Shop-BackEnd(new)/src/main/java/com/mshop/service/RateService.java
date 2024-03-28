package com.mshop.service;

import java.util.List;
import java.util.Optional;
import com.mshop.entity.Rate;

public interface RateService {
	public List<Rate> findByProduct(Long id);
	
	public Double getAvgByProduct(Long id);
	
	public Boolean existsById(Long id);
	
	public Optional<Rate> findById(Long id);
	
	public Rate save(Rate cart);
	
	public List<Rate> findAll();
	
	public void deleteById(Long id);
}
