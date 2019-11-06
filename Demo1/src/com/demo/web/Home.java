package com.demo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.HomeDAOImpl;
import com.demo.model.BeanUser;

@Controller
@RequestMapping("/home")  //首页
public class Home {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Home.class);
	@Autowired
	private HomeDAOImpl homeBeanDAOImpl; 
	
	/**
	 * 我的桌面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/welcome")
	public ModelAndView welcome(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BeanUser beanUser = (BeanUser) request.getSession().getAttribute("curUser");
		ModelAndView mv = new ModelAndView();
		mv.addObject("beanUser", beanUser);
		mv.setViewName("/home/welcome");
		return mv;
	}
}
