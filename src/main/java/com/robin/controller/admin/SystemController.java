package com.robin.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.robin.entity.admin.User;
import com.robin.service.admin.UserService;
import com.robin.util.CpachaUtil;

@Controller
@RequestMapping("/system")
public class SystemController {
	@Autowired
	private UserService userService;
	
//	@RequestMapping(value="/index", method=RequestMethod.GET)
//	public String index() {
//		return "system/index";
//	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("system/index");
		model.addObject("name", "Robin");
		return model;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}
	
	/**
	 * 登录表单提交处理
	 * @param user
	 * @param cpacha
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(User user, String cpacha, HttpServletRequest request) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if(user == null) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户信息！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			ret.put("type", "error");
			ret.put("msg", "请填写用户名！");
			return ret;
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "请填写密码！");
			return ret;
		}
		if(StringUtils.isEmpty(cpacha)) {
			ret.put("type", "error");
			ret.put("msg", "请填写验证码！");
			return ret;
		}
		Object loginCpacha = request.getSession().getAttribute("loginCpacha");
		if(loginCpacha == null) {
			ret.put("type", "error");
			ret.put("msg", "会话超时，请刷新页面！");
			return ret;
		}
		if(!cpacha.equalsIgnoreCase(loginCpacha.toString())) {
			ret.put("type", "error");
			ret.put("msg", "验证码错误！");
			return ret;
		}
		User findUserName = userService.findByUsername(user.getUsername());
		if(findUserName==null) {
			ret.put("type", "error");
			ret.put("msg", "该用户不存在！");
			return ret;
		}
		if(!user.getPassword().equals(findUserName.getPassword())) {
			ret.put("type", "error");
			ret.put("msg", "密码错误！");
			return ret;
		}

		
		ret.put("type", "success");
		ret.put("msg", "登录成功！");
		ret.put("data",findUserName);
		return ret;
	}
	
	/**
	 * 本系统所有的验证码均采用此方法
	 * @param vcodeLength
	 * @param width
	 * @param height
	 * @param cpachaType 用来区别验证码的类型，传入字符串
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/generateCpacha", method=RequestMethod.GET)
	public void generateCpacha(
			@RequestParam(name="vl", required = false, defaultValue = "4") Integer vcodeLength,
			@RequestParam(name="w", required = false, defaultValue = "115") Integer width,
			@RequestParam(name="h", required = false, defaultValue = "30") Integer height,
			@RequestParam(name="type", required = false, defaultValue = "loginCpacha") String cpachaType,
			HttpServletRequest request, HttpServletResponse response) {
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLength, width, height);
		String vcode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, vcode);
		BufferedImage bufferedVcodeImage = cpachaUtil.generatorVCodeImage(vcode, true);
		try {
			ImageIO.write(bufferedVcodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
