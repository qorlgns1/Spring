import java.sql.Connection;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.support.GenericXmlApplicationContext;

import mybatis.dao.GoodDAO;
import mybatis.dao.GoodMapper;
import mybatis.domain.Good;

public class Main {

	public static void main(String[] args) {
//		try {
//			GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//			
////데이터베이스 연동 테스트			
////			DataSource dataSource = context.getBean(DataSource.class);
////			Connection con = dataSource.getConnection();
////			System.out.println(con);
//			
//			
////sqlSession 테스트			
////			SqlSession sqlSession = context.getBean(SqlSession.class);
////			System.out.println(sqlSession);
//			
//			GoodDAO goodDAO = context.getBean(GoodDAO.class);
//			
//			//전체 데이터 가져오기
//			//System.out.println(goodDAO.allGood());
//			
//			//코드로 데이터 가져오기
//			//System.out.println(goodDAO.getGood(2));
//			
////			//삽입할 데이터를 생성
////			Good good = new Good();
////			good.setCode(3);
////			good.setName("수박");
////			good.setRegdate(new Date());
////			System.out.println(goodDAO.insertGood(good));
//			
////			//수정
////			Good good = new Good();
////			good.setCode(10);
////			good.setName("낑깡");
////			good.setRegdate(new Date());
////			System.out.println(goodDAO.updateGood(good));
//			
//			
//			//삭제
//			//System.out.println(goodDAO.deleteGood(3));
//			
//			//System.out.println(goodDAO.searchName("수"));
//			
//			//삽입할 데이터를 생성
//			Good good = new Good();
//			good.setCode(8);
//			good.setName("파인애플");
//			good.setRegdate(new Date());
//			System.out.println(goodDAO.insertGood(good));
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
		
		
		try {
			GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//			//데이터베이스 접속 정보 가져오기
//			DataSource ds = context.getBean(DataSource.class);
//			Connection con = ds.getConnection();
//			System.out.println(con);
			
			GoodMapper goodMapper = context.getBean(GoodMapper.class);
			System.out.println(goodMapper.allGood());
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
	}

}
}