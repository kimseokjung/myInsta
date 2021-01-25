package com.android.board.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.board.entity.rec_entity;


@WebServlet(name = "Androidlike", urlPatterns = { "/android_like" })
public class androidLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		////////////////////////////////////
		System.out.println("Android Like Service Enter");
		////////////////////////////////////
		int idx= Integer.parseInt(request.getParameter("idx"));
		String userid=request.getParameter("userid");
		
		rec_entity recEntity= new rec_entity();
		recEntity.setIdx(idx);
		recEntity.setUserid(userid);
		
		imgBoard_dao imgDao = new imgBoard_dao();
		
		int n =imgDao.recCheck(recEntity);
		
		if(n ==1) {
			imgDao.recDelete(recEntity);
			System.out.println("좋아요 취소");
		}
		else {
			imgDao.recUpdate(recEntity);
			System.out.println("좋아요 등록");
		}
		
		
		int count =imgDao.recCount(idx);

		
		JSONObject likeObj = new JSONObject();
		likeObj.put("likeCount", String.valueOf(count));
		likeObj.put("isChecked", String.valueOf(n));
		likeObj.put("code", "504");//라이크에서 돌아옴
		
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(likeObj);
		
	}


}
