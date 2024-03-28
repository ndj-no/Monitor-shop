package com.mshop.service;

import java.util.List;
import java.util.Optional;
import com.mshop.entity.OrderDetail;

public interface OrderDetailService {
	public List<OrderDetail> findOrderDetailByOrderId(Long id);
	
	public OrderDetail save(OrderDetail detail);
	
	public Optional<OrderDetail> findById(Long id);
	
	public Boolean existsById(Long id);
}
