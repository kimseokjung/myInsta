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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.board.entity.rec_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

@WebServlet(name = "androidReplyList", urlPatterns = { "/android_reply_list" })
public class androidReplyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android Reply Service Enter");
	////////////////////////////////////
	
	int idx = Integer.parseInt(request.getParameter("idx"));
	
	imgReply_dao dao = new imgReply_dao();
	List<imgReply_entity> reList = dao.getReplyList(idx);
	JSONArray replyArr = new JSONArray();
	
	if (reList != null) {
		System.out.println("OK Reply List");
		
		for (int i = 0; i < reList.size(); i++) {
			JSONObject replyObj = new JSONObject();
			replyObj.put("replyIdx", reList.get(i).getIdx());
			replyObj.put("userid", reList.get(i).getWriteid());
			replyObj.put("content", reList.get(i).getContent());
			replyObj.put("boardIdx", reList.get(i).getBoardidx());
			replyArr.add(replyObj);
		
		}
	}
	
	response.setContentType("application/x-json; charset=UTF-8");
	System.out.println(replyArr.toJSONString());
	response.getWriter().print(replyArr.toJSONString());

}

}
