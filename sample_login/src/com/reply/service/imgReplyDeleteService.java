package com.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.login.entity.login_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

import controller.CommandAction;

public class imgReplyDeleteService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		System.out.println("reply delete enter");
			
		imgReply_entity entity = new imgReply_entity();
		entity.setBoardidx(Integer.parseInt(request.getParameter("idx")));
		entity.setIdx(Integer.parseInt(request.getParameter("replyidx")));
		
		imgReply_dao dao = new imgReply_dao();
		int n = dao.replyDelete(entity);
		
		return "boardView.do?idx="+entity.getBoardidx();
	}

}
