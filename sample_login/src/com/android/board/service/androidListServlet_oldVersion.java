package com.android.board.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;

@WebServlet(name = "androidBoardList1", urlPatterns = { "/android_board_list1" })
public class androidListServlet_oldVersion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android BoardList Service Enter");
	////////////////////////////////////
	
	imgBoard_dao dao = new imgBoard_dao();
	
	int total=dao.getTotalArticle();//총글수	
	System.out.println("total : "+total);

	
	List<imgBoard_entity> list = dao.getFindMyBoardList();
	
	JSONArray boardArr = new JSONArray();

	if (list != null) {
		System.out.println("OK List");
		for (int i = 0; i < list.size(); i++) {
			JSONObject boardObj = new JSONObject();
			boardObj.put("idx", String.valueOf(list.get(i).getIdx()));
			boardObj.put("userid",list.get(i).getUserid());
			boardObj.put("imgPath",list.get(i).getImgPath());
			boardObj.put("logtime",list.get(i).getLogtime());
			boardObj.put("content",list.get(i).getContent());
			boardObj.put("writeuserimg",list.get(i).getWriteuserimg());
			System.out.println(boardObj.toString());
			boardArr.add(boardObj);

		}

	}
	
	response.setContentType("application/x-json; charset=UTF-8");
	System.out.println(boardArr.toJSONString());
	response.getWriter().print(boardArr.toJSONString());

}

}
