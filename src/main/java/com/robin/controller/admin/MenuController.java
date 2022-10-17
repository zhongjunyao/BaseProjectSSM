package com.robin.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.robin.entity.admin.Menu;
import com.robin.service.admin.MenuService;

/**
 * 菜单管理控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model) {
		model.setViewName("menu/list");
		return model;
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Menu menu) {
		Map<String, Object> ret = new HashMap<>();
		if(menu == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写正确菜单信息！");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getName())) {
			ret.put("type", "error");
			ret.put("msg", "请填写菜单名称！");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getUrl())) {
			ret.put("type", "error");
			ret.put("msg", "请填写菜单链接！");
			return ret;
		}
		if(StringUtils.isEmpty(menu.getIcon())) {
			ret.put("type", "error");
			ret.put("msg", "请填写菜单图标！");
			return ret;
		}
		Integer retCount = menuService.addMenu(menu);
		Long retId = menu.getId();
		System.out.println("retId:" + retId + "; menu.id: " + retId);
		if(retCount<=0) {
			ret.put("type", "error");
			ret.put("msg", "添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "菜单添加成功");
		ret.put("data", retId);
		return ret;
	}
	
	@RequestMapping(value="/getMenuList", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getMenuList(Menu menu, 
			@RequestParam(required = false, defaultValue = "20") int pageSize, 
			@RequestParam(required = false, defaultValue = "1") int pageIndex) {
		Map<String, Object> ret = new HashMap<>();
		Map<String, Object> data = new HashMap<>();
		List<Menu> retList = menuService.getMenuList(menu, pageSize, pageIndex);
		if(retList==null) {
			ret.put("type", "error");
			ret.put("msg", "查询菜单失败，请联系管理员！");
			return ret;
		}
		data.put("total", 12);
		data.put("rows", retList);
		
		ret.put("type", "success");
		ret.put("msg", "查询菜单成功");
		ret.put("data", data);
		return ret;
	}

}
