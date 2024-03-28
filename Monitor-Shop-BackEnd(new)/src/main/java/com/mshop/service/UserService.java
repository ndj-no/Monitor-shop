package com.mshop.service;

import java.util.List;

import com.mshop.entity.User;
import java.util.Optional;

public interface UserService {
	
	public List<User> findByStatusTrueAndRoleFalse();
	
	public User findByEmail(String email);
	
	public Boolean existsByEmail(String email);	
	
	public List<User> findAllAdmin();
	
	public List<User> findAllUser();
	
	public User save(User user);
	
	public Boolean existsById(Long id);
	
	public Optional<User> findById(Long id);
}
