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


@WebServlet(name = "AndroidJoin", urlPatterns = { "/android_join" })
public class androidJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		////////////////////////////////////
		System.out.println("안드로이드 회원가입 service");
		////////////////////////////////////
		
		login_entity Inputentity = new login_entity();
		login_dao dao = new login_dao();
		
		
		Inputentity.setUserid(request.getParameter("input_id"));
		Inputentity.setPwd(request.getParameter("input_pwd"));
		Inputentity.setName(request.getParameter("input_name"));
		Inputentity.setEmail(request.getParameter("input_email"));
		Inputentity.setAddress(request.getParameter("input_address"));
		Inputentity.setChk(request.getParameter("input_chk"));
		
		
		login_entity checkentity = dao.existCheck(Inputentity.getUserid());
		
		JSONObject loginCk = new JSONObject();
		
		if(checkentity==null) {
			int n = dao.getJoin(Inputentity);
			if(n > 0) {
				System.out.println("회원가입 성공");
				loginCk.put("result", "OK");
				loginCk.put("code", "1"); // 성공 코드
				
			}else {
				System.out.println("회원가입 실패");
				loginCk.put("result", "NK");
				loginCk.put("code", "2000"); // Mapper 실패
			}
		}else {
			System.out.println("회원가입 실패");
			loginCk.put("result", "NK");
			loginCk.put("code", "1000");//아이디 중복 코드 
		}

		response.setContentType("application/x-json; charset=UTF-8");
		System.out.println(loginCk.toString());
		response.getWriter().print(loginCk.toString());
		
		
		
		
	}

}
