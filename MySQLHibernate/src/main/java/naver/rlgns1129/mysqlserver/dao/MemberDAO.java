package naver.rlgns1129.mysqlserver.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.rlgns1129.mysqlserver.domain.Member;

@Repository
public class MemberDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// email 중복검사를 위한 메소드
	public List<String> emailCheck(){
		List<String> list = sessionFactory.getCurrentSession().createNativeQuery("select email from member").getResultList();
			
		return list;
	}
	
	// nickname 중복검사를 위한 메소드
	public List<String> nicknameCheck(String nickname){
		List<String> list = sessionFactory.getCurrentSession().createNativeQuery("select nickname from member where nickname = \'" + nickname + "\'").getResultList();
			
		return list;
	}
	
	//회원가입을 위한 메소드
	public void join(Member member) {
		sessionFactory.getCurrentSession().save(member);
	}
	
	//로그인을 위한 메소드
	//nickname 과 pw를 가지고 로그인
	//nickname을 가지고 모든 정보를 전부 찾아가면 됩니다.
	public List<Member> login(String nickname) {
		List<Member> list = sessionFactory.getCurrentSession().createNativeQuery("select nickname, pw, email, profile from member where nickname= \'" + nickname + "\'", Member.class).getResultList();
	
		return list;
	} 
		
	//회원정보를 수정하는 메소드
	public void update(Member member) {
		//다른 SQL 작업과 혼합이 되는 경우 한꺼번에 수행할 때는
		//update 대신에 merge를 사용하며
		//없으면 저장하고 있으면 수정하고자 하는 경우에는 saveOrUpdate
		sessionFactory.getCurrentSession().merge(member);
	}
		
	public void delete(Member member) {
		//이전에 수행중인 내용을 전부 삭제하고 작업을 수행
		sessionFactory.getCurrentSession().clear();
		sessionFactory.getCurrentSession().delete(member);
	}
		
		
		
}




