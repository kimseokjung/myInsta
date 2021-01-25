package com.login.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.login_dao;
import com.login.entity.id_entity;
import com.login.entity.login_entity;

import controller.CommandAction;

public class loginService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("loging service enter");
		
		String id=request.getParameter("input_id");
		String pwd=request.getParameter("input_pwd");
		
		login_dao dao = new login_dao();
		login_entity entity = dao.getUser(id,pwd);
		List<id_entity> idlist = dao.getidList();
		
		HttpSession session=request.getSession();
		
		if(entity != null) {
			//세션설정
			System.out.println("로그인성공");
			session.setAttribute("logOK", entity);  //로그인 세션 생성
			System.out.println(entity.getEmail());
			System.out.println(entity.getAddress());
			if (idlist != null) {
				session.setAttribute("idlist", idlist); 
			}
			return "boardList.do";
		}else {
			String noUser="아이디 비밀번호를 다시 확인해주세요.";
			session.setAttribute("errMsg", noUser); 
			return "login.jsp";
		}
		
		
		
	}

	
	
	
}
