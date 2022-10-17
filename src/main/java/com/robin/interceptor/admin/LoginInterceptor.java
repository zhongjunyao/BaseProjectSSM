package com.robin.interceptor.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;

/**
 * 后台登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object others, Exception exception)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object others, ModelAndView model)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object others) throws Exception {
		String requestURI = request.getRequestURI();
		Object admin = request.getSession().getAttribute("admin");
		if(admin == null) {
			// 表示未登录，或者登录失效
			System.out.println("链接 '"+requestURI+"' 进入拦截器");
			System.out.println("Context:" + request.getServletContext().getContextPath());
			
			String xhrHeadField = request.getHeader("X-Requested-With");
			// 判断是否ajax 
			if("XMLHttpRequest".equals(xhrHeadField)) {//是 ajax 请求
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录会话超时或还未登录，请重新登录！");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			// 表示普通链接跳转，直接重定向到登录界面
			response.sendRedirect(request.getServletContext().getContextPath() + "/system/login");
			return false;
		}
		return true;
	}

}
