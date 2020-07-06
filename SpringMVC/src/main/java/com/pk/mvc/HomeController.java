package com.pk.mvc;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pk.mvc.domain.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "index.html"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	//form 요청을 GET으로 전송했을 때 처리할 메소드
	@RequestMapping(value = "form", method = RequestMethod.GET)
	public String form() {
		//단순 페이지 이동은 jsp 파일의 이름만 리턴하면 됩니다.
		return "form";
	}
	
//	@RequestMapping(value = "form", method = RequestMethod.POST)
//	//기존의 HttpServletRequest를 이용한 파라미터 처리를 위한 메소드
//	public String form (HttpServletRequest request){
//		String name = request.getParameter("name");
//		String phone = request.getParameter("phone");
//		String age = request.getParameter("age");
//		
//		//출력
//		System.out.println("이름: " + name);
//		System.out.println("전화번호: " + phone);
//		System.out.println("나이: " + age);
//		
//		return "result";
//	}
	
//	@RequestMapping(value = "form", method = RequestMethod.POST)
//	//@RequestParam을 이용한 파라미터 처리를 위한 메소드
//	//Spring에서 파라미터 처리를 할 때 많이 사용하는 방법
//	//동일한 이름의 파라미터가 여러 개 있으면 List나 배열로 받는 것도 가능
//	public String form (
//			@RequestParam("name") String name,
//			@RequestParam("phone") String phone,
//			@RequestParam("age") String age){
//		
//		//출력
//		System.out.println("이름: " + name);
//		System.out.println("전화번호: " + phone);
//		System.out.println("나이: " + age);
//		
//		return "result";
//	}
	
	@RequestMapping(value = "form", method = RequestMethod.POST)
	//Command 객체를 이용하는 방법
	public String form (User user){
		//출력
		System.out.println(user);
		return "result";
	}
	
	@RequestMapping(value = "detail/{num}", method = RequestMethod.GET)
	//경로를 변수로 이용하고자 할 때 읽어내는 방법 : 최근의 블로그형 게시판에 이용
	public String detail(@PathVariable("num")int num){
		//출력
		System.out.println(num);
		return "result";
	}
}
