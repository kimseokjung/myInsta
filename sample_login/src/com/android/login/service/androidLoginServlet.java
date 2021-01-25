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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.login.dao.login_dao;
import com.login.entity.login_entity;


@WebServlet(name = "AndroidLogin", urlPatterns = { "/android_login" })
public class androidLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "unused" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		////////////////////////////////////
		System.out.println("Android Login Service Enter");
		////////////////////////////////////
		
		
		String id = request.getParameter("input_id");
		String pwd = request.getParameter("input_pwd");
		
		System.out.println("id : "+id);
		
		login_dao dao = new login_dao();
		login_entity entity = dao.getUser(id,pwd);
		
		
		
		
		JSONObject loginCk = new JSONObject();
		
		if(entity != null) {
			//로그인 성공
			System.out.println("entity : "+entity.getUserid());
			System.out.println("entity : "+entity.getProfileimg());
			System.out.println("로그인 성공");
			loginCk.put("result", "OK");
			loginCk.put("userId", id);
			loginCk.put("code", "1");
			loginCk.put("profileImg", entity.getProfileimg());
			
		}else {
			//로그인 실패
			System.out.println("로그인 실패");
			loginCk.put("result", "NK");
			loginCk.put("code", "1000"); // 아이디 비번 다름
		}

		response.setContentType("application/x-json; charset=UTF-8");
		System.out.println(loginCk.toString());
		response.getWriter().print(loginCk.toString());
		
		
		
		
	}

}
