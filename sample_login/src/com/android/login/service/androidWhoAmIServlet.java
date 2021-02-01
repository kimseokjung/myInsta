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


@WebServlet(name = "AndroidIdCheck", urlPatterns = { "/android_idcheck" })
public class androidWhoAmIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		////////////////////////////////////
		System.out.println("안드로이드 회원가입 아이디 체크 service");
		////////////////////////////////////
		
		login_entity Inputentity = new login_entity();
		login_dao dao = new login_dao();
		
		
		Inputentity.setUserid(request.getParameter("input_id"));

		
		login_entity checkentity = dao.existCheck(Inputentity.getUserid());
		
		JSONObject loginCk = new JSONObject();
		
		if(checkentity==null) {
			System.out.println("사용가능한 아이디");
			loginCk.put("result", "OK");
		}else {
			System.out.println("아이디 중복");
			loginCk.put("result", "NK");
		}
		loginCk.put("code", "101");// 아이디 체크에서 복귀

		response.setContentType("application/x-json; charset=UTF-8");
		System.out.println(loginCk.toString());
		response.getWriter().print(loginCk.toString());
		
		
	}

}
