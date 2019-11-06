package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.model.BeanUser;

public interface ILoginService {
	
	public BeanUser queryUser(String username, String password) throws Exception;
	
	public BeanUser queryUsername(String username) throws Exception;
	
	public List<Map> searchNumAdd(String username) throws Exception;
	
	public int insertUser(String username, String password, String role) throws Exception;
}
