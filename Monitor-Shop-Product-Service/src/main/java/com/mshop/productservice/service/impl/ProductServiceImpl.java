package com.mshop.productservice.service.impl;

import com.mshop.productservice.entity.Product;
import com.mshop.productservice.repository.ProductRepository;
import com.mshop.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProductByCategoryId(Long id) {
        return productRepository.findAllProductByCategoryId(id);
    }

    public List<Product> findAllStatus() {
        return productRepository.findAllStatusTrue();
    }

    public Product findByIdAndStatusTrue(Long id) {
        return productRepository.findByIdAndStatusTrue(id);
    }

    @Transactional
    public Product save(Product cart) {
        return productRepository.save(cart);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}
