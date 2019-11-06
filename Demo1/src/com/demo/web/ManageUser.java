package com.demo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.BeanUser;
import com.demo.dao.LoginDAOImpl;
import com.demo.model.LayuiResponse;
import com.demo.service.IManageUserService;

@Controller
@RequestMapping("/ManageUser")  //用户管理
public class ManageUser {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ManageUser.class);
	@Autowired
	private IManageUserService manageUserService;
	private String JspPath = "/info_manage/info_manage_user";
	
	/**
	 * 加载页面信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView();
		BeanUser beanUser = (BeanUser) request.getSession().getAttribute("curUser");
		mv.addObject("beanUser", beanUser);
		mv.setViewName(JspPath + "/index");
		return mv;
	}
	
	/**
	 * 加载表单页面信息
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form")
	public ModelAndView form(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required=false) String id
			) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(id!=null){
			BeanUser beanUser = manageUserService.getDataById(id);
			mv.addObject("beanUser", beanUser);
		}
		mv.setViewName(JspPath + "/form");
		return mv;
	}
	
	/**
	 * 加载表格信息
	 * @param request
	 * @param response
	 * @param start
	 * @param end
	 * @param username
	 * @param role
	 * @throws Exception
	 */
	@RequestMapping("/table")
	public void table(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required=false) String start,@RequestParam(required=false) String end,
			@RequestParam(required=false) String username,@RequestParam(required=false) String role,
			@RequestParam(required=false) String page,@RequestParam(required=false) String limit
		) throws Exception {
		LayuiResponse r = new LayuiResponse();
		BeanUser bu = (BeanUser) request.getSession().getAttribute("curUser");
		String role_show = bu.getRole();
		List<BeanUser> list = manageUserService.queryData(start,end,username,role,page,limit);
		int num = manageUserService.CountData(start,end,username,role);
		if(list.size()>0){
			JSONArray jsons = JSONArray.fromObject(list);
			r.sendMsg(response, true, "获取数据成功", jsons, num);
		}else{
			r.sendMsg(response, false, "获取数据");
		}
		
	}

	/**
	 * 添加用户信息
	 * @param request
	 * @param response
	 * @param id
	 * @param password
	 * @param username
	 * @param nikename
	 * @param role
	 * @throws Exception
	 */
	@RequestMapping("/Add")
	public void Add(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(required=false) String id,@RequestParam(required=false) String password,
			@RequestParam(required=false) String username,@RequestParam(required=false) String nikename,
			@RequestParam(required=false) String role
			) throws Exception{
		LayuiResponse r = new LayuiResponse();
		int num = manageUserService.AddData(username, password, nikename, role);
		if(num>0){
			r.sendMsg(response, true, "添加信息成功 !");
		}else{
			r.sendMsg(response, false, "添加信息失败 !");
		}
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @param id
	 * @param password
	 * @param username
	 * @param nikename
	 * @param role
	 * @throws Exception
	 */
	@RequestMapping("/Update")
	public void Update(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(required=false) String id,@RequestParam(required=false) String password,
			@RequestParam(required=false) String username,@RequestParam(required=false) String nikename,
			@RequestParam(required=false) String role
			) throws Exception{
		LayuiResponse r = new LayuiResponse();
		BeanUser bu = (BeanUser) request.getSession().getAttribute("curUser");
		String moduser = bu.getUsername(); 
		int num = manageUserService.UpdateData(id, username, password, nikename, role, moduser);
		if(num>0){
			r.sendMsg(response, true, "更新信息成功 !");
		}else{
			r.sendMsg(response, false, "更新信息失败 !");
		}
	}
		
	/**
	 * 删除用户信息
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping("/Delete")
	public void Delete(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(required=false) String id
			) throws Exception{
		LayuiResponse r = new LayuiResponse();
		BeanUser bu = (BeanUser) request.getSession().getAttribute("curUser");
		String username = bu.getUsername(); 
		int num = manageUserService.DeleteData(username, id);
		if(num>0){
			r.sendMsg(response, true, "删除信息成功 !");
		}else{
			r.sendMsg(response, false, "删除信息失败 !");
		}
	}
	
	/**
	 * 批量删除用户信息
	 * @param request
	 * @param response
	 * @param ids
	 * @throws Exception
	 */
	@RequestMapping("/BatchDelete")
	public void BatchDelete(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(required=false) String[] ids
			) throws Exception{
		LayuiResponse r = new LayuiResponse();
		BeanUser bu = (BeanUser) request.getSession().getAttribute("curUser");
		String username = bu.getUsername(); 
		int[] num = manageUserService.BatchDeleteData(username, ids);
		if(num.length>0){
			r.sendMsg(response, true, "删除信息成功 !");
		}else{
			r.sendMsg(response, false, "删除信息失败 !");
		}
	}
}
