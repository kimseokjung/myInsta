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
import com.reply.dao.imgReply_dao;
import com.reply.entity.imgReply_entity;

@WebServlet(name = "androidBoardList", urlPatterns = { "/android_board_list" })
public class androidListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android BoardList Service Enter");
	////////////////////////////////////
	String userid = request.getParameter("userid");
	
	int pg = Integer.parseInt(request.getParameter("pg"));
	
	int pageSize = 5;					//한 페이지에 출력할 게시물 
	int endNum = pg*pageSize;			// 끝번호 
	int startNum = endNum - (pageSize-1);
	
	imgBoard_dao dao = new imgBoard_dao();
	
	int total=dao.getTotalArticle();//총글수	
	System.out.println("total : "+total);
	
	Map<String, Integer> map = new HashMap<>(); // 페이징 처리
	map.put("startNum", startNum);
	map.put("endNum", endNum);
	List<imgBoard_entity> list = dao.getBoardList(map);
	
	
	JSONArray boardArr = new JSONArray();
	String count = "";
	String isChecked = "";
	

	if (list != null) {
		System.out.println("OK List");
		for (int i = 0; i < list.size(); i++) {
			int idx = list.get(i).getIdx();
			System.out.println("idx : "+idx);
			System.out.println("userid : "+userid);
			
			rec_entity entity = new rec_entity();
			entity.setIdx(idx);
			entity.setUserid(userid);
			//좋아요 카운트
			count = String.valueOf(dao.recCount(idx));
			//내가 좋아요를체크했니?
			isChecked = String.valueOf(dao.recCheck(entity));;
			//리플레이 리스트
			imgReply_dao reDao = new imgReply_dao();
			List<imgReply_entity> reList = reDao.getReplyList(idx);
			JSONArray replyArr = new JSONArray();
			if (reList != null) {
				System.out.println("OK Reply List");
				
				for (int j = 0; j < reList.size(); j++) {
					JSONObject replyObj = new JSONObject();
					replyObj.put("replyIdx", reList.get(j).getIdx());
					replyObj.put("userid", reList.get(j).getWriteid());
					replyObj.put("content", reList.get(j).getContent());
					replyObj.put("boardIdx", reList.get(j).getBoardidx());
					replyArr.add(replyObj);
				}
			}
			
			JSONObject boardObj = new JSONObject();
			boardObj.put("idx", String.valueOf(list.get(i).getIdx()));
			boardObj.put("userid",list.get(i).getUserid());
			boardObj.put("imgPath",list.get(i).getImgPath());
			boardObj.put("logtime",list.get(i).getLogtime());
			boardObj.put("content",list.get(i).getContent());
			boardObj.put("writeuserimg",list.get(i).getWriteuserimg());
			boardObj.put("likeCount",count);
			boardObj.put("isChecked",isChecked);
			boardObj.put("reply",replyArr);
			System.out.println(boardObj.toString());
		
			boardArr.add(boardObj);

		}

	}
	
	response.setContentType("application/x-json; charset=UTF-8");
	System.out.println(boardArr.toJSONString());
	response.getWriter().print(boardArr.toJSONString());

}

}
