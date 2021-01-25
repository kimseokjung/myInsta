package com.board.service;


import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.login.dao.login_dao;
import com.login.entity.login_entity;

import controller.CommandAction;

public class profileListService implements CommandAction {

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String userid = request.getParameter("id");
		
		imgBoard_dao dao = new imgBoard_dao();
		List<imgBoard_entity> polist = dao.getProfileList(userid);
		
		request.setAttribute("polist", polist);
		
			return "/view/profile.jsp";
	}
}
