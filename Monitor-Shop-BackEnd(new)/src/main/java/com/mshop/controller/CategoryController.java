package com.mshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mshop.entity.Category;
import com.mshop.service.CategoryService;

//@CrossOrigin("*")
@RestController
@RequestMapping("api/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping()
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.findByStatusTrue());
	}
	
	@RequestMapping("{id}")
	public ResponseEntity<Category> getOne(@PathVariable("id") Long id) {
		if(!categoryService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoryService.findById(id).get());
	}
	
	@PostMapping
	public ResponseEntity<Category> post(@RequestBody Category category) {
		if(categoryService.existsById(category.getCategoryId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(categoryService.save(category));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Category> put(@RequestBody Category category, @PathVariable("id") Long id) {
		if(!id.equals(category.getCategoryId())) {
			return ResponseEntity.badRequest().build();
		}
		if(!categoryService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoryService.save(category));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if(!categoryService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Category ca = categoryService.findById(id).get();
		ca.setStatus(false);
		categoryService.save(ca);
//		repo.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
