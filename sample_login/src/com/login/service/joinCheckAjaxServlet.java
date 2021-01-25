package com.login.service;

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


@WebServlet(name = "JoinCheckAjax", urlPatterns = { "/joinCheckAjax" })
public class joinCheckAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("id check enter");
		String id = request.getParameter("input_id");
		
		login_dao loginDao= new login_dao();
		
		String chkUserid = loginDao.getUserCheck(id);
		
		System.out.println("check : "+chkUserid);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(chkUserid);
		
		
		
	}

}
