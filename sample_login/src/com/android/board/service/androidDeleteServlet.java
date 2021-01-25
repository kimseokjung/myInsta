package com.android.board.service;

import java.io.IOException;
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
import com.login.dao.login_dao;
import com.login.entity.login_entity;


@WebServlet(name = "AndroidBoardDelete", urlPatterns = { "/android_board_delete" })
public class androidDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		////////////////////////////////////
		System.out.println("Android Board Delete Service Enter");
		////////////////////////////////////
		
		int seq = Integer.parseInt(request.getParameter("idx"));
		System.out.println("idx: "+seq);
				
		imgBoard_dao dao = new imgBoard_dao();
		int n = dao.boardDelete(seq);
		
		JSONObject obj = new JSONObject();
		
		if(n > 0) {
			System.out.println("글 삭제 완료!!");
			obj.put("code",	"505"); //글삭제 코드
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(obj.toJSONString());
		}           	
		
	}

}
