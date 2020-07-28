package naver.rlgns1129.mysqlserver;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.rlgns1129.mysqlserver.service.MemberService;

@RestController
public class MemberDataController {
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="join", method=RequestMethod.POST)
	public Map<String, Object> join(MultipartHttpServletRequest request){
		
		memberService.join(request);
		
		Map<String, Object> map = (Map<String, Object>)request.getAttribute("result");
		
		return map;
	}

	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public Map<String, Object> login(HttpServletRequest request){
		
		memberService.login(request);
		
		Map<String, Object> map = (Map<String, Object>)request.getAttribute("result");
		
		return map;
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public Map<String, Object> update(MultipartHttpServletRequest request){
		
		memberService.update(request);
		
		Map<String, Object> map = (Map<String, Object>)request.getAttribute("result");
		
		return map;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public Map<String, Object> delete(HttpServletRequest request){
		
		memberService.delete(request);
		
		Map<String, Object> map = (Map<String, Object>)request.getAttribute("result");
		
		return map;
	}
	
}
