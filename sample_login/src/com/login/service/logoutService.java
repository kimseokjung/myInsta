package com.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.entity.login_entity;

import controller.CommandAction;

public class logoutService implements CommandAction {

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		login_entity entity = (login_entity)session.getAttribute("logOK");
		
		if(entity != null) {
			session.removeAttribute("logOK");//or session.invalidate();
			return "login.jsp";
		}
		return "login.jsp";
	}

}
