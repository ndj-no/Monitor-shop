package com.mshop.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.mshop.service.RateService;
import com.mshop.entity.Rate;
import com.mshop.repository.RateRepository;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {
	@Autowired
    private RateRepository rateRepository;
	
	public List<Rate> findByProduct(Long id) {
        return rateRepository.findByProduct(id);
    }
	
	public Optional<Rate> findById(Long id) {
        return rateRepository.findById(id);
    }
	
	public Boolean existsById(Long id) {
		return rateRepository.existsById(id);
	}
	
	public Double getAvgByProduct(Long id) {
		return rateRepository.getAvgByProduct(id);
	}
	
	@Transactional
	public Rate save(Rate cart) {
        return rateRepository.save(cart);
    }
	
	public List<Rate> findAll() {
        return rateRepository.findAll();
    }
	
	public void deleteById(Long id) {
        rateRepository.deleteById(id);
    }
}
