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

@WebServlet(name = "androidProfileList", urlPatterns = { "/android_profile_list" })
public class androidProfileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android ProfileList Service Enter");
	////////////////////////////////////
	String userid = request.getParameter("userid");
	
	imgBoard_dao dao = new imgBoard_dao();
	List<imgBoard_entity> polist = dao.getProfileList(userid);
	
	
	JSONArray profileArr = new JSONArray();
	String count = "";
	String isChecked = "";
	

	if (polist != null) {
		System.out.println("OK Profile List");
		for (int i = 0; i < polist.size(); i++) {
			int idx = polist.get(i).getIdx();
			System.out.println("idx : "+idx);
			System.out.println("userid : "+userid);
			
			rec_entity entity = new rec_entity();
			entity.setIdx(idx);
			entity.setUserid(userid);
			//좋아요 카운트
			count = String.valueOf(dao.recCount(idx));
			//내가 좋아요를체크했니?
			isChecked = String.valueOf(dao.recCheck(entity));;
			
			
			JSONObject profileObj = new JSONObject();
			profileObj.put("idx", String.valueOf(polist.get(i).getIdx()));
			profileObj.put("userid",polist.get(i).getUserid());
			profileObj.put("imgPath",polist.get(i).getImgPath());
			profileObj.put("logtime",polist.get(i).getLogtime());
			profileObj.put("content",polist.get(i).getContent());
			profileObj.put("writeuserimg",polist.get(i).getWriteuserimg());
			profileObj.put("likeCount",count);
			profileObj.put("isChecked",isChecked);
			System.out.println(profileObj.toString());
			profileArr.add(profileObj);

		}

	}
	
	response.setContentType("application/x-json; charset=UTF-8");
	System.out.println(profileArr.toJSONString());
	response.getWriter().print(profileArr.toJSONString());

}

}
