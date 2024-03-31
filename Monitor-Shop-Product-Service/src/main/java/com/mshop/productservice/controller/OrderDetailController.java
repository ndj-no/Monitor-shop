package com.mshop.productservice.controller;

import com.mshop.productservice.entity.OrderDetail;
import com.mshop.productservice.service.OrderDetailService;
import com.mshop.productservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order-detail")
public class OrderDetailController {
    @Autowired
    OrderDetailService repo;

    @Autowired
    OrderService Orepo;

    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> get(@PathVariable("id") Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDetail> put(@PathVariable("id") Long id, @RequestBody OrderDetail orderDetail) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (id != orderDetail.getId()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.save(orderDetail));
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailByOrder(@PathVariable("id") Long id) {
        if (!Orepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repo.findOrderDetailByOrderId(id));
    }

    @PostMapping()
    public ResponseEntity<OrderDetail> post(@RequestBody OrderDetail orderDetail) {
        if (repo.existsById(orderDetail.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.save(orderDetail));
    }

}
