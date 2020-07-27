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
import naver.rlgns1129.oracleserver.service.ItemService;

@RestController
public class ItemDataController {

	@Autowired
	private ItemService itemService;
	//테스트 하는 방법
	//http://localhost:9000/oracleserver/list?pageno=1
	//위에 oracleserver/list?pageno=1 중 list는 아래 value의 list
	@RequestMapping(value="list" , method=RequestMethod.GET)
	public Map<String, Object> list(HttpServletRequest request){
		//서비스 메소드를 실행
		itemService.list(request);
		Map<String, Object> map = new HashMap<String, Object>();
		//데이터를 가져와서 map에 출력
		List<Item> list = (List<Item>)request.getAttribute("list");
		map.put("list", list);
		
		return map;
	}
	//테스트 하는 방법
	//http://localhost:9000/oracleserver/detail?itemid=1
	//위에 oracleserver/detail?itemid=1 중 detail는 아래 value의 detail
	@RequestMapping(value="detail" , method=RequestMethod.GET)
	public Map<String, Object> detail(HttpServletRequest request){
		itemService.detail(request);
		Item item = (Item)request.getAttribute("item");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item", item);
		return map;
	}
	
	
	
}
