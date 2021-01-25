package com.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.login_dao;
import com.login.entity.login_entity;

import controller.CommandAction;

public class joinService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		////////////////////////////////////
		System.out.println("회원가입 service");
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
		
		if(checkentity==null) {
			int n = dao.getJoin(Inputentity);
			System.out.println("n : "+n);
			return "login.jsp";
		}else {
			System.out.println("회원가입실패");
			HttpSession session=request.getSession();
			String userExistChk="아이디가 중복됩니다.";
			session.setAttribute("UserExistMsg", userExistChk);
			return "/login/signUp.jsp";
		}
		
	}

}
