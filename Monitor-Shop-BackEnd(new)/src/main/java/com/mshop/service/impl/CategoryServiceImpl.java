package com.mshop.service.impl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mshop.entity.Category;
import com.mshop.repository.CategoryRepository;
import com.mshop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;
	
	public List<Category> findByStatusTrue() {
        return categoryRepository.findByStatusTrue();
    }
	
	@Transactional
	public Category save(Category cate) {
        return categoryRepository.save(cate);
    }
	
	public Boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}
	
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}
}
