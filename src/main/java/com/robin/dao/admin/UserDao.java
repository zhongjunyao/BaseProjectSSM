package com.robin.dao.admin;

import org.springframework.stereotype.Repository;

import com.robin.entity.admin.User;

/**
 * User Dao
 * @author Administrator
 *
 */
@Repository
public interface UserDao {
	public User findByUsername(String username);
}
