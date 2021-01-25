package com.reply.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

import controller.CommandAction;

public class imgReplyListService implements CommandAction {

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		HttpSession session = request.getSession();
		int idx = (Integer)session.getAttribute("idx");
		
		imgReply_dao dao = new imgReply_dao();
		List<imgReply_entity> reList = dao.getReplyList(idx);
		
		request.setAttribute("Replylist", reList);
		
		System.out.println("reply list load OK");
		
		System.out.println("여기서 이상해진다 개빡치게");
		
		return "view/post.jsp";
	}

}
