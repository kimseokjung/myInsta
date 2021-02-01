package com.android.board.service;

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
import com.login.dao.login_dao;
import com.login.entity.id_entity;
import com.login.entity.login_entity;
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

@WebServlet(name = "androidSearchList", urlPatterns = { "/android_search_list" })
public class androidSearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android SearchList Service Enter");
	////////////////////////////////////
		
	login_dao dao = new login_dao();
	List<id_entity> list = dao.getSearchUser();
	
	
	JSONArray searchArr = new JSONArray();
	
	if (list != null) {
		System.out.println("OK List");
		for (int i = 0; i < list.size(); i++) {
			JSONObject searchObj = new JSONObject();
			searchObj.put("userid",list.get(i).getUserid());
			searchObj.put("profileImg", list.get(i).getProfileimg());

			System.out.println(searchObj.toString());
		
			searchArr.add(searchObj);

		}

	}
	
	response.setContentType("application/x-json; charset=UTF-8");
	System.out.println(searchArr.toJSONString());
	response.getWriter().print(searchArr.toJSONString());

}

}
