package com.board.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;

import controller.CommandAction;

public class imgViewService implements CommandAction {

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("view들어왔따");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		System.out.println(idx);
		
		imgBoard_dao dao = new imgBoard_dao();
		imgBoard_entity dto = dao.getUserView(idx);
		
		
		HttpSession session = request.getSession();
		session.setAttribute("boardView", dto);
		session.setAttribute("idx", idx);
		
		
		return "replyList.do";
	}

}
