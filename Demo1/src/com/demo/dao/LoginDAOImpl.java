package com.demo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.demo.db.DBOperator;
import com.demo.service.ILoginService;
import com.demo.utils.UuidUtils;
import com.demo.model.BeanUser;

@Component
public class LoginDAOImpl implements ILoginService{
	/**
	 * 遍历用户表
	 */
	public BeanUser queryUser(String username, String password) throws Exception{
		DBOperator db = new DBOperator();
		try{
			String sql ="select * from info_user bu where bu.username =? and binary bu.password=? and status ='1'";
			List<BeanUser> bulist = new ArrayList<BeanUser>();
			bulist = db.find(sql, new Object[]{username,password}, BeanUser.class);
			if(bulist.size()>0){
				return bulist.get(0);
			}else{
				return null;
			}
		}catch (Exception e) {
			db.rollback();
			throw e;
		}finally {
			db.freeCon();
		}
	}
	
	/**
	 * 遍历用户表
	 */
	public BeanUser queryUsername(String username) throws Exception{
		DBOperator db = new DBOperator();
		try {
			String sql ="select * from info_user bu where bu.username =? and status ='1'";
			List<BeanUser> bulist = new ArrayList<BeanUser>();
			bulist = db.find(sql, new Object[]{username}, BeanUser.class);
			if(bulist.size()>0){
				return bulist.get(0);
			}else{
				return null;
			}
		}catch (Exception e) {
			db.rollback();
			throw e;
		}finally {
			db.freeCon();
		}
	}
	/**
	 * 判断用户的账号是否已被管理员添加
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public List<Map> searchNumAdd(String username) throws Exception{
		DBOperator db = new DBOperator();
		try {
			String sql ="select * from stu_info where stunumber = ? ";
			List<Map> list = db.find(sql, new Object[]{username});
			return list;
		}catch (Exception e) {
			db.rollback();
			throw e;
		}finally {
			db.freeCon();
		}
	}
	/**
	 * 注册
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public int insertUser(String username, String password, String role) throws Exception{
		DBOperator db = new DBOperator();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createdate = df.format(new Date());
		String uuid = (String) new UuidUtils().generate();
		try {
			String sql ="insert into info_user(userid,username,password,nikename,role,createdate,status) " +
					"values(?,?,?,?,?,STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s'),1)";
			int num = db.excute(sql, new Object[]{uuid,username,password,username,role,createdate});
			db.commit();
			return num;
		} catch (Exception e) {
			db.rollback();
			throw e;
		} finally {
			db.freeCon();
		}
	}
}
