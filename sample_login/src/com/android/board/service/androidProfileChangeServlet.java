package com.android.board.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.board.dao.imgBoard_dao;
import com.board.entity.imgBoard_entity;
import com.board.entity.rec_entity;
import com.login.dao.login_dao;
import com.login.entity.login_entity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet(name = "Androidlike", urlPatterns = { "/android_like" })
public class androidProfileChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		////////////////////////////////////
		System.out.println("Android Profile Img Change Service Enter");
		////////////////////////////////////
		int idx= Integer.parseInt(request.getParameter("idx"));
		String userid=request.getParameter("userid");
		
		
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
		}
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(n);
		
	}


}
