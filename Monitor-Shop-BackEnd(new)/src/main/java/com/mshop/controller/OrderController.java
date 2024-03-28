package com.mshop.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mshop.entity.Order;
import com.mshop.entity.OrderDetail;
import com.mshop.service.OrderDetailService;
import com.mshop.service.OrderService;
import com.mshop.service.UserService;
import com.mshop.service.SendMailService;

//@CrossOrigin("*")
@RestController
@RequestMapping("api/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SendMailService sendmail;
	
	@GetMapping()
	public ResponseEntity<List<Order>> getAll() {
		return ResponseEntity.ok(orderService.findAllOrderDesc());
	}
	
	@GetMapping("/wait")
	public ResponseEntity<List<Order>> getAllWait() {
		return ResponseEntity.ok(orderService.findAllOrderWait());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Order> getOne(@PathVariable("id") Long id) {
		if(!orderService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderService.findById(id).get());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Order>> getAllByUser(@PathVariable("id") Long id) {
		if(!userService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderService.findAllOrderByUserId(id));
	}
	
	
	@GetMapping("/user/wait/{id}")
	public ResponseEntity<List<Order>> getAllWaitByUser(@PathVariable("id") Long id) {
		if(!userService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderService.findAllOrderWaitByUserId(id));
	}
	
	@GetMapping("/user/confirmed/{id}")
	public ResponseEntity<List<Order>> getAllConfirmedByUser(@PathVariable("id") Long id) {
		if(!userService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderService.findAllOrderConfirmedByUserId(id));
	}
	
	@GetMapping("/user/paid/{id}")
	public ResponseEntity<List<Order>> getAllPaidByUser(@PathVariable("id") Long id) {
		if(!userService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderService.findAllOrderPaidByUserId(id));
	}
	
	@GetMapping("/user/cancel/{id}")
	public ResponseEntity<List<Order>> getAllCancelByUser(@PathVariable("id") Long id) {
		if(!userService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderService.findAllOrderCancelByUserId(id));
	}
	
	@PostMapping
	public ResponseEntity<Order> post(@RequestBody Order order) {
		if(orderService.existsById(order.getId())) {
			return ResponseEntity.badRequest().build();
		}
		if(!userService.existsById(order.getUser().getUserId())) {
			return ResponseEntity.notFound().build();
		}
		Order o = orderService.save(order);
		return ResponseEntity.ok(o);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Order> put(@PathVariable("id") Long id, @RequestBody Order order) {
		if(!orderService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if(id != order.getId()) {
			return ResponseEntity.badRequest().build();
		}
		Order o = orderService.save(order);
		return ResponseEntity.ok(o);
	}
	
}
