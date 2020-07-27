package naver.rlgns1129.oracleserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import naver.rlgns1129.oracleserver.domain.Item;
import naver.rlgns1129.oracleserver.domain.Member;
import naver.rlgns1129.oracleserver.service.MemberService;

@RestController
public class MemberDataController {
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="login")
	public Map<String, Object> login(HttpServletRequest request){
		//서비스 메소드를 실행
		memberService.login(request);
		Member member = (Member)request.getAttribute("result");
/*
//		나 혼자 연습!(그런데 틀림)		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map = (Map<String, Object>)request.getAttribute("map");
				
//		return map;
*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//어떤 작업 수행의 결과가 null 일 수 있는 경우는
		//다른 키를 하나 추가해서 null 여부를 판단할 수 있도록 해줍니다.
		
		if(member == null) {
			map.put("login", false);
		}else {
			map.put("login", true);
			map.put("result", member);
		}
		return map;
	}
}
