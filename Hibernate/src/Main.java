import java.sql.Connection;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import hibernate.dao.ItemDAO;
import hibernate.dao.TransactionDAO;

public class Main {

	public static void main(String[] args) {
		try {
			GenericXmlApplicationContext context = 
				new GenericXmlApplicationContext(
					"applicationContext.xml");
			/*
			DataSource ds = 
				context.getBean(DataSource.class);
			Connection con = ds.getConnection();
			System.out.println(con);
			*/
			
//			//DAO 인스턴스를 가져와서 삽입하는 메소드 호출
//			TransactionDAO dao = 
//				context.getBean(TransactionDAO.class);
//			dao.insert();
			
//			//하이버네이트 설정 확인
//			SessionFactory sessionFactory = context.getBean(SessionFactory.class);
//			System.out.println(sessionFactory);
			
			ItemDAO itemDao = context.getBean(ItemDAO.class);
			//itemDao.insert();
			//itemDao.update();
			//itemDao.delete();
			//itemDao.list();
			itemDao.getItem();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
