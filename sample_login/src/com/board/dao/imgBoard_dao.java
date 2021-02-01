package com.board.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.board.entity.imgBoard_entity;
import com.board.entity.rec_entity;
import com.login.entity.login_entity;



public class imgBoard_dao {
	
	private static SqlSessionFactory factory;
	private static imgBoard_dao instance = new imgBoard_dao();
	
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
	public static imgBoard_dao getInstance() {
		return instance;
	}

	
	//write -----------------------------------------------------------------
	
	public int boardInsert(imgBoard_entity entity) {
		SqlSession session=factory.openSession();
		int n=0;
		try{
			n=session.insert("mybatis.BoardMapper.boardInsert", entity);
			if(n > 0)
				session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
		return n;
	}
	
	//modify -----------------------------------------------------------------

	public imgBoard_entity getBoard(int seq) {
		SqlSession session = factory.openSession();
		imgBoard_entity entity = session.selectOne("mybatis.BoardMapper.getBoard",seq);
		System.out.println("entity : : :" + entity);
		session.close();
		return entity;
	}

	//delete -----------------------------------------------------------------
	
	public int boardDelete(int seq) {
		SqlSession session = factory.openSession();
		int n=0;
		System.out.println("delete mon in");
		try{
			n=session.delete("mybatis.BoardMapper.boardDelete",seq);
			System.out.println("n::::"+n);
			if(n > 0)
				session.commit();
			}catch(Exception e) {
				e.printStackTrace();
				session.rollback();
			}finally {
				session.close();
			}
		return n;
		
	}
	
	//List -----------------------------------------------------------------
	public List<imgBoard_entity> getBoardList(Map<String, Integer> map) { // 이름 변경함 확인후 주석 삭제
		SqlSession session = factory.openSession();
		List<imgBoard_entity> list = session.selectList("mybatis.BoardMapper.getBoardList",map);
		
		session.close();
		return list;
	}

	//View -----------------------------------------------------------------
	public imgBoard_entity getUserView(int idx) {
		SqlSession session = factory.openSession();
		imgBoard_entity dto = session.selectOne("mybatis.BoardMapper.getBoardView",idx);
		
		session.close();
		return dto;
	}
	
	//Update -----------------------------------------------------------------
	public void boardUpdate(imgBoard_entity dto) {
		SqlSession session=factory.openSession();
		int n=0;
		System.out.println("update dao in");
		try {
			System.out.println("try in");
			n = session.update("mybatis.BoardMapper.getUpdateUser",dto);
			System.out.println("try out");
			if(n>0) {
				session.commit();
			}
		}catch(Exception e) {
			System.out.println("catch");
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}

	}

	//-----------------------------------------------------------------
	public List<imgBoard_entity> getProfileList(String userid) {
		SqlSession session = factory.openSession();
		List<imgBoard_entity> polist = session.selectList("mybatis.BoardMapper.getProfileList",userid);
		
		session.close();
		return polist;
	}

	//-----------------------------------------------------------------
	public List<imgBoard_entity> getClickidList(String userid) {
		SqlSession session = factory.openSession();
		List<imgBoard_entity> list = session.selectList("mybatis.BoardMapper.getidList",userid);
		return list;
	}

	//board list total check-----------------------------------------------------------------
	public int getTotalArticle() { //총 게시물 수 
		SqlSession session = factory.openSession();
		int n = session.selectOne("mybatis.BoardMapper.getTotalArticle");
		
		session.close();
		return n;
	}

	public void getBoardChangeWriteProtile(login_entity entity) {
		SqlSession session=factory.openSession();
		int n=0;
		System.out.println("update profile img dao in");
		try {
			System.out.println("try in");
			n = session.update("mybatis.BoardMapper.getBoardChangeWriteProtile",entity);
			System.out.println("try out");
			if(n>0) {
				session.commit();
			}
		}catch(Exception e) {
			System.out.println("catch");
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
	}
	
	//RecUpdate-----------------------------------------------------------------

	public void recUpdate(rec_entity rec_entity) {
		SqlSession session = factory.openSession();
		int n = 0;
		try {
			n = session.insert("mybatis.BoardMapper.rec_update", rec_entity);
			if (n > 0)
				session.commit();
		} catch (Exception e) {
			System.out.println("recUpdate : " + e);
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
	//RecDelete-----------------------------------------------------------------

	public void recDelete(rec_entity rec_entity) {
		SqlSession session = factory.openSession();
		int n = 0;
		try {
			n = session.delete("mybatis.BoardMapper.rec_delete", rec_entity);
			if (n > 0)
				session.commit();
		} catch (Exception e) {
			System.out.println("recUpdate : " + e);
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

	}
	//RecCheck-----------------------------------------------------------------

	public int recCheck(rec_entity rec_entity) {
		SqlSession session = factory.openSession();
		int result = 0;
		System.out.println("dao : "+rec_entity.getIdx());
		try {
			result = (Integer) session.selectOne("mybatis.BoardMapper.rec_check", rec_entity);
		} catch (Exception e) {
			System.out.println("recCheck : " + e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}
	//RecCount-----------------------------------------------------------------

	public int recCount(int idx) {
		SqlSession session = factory.openSession();
		int result = 0;
		System.out.println("count init : "+idx);
		try {
			result = session.selectOne("mybatis.BoardMapper.rec_count", idx);
		} catch (Exception e) {
			System.out.println("recCheck : " + e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return result;
	}
	
	//Android List -----------------------------------------------------------------
	public List<imgBoard_entity> getFindMyBoardList() { // 이름 변경함 확인후 주석 삭제
		SqlSession session = factory.openSession();
		List<imgBoard_entity> list = session.selectList("mybatis.BoardMapper.getFindMyBoardList");
		
		session.close();
		return list;
	}


	
	
	
	
	
	
	

}
