package naver.rlgns1129.oracleserver.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import naver.rlgns1129.oracleserver.dao.ItemDAO;
import naver.rlgns1129.oracleserver.domain.Item;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO itemDao;
	
	
	@Override
	public void list(HttpServletRequest request) {
		//매개변수로 페이지 번호가 온다고 가정
		String pageno = request.getParameter("pageno");
		//페이지 당 데이터 개수
		int cnt = 2;
		//시작번호
		
		//pageno : 자료가 10개가 있고 페이지당 데이터 개수가 2개라고 가정했을때 pageno는 5개된다.
		//pageno : 자료가 n개가 있고 페이지당 데이터 개수가 2개라고 가정했을때
		//9번이랑 10번 데이터를 보고싶으면 page 5를 입력야 9번이랑 10번 데이터를 볼수있다.
		//그러므로 pageno는 5가 된다.
		
		//start = (5)*2 - (2-1) = 9
		//end = (5) * 2 = 10
		
		int start = Integer.parseInt(pageno) * cnt - (cnt-1);
		int end = Integer.parseInt(pageno) * cnt;
		
		//DAO 의 파라미터 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		//DAO 의 메소드를 실행하고 결과를 저장
		List<Item> list = itemDao.list(map);
		request.setAttribute("list", list);
		
		
	}

	@Override
	public void detail(HttpServletRequest request) {
		String itemid = request.getParameter("itemid");
		Item item = itemDao.detail(Integer.parseInt(itemid));
		request.setAttribute("item", item);
		

	}

}
