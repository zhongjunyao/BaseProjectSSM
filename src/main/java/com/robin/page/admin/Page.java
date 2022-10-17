package com.robin.page.admin;

import org.springframework.stereotype.Component;

/**
 * 分页
 * @author Administrator
 *
 */

@Component
public class Page {
	private int page = 1;		// 当前页码
	private int rows; 			// 每页显示的数量
	private int offset; 		// 对应数据库中的偏移量
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getOffset() {
		this.offset = (this.page - 1) * this.rows;
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
