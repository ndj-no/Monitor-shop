package com.mshop.service.impl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mshop.entity.Order;
import com.mshop.repository.OrderRepository;
import com.mshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
    private OrderRepository orderRepository;
	
	public List<Order> findAllOrderDesc() {
        return orderRepository.findAllOrderDesc();
    }
	
	public List<Order> findAllOrderByUserId(Long id) {
		return orderRepository.findAllOrderByUserId(id);
	}
	
	public List<Order> findAllOrderWait() {
		return orderRepository.findAllOrderWait();
	}
	
	public List<Order> findAllOrderCancelByUserId(Long id) {
		return orderRepository.findAllOrderCancelByUserId(id);
	}
	
	public List<Order> findAllOrderWaitByUserId(Long id) {
		return orderRepository.findAllOrderWaitByUserId(id);
	}
	
	public List<Order> findAllOrderConfirmedByUserId(Long id) {
		return orderRepository.findAllOrderConfirmedByUserId(id);
	}
	
	public List<Order> findAllOrderPaidByUserId(Long id) {
		return orderRepository.findAllOrderPaidByUserId(id);
	}
	
	public List<Object[]> getStatisticalMonthYear(int year) {
		return orderRepository.getStatisticalMonthYear(year);
	}
	
	public List<Object[]> getStatisticalYear() {
		return orderRepository.getStatisticalYear();
	}
	
	public List<Object[]> getStatisticalMonth() {
		return orderRepository.getStatisticalMonth();
	}
	
	public List<Object[]> getStatisticalDate() {
		return orderRepository.getStatisticalDate();
	}
	
	public List<Integer> getYears() {
		return orderRepository.getYears();
	}
	@Transactional
	public Order save(Order order) {
        return orderRepository.save(order);
    }
	
	public Boolean existsById(Long id) {
		return orderRepository.existsById(id);
	}
	
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}
}
