package com.robin.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.robin.entity.admin.Menu;

@Service
public interface MenuService {
	public Integer addMenu(Menu menu);
	public List<Menu> getMenuList(Menu menu, int pageSize, int pageIndex);
}
