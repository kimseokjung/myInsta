package com.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;

import controller.CommandAction;

public class imgDeleteService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("delete enter");
		
		int seq = Integer.parseInt(request.getParameter("idx"));
		System.out.println("idx: "+seq);
				
		imgBoard_dao dao = new imgBoard_dao();
		dao.boardDelete(seq);
					
		return "write/delete.jsp";
		
	}

}
