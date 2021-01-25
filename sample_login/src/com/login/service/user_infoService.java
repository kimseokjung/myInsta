package com.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.login_dao;
import com.login.entity.login_entity;

import controller.CommandAction;

public class user_infoService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("정보 변경 서비스");
		
		login_entity Changeentity = new login_entity();
		login_dao dao = new login_dao();
		
		HttpSession session = request.getSession();
		login_entity logentity = (login_entity)session.getAttribute("logOK");
		
		Changeentity.setUserid(logentity.getUserid());
		Changeentity.setPwd(request.getParameter("change_pwd"));
		Changeentity.setName(logentity.getName());
		Changeentity.setEmail(request.getParameter("change_email"));
		Changeentity.setAddress(request.getParameter("change_address"));
		Changeentity.setProfileimg(logentity.getProfileimg());
		Changeentity.setChk(logentity.getChk());
		
		session.removeAttribute("logOK");
		
		
		session.setAttribute("logOK", Changeentity);
		login_entity changeentity = (login_entity)session.getAttribute("logOK");
		
		int n = dao.infoChange(Changeentity);
		
		if(n > 0) {
			return "view/post.jsp";
		}else {
			return "login/user_info.jsp";
		}
		
	}

}
