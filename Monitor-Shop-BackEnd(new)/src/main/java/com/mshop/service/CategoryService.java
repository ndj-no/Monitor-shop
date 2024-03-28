package com.mshop.service;
import java.util.List;
import java.util.Optional;

import com.mshop.entity.Category;

public interface CategoryService {
	public List<Category> findByStatusTrue();
	
	public Category save(Category cartDetail);
	
	public Boolean existsById(Long id);
	
	public Optional<Category> findById(Long id);
	
	public void deleteById(Long id);
}
