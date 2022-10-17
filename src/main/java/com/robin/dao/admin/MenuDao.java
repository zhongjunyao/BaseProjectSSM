package com.robin.dao.admin;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.robin.entity.admin.Menu;

/**
 * Menu DAO
 * @author Administrator
 *
 */
@Repository
public interface MenuDao {
	
	public Integer addMenu(Menu menu);
	public List<Menu> getMenuList(
			@Param("menu") Menu menu, 
			@Param("pageSize") int pageSize, 
			@Param("offset") int offset);
}
