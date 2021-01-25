package com.android.board.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
import com.login.entity.login_entity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet(name = "androidBoardInsert", urlPatterns = { "/android_board_insert" })
public class androidInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	////////////////////////////////////
	System.out.println("Android Board Insert Service Enter");
	////////////////////////////////////
	try {
		
	//실제 저장 경로
	String realFolder = request.getServletContext().getRealPath("/storage");
	System.out.println("저장 폴더:" + realFolder);
	
//	//파일업로드
	MultipartRequest multi=new MultipartRequest(
	        request, realFolder, 10*1024*1024, "UTF-8",new DefaultFileRenamePolicy());
	System.out.println("name "+multi.getFilesystemName("files"));
	System.out.println("name "+multi.getParameter("files"));
	
	Enumeration files = multi.getFileNames();
	String item=(String) files.nextElement(); 
	System.out.println(item); //filenm

	imgBoard_entity entity = new imgBoard_entity();
	entity.setUserid(multi.getParameter("userid"));
	entity.setWriteuserimg(multi.getParameter("profileImg")); //보드에 표시할 유저프로필 이미
	entity.setContent(multi.getParameter("content"));
	entity.setImgPath(multi.getFilesystemName("files"));
	
	System.out.println(entity.getUserid());
	System.out.println(entity.getContent());

	
	imgBoard_dao boardDao = new imgBoard_dao();
	int n = boardDao.boardInsert(entity);
	
	if(n > 0) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("result", String.valueOf(n));
		response.setContentType("application/x-json; charset=UTF-8");
		System.out.println("데이터 저장완료!!" + jsonObj.toJSONString());
		response.getWriter().print(jsonObj.toJSONString());
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	

}

}
