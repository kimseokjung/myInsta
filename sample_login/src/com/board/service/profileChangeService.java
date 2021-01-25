package com.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.login.dao.login_dao;
import com.login.entity.login_entity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.CommandAction;

public class profileChangeService implements CommandAction{


	@Override
	public String requestPro_action(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("profileChange in");
		
		//실제 저장 경로
		String realFolder = request.getServletContext().getRealPath("/profile_img");
		//System.out.println("저장 폴더:" + realFolder);
		
		//파일업로드
		MultipartRequest multi=new MultipartRequest(
		        request, realFolder, 5*1024*1024, "UTF-8",new DefaultFileRenamePolicy());
		HttpSession session = request.getSession();
		login_entity entity = (login_entity)session.getAttribute("logOK");
		imgBoard_dao boardDao= new imgBoard_dao();
		
		System.out.println(multi.getFilesystemName("imgpath"));
		String unimg = "unimg.jpg";
		if(multi.getFilesystemName("imgpath") == null) {
			entity.setProfileimg(unimg);
		}else {
			entity.setProfileimg(multi.getFilesystemName("imgpath"));
		}
		
		System.out.println(entity.getUserid());
		System.out.println(entity.getProfileimg());
	      
		login_dao loginDao = new login_dao();
		int n = loginDao.profileimgInsert(entity);
		
		if(n > 0) {
			boardDao.getBoardChangeWriteProtile(entity); //프로필 이미지 수정 보드에 전체 수정
			return "profileList.do?id="+entity.getUserid();
		}
		return "/view/profile.jsp";
	}

//	http://localhost:8080/insta/profileList.do?id=hi 여기로 와야함
//	http://localhost:8080/insta/myProfile.do
}
