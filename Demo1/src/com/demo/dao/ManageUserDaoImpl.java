package com.demo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.demo.model.BeanUser;
import com.demo.db.DBOperator;
import com.demo.service.IManageUserService;
import com.demo.utils.StrUtils;
import com.demo.utils.TimeUtils;
import com.demo.utils.UuidUtils;

@Component
public class ManageUserDaoImpl implements IManageUserService{
	/**
	 * 根据id获取数据
	 */
	public BeanUser getDataById(String id) throws Exception{
		DBOperator db = new DBOperator();
		try {
			String sql ="select * from info_user where status = '1' and delstatus = '0' and id = ? ";
			List<BeanUser> list = db.find(sql, new Object[]{id}, BeanUser.class);
			BeanUser beanUser = list.get(0);
			return beanUser;
		} catch (Exception e) {
			throw e;
		} finally {
			db.freeCon();
		}
	}
	
	/**
	 * 遍历用户数据,分页
	 */
	public List<BeanUser> queryData(String start,String end,String username,String role,String page,String limit) throws Exception{
		DBOperator db = new DBOperator();
		int curr = (Integer.parseInt(page)-1)*Integer.parseInt(limit);
		try {
			String sql ="select * from info_user where status = '1' and delstatus = '0'";
			if(StrUtils.strIsNotEmpty(start)){
				sql += " and createdate > '"+start+"'";
			}
			if(StrUtils.strIsNotEmpty(end)){
				sql += " and createdate < '"+end+"'";
			}
			if(StrUtils.strIsNotEmpty(username)){
				sql += " and username like '%"+username+"%'";
			}
			if(StrUtils.strIsNotEmpty(role)){
				sql += " and role = '"+role+"'";
			}
			sql += " limit " + curr + ","+limit;
			List<BeanUser> list = db.find(sql, BeanUser.class);
			return list;
		} catch (Exception e) {
			throw e;
		} finally {
			db.freeCon();
		}
	}
	
	/**
	 * 获取用户数量
	 */
	public int CountData(String start,String end,String username,String role) throws Exception{
		DBOperator db = new DBOperator();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createdate = df.format(new Date());
		try {
			String sql ="select * from info_user where status = '1' and delstatus = '0'";
			if(StrUtils.strIsNotEmpty(start)){
				sql += " and createdate >= '"+start+"'";
			}
			if(StrUtils.strIsNotEmpty(end)){
				sql += " and createdate <= '"+end+"'";
			}
			if(StrUtils.strIsNotEmpty(username)){
				sql += " and username like '%"+username+"%'";
			}
			if(StrUtils.strIsNotEmpty(role)){
				sql += " and role = '"+role+"'";
			}
			List<Map> list = db.find("select count(*) sum from ( "+sql+" ) t");
			int num = Integer.parseInt(list.get(0).get("sum").toString());
			return num;
		} catch (Exception e) {
			throw e;
		} finally {
			db.freeCon();
		}
	}
	
	/**
	 * 添加用户
	 */
	public int AddData(String username, String password, String nikename, String role) throws Exception{
		DBOperator db = new DBOperator();
		String uuid = (String) new UuidUtils().generate();
		String createdate = TimeUtils.getNowTime();
		try {
			String sql ="insert into info_user(id,username,password,nikename,role,createdate,status,delstatus) values(?,?,?,?,?,?,?,?) ";
			int num = db.excute(sql, new Object[]{uuid, username, password, nikename, role, createdate, 1, 0});
			db.commit();
			return num;
		} catch (Exception e) {
			db.rollback();
			throw e;
		} finally {
			db.freeCon();
		}
	}
	
	/**
	 * 修改用户
	 */
	public int UpdateData(String id, String username, String password, String nikename, String role, String moduser) throws Exception{
		DBOperator db = new DBOperator();
		String uuid = (String) new UuidUtils().generate();
		String moddate = TimeUtils.getNowTime();
		try {
			String sql ="update info_user set username = ?, password = ?, nikename = ?, role = ?," +
					"moduser = ?,moddate = date_format(?, '%Y-%m-%d %H:%i:%s') where id=? ";
			int num = db.excute(sql, new Object[]{username, password, nikename, role, moduser, moddate, id});
			db.commit();
			return num;
		} catch (Exception e) {
			db.rollback();
			throw e;
		} finally {
			db.freeCon();
		}
	}
	
	/**
	 * 删除用户
	 */
	public int DeleteData(String username, String id) throws Exception{
		DBOperator db = new DBOperator();
		String createdate = TimeUtils.getNowTime();
		try {
			String sql ="update info_user set delstatus = '1',deluser = ?,deldate = date_format(?, '%Y-%m-%d %H:%i:%s') where id=? ";
			int num = db.excute(sql, new Object[]{username,createdate, id});
			db.commit();
			return num;
		} catch (Exception e) {
			db.rollback();
			throw e;
		} finally {
			db.freeCon();
		}
	}
	
	/**
	 * 批量删除用户
	 */
	public int[] BatchDeleteData(String username, String[] ids) throws Exception{
		DBOperator db = new DBOperator();
		String createdate = TimeUtils.getNowTime();
		Object[][] params = new Object[ids.length][];
		try {
			String sql ="update info_user set delstatus = '1',deluser = ?,deldate = date_format(?, '%Y-%m-%d %H:%i:%s') where id=? ";
			int i = 0;
			for (String id : ids) {  
				params[i] = new Object[]{username,createdate, id};
				i++;
			} 
			int[] num = db.excuteBatch(sql, params);
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
