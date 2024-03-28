package com.mshop.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mshop.entity.OrderDetail;
import com.mshop.repository.OrderDetailRepository;
import com.mshop.service.OrderDetailService;

@Service
public class OrDerDetailServiceImpl implements OrderDetailService{
	@Autowired
    private OrderDetailRepository orderDetailRepository;
	
	public List<OrderDetail> findOrderDetailByOrderId(Long id) {
        return orderDetailRepository.findOrderDetailByOrderId(id);
    }
	
	@Transactional
	public OrderDetail save(OrderDetail detail) {
        return orderDetailRepository.save(detail);
    }
	
	public Boolean existsById(Long id) {
		return orderDetailRepository.existsById(id);
	}
	
	public Optional<OrderDetail> findById(Long id) {
		return orderDetailRepository.findById(id);
	}
}
