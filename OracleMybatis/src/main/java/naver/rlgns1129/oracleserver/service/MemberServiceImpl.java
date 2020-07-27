package naver.rlgns1129.oracleserver.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.rlgns1129.oracleserver.dao.MemberDAO;
import naver.rlgns1129.oracleserver.domain.Member;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDao;
	
	@Override
	public void login(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//DAO의 매개변수 만들
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		
		//DAO의 메소드를 호출
		Member result = memberDao.login(member);
		
		//result가 null이면 로그인 실패 그렇지 않으면 로그인 성공
	
/*		나 혼자 연습해봤지만 틀림!
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", result.getId());
//		map.put("pw", result.getPw());
		
//		request.setAttribute("map", map);
*/		
		request.setAttribute("result", result);
	}

}
