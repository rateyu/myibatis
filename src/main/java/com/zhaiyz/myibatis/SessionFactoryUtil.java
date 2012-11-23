/**
 * 
 */
package com.zhaiyz.myibatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author zhaiyz
 * 
 */
public final class SessionFactoryUtil {

	private static SqlSessionFactory sqlSessionFactory = null;

	private SessionFactoryUtil() {
	}

	static {
		String resource = "mybatis-configuration.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}

	public static void main(String[] args) {
		SqlSession ss = SessionFactoryUtil.getSqlSession();
		User user = (User) ss.selectOne("com.zhaiyz.myibatis.selectById", 1);
		System.out.println(user.getDegree());
		ss.close();
	}
}
