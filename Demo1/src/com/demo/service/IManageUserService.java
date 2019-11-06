package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.model.BeanUser;

public interface IManageUserService {
	public BeanUser getDataById(String id) throws Exception;
	
	public List<BeanUser> queryData(String start,String end,String username,String role,String page,String limit) throws Exception;
	
	public int CountData(String start,String end,String username,String role) throws Exception;
	
	public int AddData(String username, String password, String nikename, String role) throws Exception;
	
	public int UpdateData(String id, String username, String password, String nikename, String role, String moduser) throws Exception;
	
	public int DeleteData(String username, String id) throws Exception;
	
	public int[] BatchDeleteData(String username, String[] ids) throws Exception;
}
