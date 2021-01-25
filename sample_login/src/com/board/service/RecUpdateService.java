package com.board.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.board.entity.rec_entity;


@WebServlet(name = "RecUpdateService_name", urlPatterns = { "/RecUpdateService_url" })
public class RecUpdateService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(count);
		
	}


}
