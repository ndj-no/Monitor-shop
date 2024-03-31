package com.mshop.productservice.controller;

import com.mshop.productservice.entity.Product;
import com.mshop.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAllStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Long id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        List<Product> list = productService.findAllStatus();
        Product product = productService.findByIdAndStatusTrue(id);
        boolean check = false;
        for (Product p : list) {
            if (p.getProductId() == product.getProductId()) {
                check = true;
            }
        }
        ;
        if (!check) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> post(@RequestBody Product product) {
        if (productService.existsById(product.getProductId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> put(@PathVariable("id") Long id, @RequestBody Product product) {
        if (!id.equals(product.getProductId())) {
            return ResponseEntity.badRequest().build();
        }
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!productService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Product pro = productService.findById(id).get();
        pro.setStatus(false);
        productService.save(pro);
//		repo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-category/{id}")
    public ResponseEntity<List<Product>> getAllByCategory(@PathVariable("id") Long id) {
//		System.out.println(repo.findAllProductByCategoryId(id));
        return ResponseEntity.ok(productService.findAllProductByCategoryId(id));
    }
}
