package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.GoodService;

@Controller
public class GoodController {
	//Service 인스턴스를 저장할 변수를 생성
	@Autowired
	private GoodService goodService;
	
	public void detail() {
		goodService.detail();
	}
}
