package naver.rlgns1129.oracleserver.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.rlgns1129.oracleserver.domain.Item;

@Repository
public class ItemDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//데이터 번호를 받아서 번호에 해당하는 데이터를 가져오는 메소드
	public List<Item> list(Map map){
		return sqlSession.selectList("item.list", map);
	}
	
	//itemid를 받아서 1개의 데이터 가져오는 메소드
	public Item detail(Integer itemid){
		return sqlSession.selectOne("item.detail", itemid);
	}
	
}
