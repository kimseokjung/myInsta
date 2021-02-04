package com.follow;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.board.entity.imgBoard_entity;
import com.login.entity.id_entity;
import com.login.entity.login_entity;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp.Factory;

public class follow_dao {

	private static SqlSessionFactory factory;
	static {
		try {
			String resource = "mybatis/mybatis-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
		}
	}

	// 팔로잉 -----------------------------------------------------------------

	public List<follow_entity> getFollowing(String userid) {
		SqlSession session = factory.openSession();

		List<follow_entity> list = session.selectList("mybatis.FollowMapper.following", userid);
		session.close();

		return list;
	}
	// 팔로워 -----------------------------------------------------------------

	public List<follow_entity> getFollower(String target) {
		SqlSession session = factory.openSession();

		List<follow_entity> list = session.selectList("mybatis.FollowMapper.follower", target);
		session.close();

		return list;
	}
	
	// 팔로잉 등록  -----------------------------------------------------------------
	public int addFollow(follow_entity entity) {

		
		SqlSession session = factory.openSession();
		int n = 0;
		try {
			n = session.insert("mybatis.FollowMapper.addFollow", entity);
			if (n > 0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		return n;
	}
	
	//언팔로우 -----------------------------------------------------------------
	
	public int deleteFollow(follow_entity entity) {
		SqlSession session = factory.openSession();
		
		int n=0;
		System.out.println("unfollow in");
		try{
			n=session.delete("mybatis.FollowMapper.deleteFollow",entity);
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

}
