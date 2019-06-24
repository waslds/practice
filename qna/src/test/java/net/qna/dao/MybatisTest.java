package net.qna.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import net.qna.domain.users.User;

public class MybatisTest {
	private static final Logger log = LoggerFactory.getLogger(MybatisTest.class);
	private SqlSessionFactory sqlSessionFactory;

	@Before
	public void setup() throws IOException {
		String resource = "mybatis-config-test.xml"; 
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("qna.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		log.info("database initialized success!");
	}
	
	private DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/qna-user");
		dataSource.setUsername("sa");
		return dataSource;
	}

	@Test
	public void testGettingStarted() throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne("UserMapper.findById", "javajigi");
			log.debug("User : {}", user);
		}
	}

	@Test
	public void testInsert() throws Exception {
		try (SqlSession session = sqlSessionFactory.openSession()) {
			User user = new User("sanjigi", "password", "산지기", "sanjigi@slipp.net");
			session.insert("UserMapper.create", user);
			User actual = session.selectOne("UserMapper.findById", user.getUserId());
			assertThat(actual, is(user));
		}
	}
}
