package com.board.service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandAction;

public class imgUpdateService implements CommandAction{

	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		System.out.println("update enter");
	
		//실제 저장 경로
		String realFolder = request.getServletContext().getRealPath("/storage");
		MultipartRequest multi=new MultipartRequest(
		        request, realFolder, 5*1024*1024, "UTF-8",new DefaultFileRenamePolicy());
		
		
		imgBoard_entity dto = new imgBoard_entity();
		dto.setIdx(Integer.parseInt(multi.getParameter("idx")));
		dto.setUserid(multi.getParameter("userid"));
		dto.setContent(multi.getParameter("content"));
		if(multi.getFilesystemName("imgpath") == null) {
			System.out.println("이미지가 업따?");
			dto.setImgPath(multi.getParameter("myimgpath"));
		}else {
			System.out.println("이미지가 이따");
			dto.setImgPath(multi.getFilesystemName("imgpath"));
		}
		dto.setLogtime(multi.getParameter("logtime"));
		
		
		System.out.println("aaaaaa :"+dto.getContent()+","+dto.getImgPath()+","+dto.getIdx()+","+dto.getLogtime());
		imgBoard_dao dao = new imgBoard_dao(); 
		dao.boardUpdate(dto);
		
		
		return "boardView.do?idx="+dto.getIdx();
	}

}
