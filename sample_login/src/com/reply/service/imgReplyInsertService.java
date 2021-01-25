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

public class imgReplyInsertService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		System.out.println("reply insert enter");
		HttpSession session = request.getSession();
		int boardIdx = Integer.parseInt(request.getParameter("board_idx"));
		
		imgReply_entity dto = new imgReply_entity();
		dto.setBoardidx(boardIdx);
		dto.setWriteid(request.getParameter("write_user"));
		dto.setContent(request.getParameter("reply_write"));
		
		System.out.println("boardidx : "+dto.getBoardidx());
		System.out.println("writeid : "+dto.getWriteid());
		System.out.println("content : "+dto.getContent());
		
		imgReply_dao replyDao = new imgReply_dao();
		int n = replyDao.replyInsert(dto);
		
		if(n > 0) {
			request.setAttribute("replyinsertOK", n);
			return "boardView.do?idx="+boardIdx;
		}else {
			return "boardView.do?idx="+boardIdx;
		}
	}

}
