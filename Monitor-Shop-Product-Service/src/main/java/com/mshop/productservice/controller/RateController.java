package com.mshop.productservice.controller;

import com.mshop.productservice.entity.Rate;
import com.mshop.productservice.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("api/rates")
public class RateController {
    @Autowired
    RateService rateService;

    @GetMapping()
    public ResponseEntity<List<Rate>> getAll() {
        return ResponseEntity.ok(rateService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Rate> getOne(@PathVariable("id") Long id) {
        if (!rateService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rateService.findById(id).get());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<List<Rate>> getByProduct(@PathVariable("id") Long id) {
        return ResponseEntity.ok(rateService.findByProduct(id));
    }

    @GetMapping("product-avg/{id}")
    public ResponseEntity<Double> getAvgProduct(@PathVariable("id") Long id) {
        Double rate = rateService.getAvgByProduct(id);
        if (rate == null) {
            rate = 0.0;
            return ResponseEntity.ok(rate);
        }
        return ResponseEntity.ok(rate);
    }

    @PostMapping()
    public ResponseEntity<Rate> post(@RequestBody Rate rate) {
        return ResponseEntity.ok(rateService.save(rate));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        if (!rateService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        rateService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
