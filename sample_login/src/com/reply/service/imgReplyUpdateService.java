package com.reply.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

import controller.CommandAction;

public class imgReplyUpdateService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		System.out.println("reply update enter");
		
		String content = request.getParameter("content");
		int board_idx = Integer.parseInt(request.getParameter("idx"));
		int reply_idx = Integer.parseInt(request.getParameter("replyidx"));
		
		imgReply_dao dao = new imgReply_dao();
		imgReply_entity entity = new imgReply_entity();
		
		entity.setBoardidx(board_idx);
		entity.setContent(content);
		entity.setIdx(reply_idx);
		
		int n = dao.replyUpdate(entity);
		System.out.println("n :::"+n);
		if(n>0) {
			return "boardView.do?idx="+board_idx;
		}else {
			
		}
		return "boardView.do?idx="+board_idx;
//		imgBoard_entity dto = new imgBoard_entity();
//		dto.setIdx(Integer.parseInt(request.getParameter("idx")));
//		dto.setUserid(request.getParameter("userid"));
//	//	dto.setTitle(request.getParameter("title"));
//		dto.setContent(request.getParameter("content"));
//		
//		imgBoard_dao boardDAO = new imgBoard_dao();
//		int n =0;
//				 boardDAO.boardUpdate(dto);
//		
//		if(n > 0) {
//			request.setAttribute("updateOK", n);
//			return "boardList.do";
//		}else {
//			return "write/user_write.jsp";
//		}
	}

}
