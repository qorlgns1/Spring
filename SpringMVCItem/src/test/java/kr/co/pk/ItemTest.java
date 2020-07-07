package kr.co.pk;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//설정 파일을 읽어오는 코드
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ItemTest {
	@Autowired
	private DataSource dataSource;
	
	//MtBatis 사용 클래스를 주입
	@Autowired
	private SqlSession sqlSession;
	
	//MyBatis 설정 정보를 테스트
	@Test
	public void mybatisTest() {
		System.out.println(sqlSession);
	}
	
	@Test
	public void detailItem() {
		System.out.println(sqlSession.selectOne("item.detailitem",1));
	}
	
	//데이터베이스 연결을 테스트
	@Test
	public void connectTest() {
		
		try {
			System.out.println(dataSource.getConnection());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
