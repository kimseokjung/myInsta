package com.android.login.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.login.dao.login_dao;
import com.login.entity.login_entity;


@WebServlet(name = "AndroidChangeUserInfo", urlPatterns = { "/android_change_user_info" })
public class androidUserInfoChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		////////////////////////////////////
		System.out.println("안드로이드 회원정보 수정 service");
		////////////////////////////////////
		boolean isNotPw = false;
		
		String userid = request.getParameter("userid");
		String pwd = "";
		if(request.getParameter("pwd").equalsIgnoreCase("nope"))
			isNotPw = true;
		else
			pwd = request.getParameter("pwd");
		
		String name = request.getParameter("nicName");
		String email = request.getParameter("email");
		String add = request.getParameter("add");
		
		login_entity changeEntity = new login_entity();
		login_dao dao = new login_dao();
		
		changeEntity.setUserid(userid);
		if(!isNotPw) {
			changeEntity.setPwd(pwd);
		}
		changeEntity.setName(name);
		changeEntity.setEmail(email);
		changeEntity.setAddress(add);
			
		int n = dao.androidUserInfoChange(changeEntity);
		
		
		JSONObject obj = new JSONObject();
		response.setContentType("application/x-json; charset=UTF-8");
		obj.put("code", "505");
		if(n > 0) {
			obj.put("result", "OK");
			System.out.println(obj.toString());
			response.getWriter().print(obj.toString());
			
		}else {
			obj.put("result", "NK");
			System.out.println(obj.toString());
			response.getWriter().print(obj.toString());
		}
		
	}

}
