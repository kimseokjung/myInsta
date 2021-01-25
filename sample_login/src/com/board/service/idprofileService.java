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

public class idprofileService implements CommandAction {

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		System.out.println("id프로필 서비스 들어옴");

		String userid = request.getParameter("id");
		System.out.println(userid);
		
		login_dao dao = new login_dao();
		String clickidProfileImg = dao.getClickidProfileImg(userid);
		
		imgBoard_dao boarddao = new imgBoard_dao();
		List<imgBoard_entity> list = boarddao.getClickidList(userid);

		request.setAttribute("clickidProfileImg", clickidProfileImg);
		request.setAttribute("list", list);
		return "/view/idprofile.jsp";
	}
}
