package com.android.reply.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.board.entity.rec_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

@WebServlet(name = "androidReplyInsert", urlPatterns = { "/android_reply_insert" })
public class androidReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android Reply Insert Service Enter");
	////////////////////////////////////
	
	imgReply_entity dto = new imgReply_entity();
	dto.setBoardidx(Integer.parseInt(request.getParameter("idx")));
	dto.setWriteid(request.getParameter("write_user"));
	dto.setContent(request.getParameter("content"));
	
	System.out.println("boardidx : "+dto.getBoardidx());
	System.out.println("writeid : "+dto.getWriteid());
	System.out.println("content : "+dto.getContent());
	
	imgReply_dao replyDao = new imgReply_dao();
	int n = replyDao.replyInsert(dto);
	
	if(n > 0) {
		System.out.println("댓글 등록 완료!!");
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(n);
	}
	}

}
