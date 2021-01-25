package com.board.service;

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


@WebServlet(name = "BoardAjax", urlPatterns = { "/boardAjax" })
public class boardAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		imgBoard_dao dao = new imgBoard_dao();
		
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		int total=dao.getTotalArticle();//총글수	
		System.out.println("total : "+total);
	
		int pageSize = 3;					//한 페이지에 출력할 게시물 
		int endNum = pg*pageSize;			// 끝번호 
		int startNum = endNum - (pageSize-1);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		List<imgBoard_entity> list = dao.getBoardList(map);
		System.out.println("list Size : "+list.size());
		
		JSONArray listObj = new JSONArray(); 

		for(int i = 0; i < list.size() ; i++) {    //게시판 글 수의 사이즈 만큼 담는다.
			int boardIdx = list.get(i).getIdx();
			String boardUserid = list.get(i).getUserid();
			String boardContent = list.get(i).getContent();
			String boardImgPath = list.get(i).getImgPath();
			String boardDate = list.get(i).getLogtime();
			String boardWriteuserimg = list.get(i).getWriteuserimg();
			
			JSONObject obj = new JSONObject();
			obj.put("boardIdx", boardIdx);
			obj.put("boardUserid", boardUserid);
			obj.put("boardContent", boardContent);
			obj.put("boardImgPath", boardImgPath);
			obj.put("boardDate", boardDate);
			obj.put("boardWriteuserimg", boardWriteuserimg);
			listObj.add(obj);
			System.out.println(listObj.get(i));
		}

		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(listObj);
		
		
		
		
	}

}
