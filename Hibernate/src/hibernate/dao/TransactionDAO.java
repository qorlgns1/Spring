package hibernate.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionDAO {
	//Spring JDBC 에서 제공하는 데이터 삽입 클래스의 변수
	@Autowired
	private SimpleJdbcInsert template;
	
	@Transactional
	public void insert() {
		//테이블 이름 설정
		template.withTableName("GOODS");
		//삽입할 데이터 생성
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 12);
		map.put("name", "오이");
		map.put("regdate", new Date());
		
		//동일한 데이터를 2번 삽입하므로 2번째 삽입할 때
		//기본키 중복 예외 발생
		template.execute(map);
		template.execute(map);
		}
}
