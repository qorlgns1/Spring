package naver.rlgns1129.oracleserver.dao;

import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.rlgns1129.oracleserver.domain.Member;



@Repository
public class MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public Member login(Member member) {
		return sqlSession.selectOne("member.login", member);
	}
	
}
