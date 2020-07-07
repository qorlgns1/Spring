package kr.co.pk.item.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.pk.item.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = {"/", "index.html"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		//서비스의 메소드를 호출
		itemService.allItem(request, response);
		return "home";
	}
	
	@RequestMapping(value = {"detail/{itemid}"}, method = RequestMethod.GET)
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		//서비스의 메소드를 호출
		//System.out.println("item.itemcontroller.detail 요청 시작");
		itemService.detailItem(request, response);
		return "detail";
	}
}
