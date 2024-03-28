package com.mshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mshop.entity.CartDetail;
import com.mshop.entity.Product;
import com.mshop.service.CartDetailService;
import com.mshop.service.CartService;
import com.mshop.service.ProductService;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart-detail")
public class CartDetailController {
	@Autowired
	CartDetailService cartDetailService;

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	

	@GetMapping("cart/{id}")
	public ResponseEntity<List<CartDetail>> getByCartId(@PathVariable("id") Long id) {
		if (!cartService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartDetailService.getByCartId(id));
	}
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	public ResponseEntity<CartDetail> getOne(@PathVariable("id") Long id) {
		if(!cartDetailService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartDetailService.findById(id).get());
	}

	@PostMapping()
	public ResponseEntity<CartDetail> post(@RequestBody CartDetail detail) {
		if (!cartService.existsById(detail.getCart().getId())) {
			return ResponseEntity.notFound().build();
		}
		boolean check = false;
		List<Product> listP = productService.findAllStatus();
		Product product = productService.findByIdAndStatusTrue(detail.getProduct().getProductId());
		for(Product p : listP){
			if(p.getProductId() == product.getProductId()) {
				check = true;
			}
		};
		if(!check) {
			return ResponseEntity.notFound().build();			
		}
		List<CartDetail> listD = cartDetailService.getByCartId(detail.getCart().getId());
		for (CartDetail item : listD) {
			if (item.getProduct().getProductId() == detail.getProduct().getProductId()) {
				item.setQuantity(item.getQuantity() + 1);
				item.setPrice(item.getPrice() + detail.getPrice());
				return ResponseEntity.ok(cartDetailService.save(item));
			}
		}
		return ResponseEntity.ok(cartDetailService.save(detail));
	}

	@PutMapping()
	public ResponseEntity<CartDetail> put(@RequestBody CartDetail detail) {
		if (!cartService.existsById(detail.getCart().getId())) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cartDetailService.save(detail));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (!cartDetailService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		cartDetailService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
