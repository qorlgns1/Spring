package naver.rlgns1129.mysqlserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.rlgns1129.mysqlserver.domain.Item;
import naver.rlgns1129.mysqlserver.service.ItemService;

@RestController
public class ItemDataController {
	@Autowired
	private ItemService itemService;
	
	//검색해서 데이터를 전송하는 요청을 생성
	@RequestMapping(value="list")
	public Map<String, Object> list(HttpServletRequest request){
		itemService.list(request);
		List<Item> list = (List<Item>)request.getAttribute("list");
		int count = (Integer)request.getAttribute("count");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", count);
		map.put("list", list);
	
		
		return map;
	}
	

	@RequestMapping(value="detail")
	public Map<String, Object> detail(HttpServletRequest request){
		itemService.detail(request);
		Item item = (Item)request.getAttribute("item");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item", item);	
		
		return map;
	}
	//데이터를 입력받아서 수행하는 처리는 method를 POST로 설정
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public Map<String, Object> insert(MultipartHttpServletRequest request){
		
		//데이터를 삽입하는 메소드를 호출
		
		itemService.insert(request);
		Boolean result = (Boolean)request.getAttribute("insert");
		
		Map<String, Object> map = new HashMap<String, Object>();
		//삽입에 성공하면 result는 true 그렇지 않으면 false
		if(result != null) {
			map.put("result", result);
		}else {
			map.put("result", false);
		}
		return map;
	}
	
	
	
}
