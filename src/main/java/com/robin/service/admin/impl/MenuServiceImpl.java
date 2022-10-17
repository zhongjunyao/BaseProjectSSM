package com.robin.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.robin.dao.admin.MenuDao;
import com.robin.entity.admin.Menu;
import com.robin.service.admin.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public Integer addMenu(Menu menu) {
		return menuDao.addMenu(menu);
	}

	@Override
	public List<Menu> getMenuList(Menu menu, int pageSize, int pageIndex) {
		return menuDao.getMenuList(menu, pageSize, (pageIndex-1) * pageSize);
	}

}
