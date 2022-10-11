package com.robin.service.admin;

import org.springframework.stereotype.Service;

import com.robin.entity.admin.User;

/**
 * 用户service
 * @author Administrator
 *
 */
@Service
public interface UserService {
	public User findByUsername(String username);
	
}
