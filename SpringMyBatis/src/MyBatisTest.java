

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//스프링 설정 파일의 내용을 실행시켜서 테스트하기 위해서 작성
//설정 파일의 경로만 변경해가면서 사용
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"applicationContext.xml"})
public class MyBatisTest {
	//동일한 자료형으로 만들어진 bean이 있으면 자동으로 주입받는
	//어노테이션으로 동일한 자료형의 bean이 없거나 2개이면 예외 발생
	//2개 이상 일 때는 아래에 name을 설정해서 name에 해당하는 것을 받아오게 됨
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void connectionTest() {
		try {
			Connection con = dataSource.getConnection();
			System.out.println(con);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
