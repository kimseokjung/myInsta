package com.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.login.entity.login_entity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandAction;

public class imgInsertService implements CommandAction{


	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("insert enter");
		
		//실제 저장 경로
		String realFolder = request.getServletContext().getRealPath("/storage");
		//System.out.println("저장 폴더:" + realFolder);
		
		//파일업로드
		MultipartRequest multi=new MultipartRequest(
		        request, realFolder, 5*1024*1024, "UTF-8",new DefaultFileRenamePolicy());
		
		//세션 오픈 한다음 login entity사용가능하게 만듬
		HttpSession session = request.getSession();
		login_entity logentity = (login_entity)session.getAttribute("logOK");
		
		imgBoard_entity entity = new imgBoard_entity();
		entity.setUserid(logentity.getUserid());
		entity.setWriteuserimg(logentity.getProfileimg()); //보드에 표시할 유저프로필 이미
		entity.setContent(multi.getParameter("content"));
		entity.setImgPath(multi.getFilesystemName("imgpath"));
		
		System.out.println(entity.getUserid());
		System.out.println(entity.getContent());
		
	      
		imgBoard_dao boardDao = new imgBoard_dao();
		int n = boardDao.boardInsert(entity);
		
		if(n > 0) {
			request.setAttribute("insertOK", n);
			return "boardList.do";
		}else {
			return "write/user_write.jsp";
		}
	}

}
