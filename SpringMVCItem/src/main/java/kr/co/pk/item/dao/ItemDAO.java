package kr.co.pk.item.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.pk.item.domain.Item;

@Repository
public class ItemDAO {
	@Autowired
	// MyBatis를 xml로 이용할 때 사용하는 클래스
	private SqlSession sqlSession;

	public List<Item> allItem() {
		return sqlSession.selectList("item.allitem");
	}

	public Item detailItem(Integer itemid) {
		return sqlSession.selectOne("item.detailitem", itemid);
	}
}
