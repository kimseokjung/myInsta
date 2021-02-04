package com.follow.android;

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
import com.follow.follow_dao;
import com.follow.follow_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

@WebServlet(name = "androidFollowdelete", urlPatterns = { "/android_follow_delete" })
public class androidFollowDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android Follow Delete Service Enter");
	////////////////////////////////////
	
	follow_entity dto = new follow_entity();
	dto.setTarget(request.getParameter("target"));
	dto.setUserid(request.getParameter("userid"));
	
	System.out.println("target : "+dto.getTarget());
	System.out.println("userid : "+dto.getUserid());
	
	follow_dao dao = new follow_dao();
	int n = dao.deleteFollow(dto);
	
	JSONObject obj = new JSONObject();	
	if(n > 0) {
		System.out.println("언팔로우!!!");
		obj.put("result", "OK");
	}else {
		obj.put("result", "NK");
	}
	
	obj.put("code", "401");
	
	response.setContentType("application/x-json; charset=UTF-8");
	System.out.println(obj.toJSONString());
	response.getWriter().print(obj.toJSONString());
	}
}


