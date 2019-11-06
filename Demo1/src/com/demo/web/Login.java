package com.demo.web;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.LayuiResponse;
import com.demo.model.BeanUser;
import com.demo.db.DBOperator;
import com.demo.service.ILoginService;

@Controller
@RequestMapping("")  //用户登录
/*2019-10-31 静畏人心*/
public class Login {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Login.class);
	@Autowired
	private ILoginService loginService;
	/**
	 * 登录界面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({"","/","/login","/index"})
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BeanUser beanUser = (BeanUser) request.getSession().getAttribute("curUser");
		ModelAndView mv = new ModelAndView();
		if(beanUser==null){
			mv.setViewName("/login/index");
		}else{
			mv.addObject("beanUser", beanUser);
			mv.setViewName("/home/index");
		}
		return mv;
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	@RequestMapping("/log")
	public void log(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(required=false) String username,@RequestParam(required=false) String password) throws Exception {
		LayuiResponse r = new LayuiResponse();
		BeanUser beanUser = loginService.queryUser(username, password);
		if (beanUser == null) {
			r.sendMsg(response, false, "用户名或密码错误!");
		}else{
			request.getSession().setMaxInactiveInterval(60*60*5); //登陆状态持续5小时
			request.getSession().setAttribute("curUser",beanUser);
			r.sendMsg(response, true, "验证成功");
		}
	}
	
	/**
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/exit")
	public ModelAndView exit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView();
		request.getSession().setAttribute("curUser",null);
		request.getSession().invalidate();
		mv.setViewName("/login/index");
		return mv;
	}
	
	/**
	 * 异常界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/error")
	public ModelAndView error(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/error");
		return mv;
	}
}

