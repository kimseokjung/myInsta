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


@WebServlet(name = "AndroidWhoAmI", urlPatterns = { "/android_who_am_i" })
public class androidChkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		////////////////////////////////////
		System.out.println("안드로이드 회원정보 수정 service");
		////////////////////////////////////
		
		login_entity entity = new login_entity();
		login_dao dao = new login_dao();
		
		
		entity.setUserid(request.getParameter("userid"));
		entity.setPwd(request.getParameter("pwd"));

		System.out.println(""+request.getParameter("userid"));
		System.out.println(""+request.getParameter("pwd"));
		login_entity checkentity = dao.findUserInfo(entity);
		
		
		JSONObject userInfo = new JSONObject();
		if(checkentity != null) {
			System.out.println("사용가능한 아이디");
			userInfo.put("result", "OK");
			userInfo.put("userid", checkentity.getUserid());
			userInfo.put("name", checkentity.getName());
			userInfo.put("email", checkentity.getEmail());
			userInfo.put("addr", checkentity.getAddress());
			userInfo.put("chk", checkentity.getChk());
		}else {
			System.out.println("찾을 수 없습니다.");
			userInfo.put("result", "NK");
		}
		userInfo.put("code", "101");// 아이디 체크에서 복귀

		response.setContentType("application/x-json; charset=UTF-8");
		System.out.println(userInfo.toString());
		response.getWriter().print(userInfo.toString());
		
		
	}

}
