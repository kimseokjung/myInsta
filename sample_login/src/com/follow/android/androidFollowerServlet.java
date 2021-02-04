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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.board.entity.rec_entity;
import com.follow.follow_dao;
import com.follow.follow_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

@WebServlet(name = "androidFollower", urlPatterns = { "/android_follower" })
public class androidFollowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android Follower Service Enter");
	////////////////////////////////////
	
	String target = request.getParameter("userid");
	
	follow_dao dao = new follow_dao();
	List<follow_entity> List = dao.getFollower(target);
	
	JSONObject followerO = new JSONObject();
	JSONArray fArr = new JSONArray();
	
	if (List != null) {
		System.out.println("OK Follower List");
		
		for (int i = 0; i < List.size(); i++) {
			JSONObject Obj = new JSONObject();
			Obj.put("target", List.get(i).getTarget());
			Obj.put("userid", List.get(i).getUserid());
			fArr.add(Obj);
		}
		followerO.put("code", "601");
		followerO.put("result", "OK");
		followerO.put("list", fArr);
	}else {
		followerO.put("code", "601");
		followerO.put("result", "NK");
	}
	
	response.setContentType("application/x-json; charset=UTF-8");
	System.out.println(followerO.toJSONString());
	response.getWriter().print(followerO.toJSONString());

}

}
