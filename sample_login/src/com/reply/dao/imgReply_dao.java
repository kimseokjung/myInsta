package com.reply.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.board.entity.imgBoard_entity;
import com.login.entity.login_entity;
import com.reply.entity.imgReply_entity;



public class imgReply_dao {
	
	private static SqlSessionFactory factory;
	
	//Mybatis연결객체 생성--------------------------------------
	static {
		try {
			String resource="mybatis/mybatis-config.xml";
			Reader reader=Resources.getResourceAsReader(resource);
			factory=new SqlSessionFactoryBuilder().build(reader);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	//List -----------------------------------------------------------------
	public List<imgReply_entity> getReplyList(int idx) {
		SqlSession session = factory.openSession();
		List<imgReply_entity> reList = session.selectList("mybatis.ReplyMapper.getRelpyList",idx);
		
		session.close();
		return reList;
	}
	
	//write -----------------------------------------------------------------
	public int replyInsert(imgReply_entity dto) {
		SqlSession session=factory.openSession();
		int n = 0;
		
		try {
			n = session.insert("mybatis.ReplyMapper.getInsertReply",dto);
			if(n > 0) {
				session.commit();
			}
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		System.out.println(" Reply Write OK");
		return n;
	}

	public int replyDelete(imgReply_entity entity) {
		SqlSession session=factory.openSession();
		int n = 0;
		
		try {
			n = session.delete("mybatis.ReplyMapper.getDeleteReply",entity);
			if(n > 0) {
				session.commit();
			}
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		System.out.println(" Reply Write OK");
		return n;
	}

	public int replyUpdate(imgReply_entity entity) {
		
		SqlSession session=factory.openSession();
		int n = 0;
		
		try {
			n = session.update("mybatis.ReplyMapper.getUpdateReply",entity);
			if(n > 0) {
				session.commit();
			}
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		return n;
	}






}
