package com.mshop.authservice.controller;

import com.mshop.authservice.client.CartClient;
import com.mshop.authservice.dto.Cart;
import com.mshop.authservice.entity.User;
import com.mshop.authservice.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CartClient cartClient;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findByStatusTrueAndRoleFalse());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.findById(id).get());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<User> getOneByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<User> post(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.notFound().build();
        }
        if (userService.existsById(user.getUserId())) {
            return ResponseEntity.badRequest().build();
        }
        String encodedPw = DigestUtils
                .md5Hex(user.getPassword())
                .toUpperCase();
        user.setPassword(encodedPw);
        User u = userService.save(user);
        Cart c = new Cart(0L, 0.0, user.getAddress(), user.getPhone(), true, u.getUserId());
        cartClient.addCartUser(u.getUserId(), c);
        return ResponseEntity.ok(u);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> put(@PathVariable("id") Long id, @RequestBody User user) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        if (!id.equals(user.getUserId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
//		Crepo.deleteByUserId(id);
        User u = userService.findById(id).get();
        u.setStatus(false);
        userService.save(u);
//		repo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/exist-by-user-id")
    public Boolean existByUserId(@RequestParam("userId") Long userId) {
        return userService.existsById(userId);
    }

    @GetMapping("/exist-by-email")
    public Boolean existByEmail(@RequestParam("email") String email) {
        return userService.existsByEmail(email);
    }

    @PostMapping("/get-all-by-ids")
    public ResponseEntity<List<User>> getAllByIds(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(userService.findAllByIds(ids));
    }
}
