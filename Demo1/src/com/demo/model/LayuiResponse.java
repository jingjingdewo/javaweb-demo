package com.demo.model;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LayuiResponse {
	private HttpServletResponse response;
	
	private boolean code = true;
	private Object msg;
	private Object count;
	private Object data;
	
	public HttpServletResponse getResponse() {
		return response;
	}
	public LayuiResponse setResponse(HttpServletResponse response) {
		this.response = response;
		return this;
	}
	
	public boolean isCode() {
		return code;
	}
	public LayuiResponse setCode(boolean code) {
		this.code = code;
		return this;
	}
	public Object getMsg() {
		return msg;
	}
	public LayuiResponse setMsg(Object msg) {
		this.msg = msg;
		return this;
	}
	public Object getCount() {
		return count;
	}
	public LayuiResponse setCount(Object count) {
		this.count = count;
		return this;
	}
	public Object getData() {
		return data;
	}
	public LayuiResponse setData(Object data) {
		this.data = data;
		return this;
	}
	
	public void sendMsg(HttpServletResponse response, Boolean code, String msg) throws IOException {
		JSONObject obj = new JSONObject();
		int c=code?0:1;
		obj.put("code", c);
		obj.put("msg", msg);
		response.setContentType("text/x-json;charset=UTF-8");
		response.getWriter().write(obj.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	public void sendMsg(HttpServletResponse response, Boolean code, String msg, JSONArray data) throws IOException {
		JSONObject obj = new JSONObject();
		int c=code?0:1;
		obj.put("code", c);
		obj.put("msg", msg);
		obj.put("data", data);
		response.setContentType("text/x-json;charset=UTF-8");
		response.getWriter().write(obj.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	public void sendMsg(HttpServletResponse response, Boolean code, String msg, JSONArray data, int count) throws IOException {
		JSONObject obj = new JSONObject();
		int c=code?0:1;
		obj.put("code", c);
		obj.put("msg", msg);
		obj.put("count", count);
		obj.put("data", data);
		response.setContentType("text/x-json;charset=UTF-8");
		response.getWriter().write(obj.toString());
		response.getWriter().flush();
		response.getWriter().close();
	}
}
