package com.mshop.service;
import java.util.List;
import java.util.Optional;
import com.mshop.entity.Product;

public interface ProductService {
	public List<Product> findAllProductByCategoryId(Long id);
	
	public List<Product> findAllStatus();
	
	public Product findByIdAndStatusTrue(Long id);
	
	public Product save(Product cart);
	
	public Optional<Product> findById(Long id);
	
	public Boolean existsById(Long id);
}
