package com.robin.entity.admin;

import org.springframework.stereotype.Component;

@Component
public class Menu {
	private Long id;
	private Long parentId;			// 父菜单ID
	private String name;			// 菜单名称
	private String url;				// 点击后的url
	private String icon;			// 菜单icon图标
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
