package kr.co.pk.item.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.pk.item.dao.ItemDAO;
import kr.co.pk.item.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO itemDao;

	@Override
	public void allItem(HttpServletRequest request, HttpServletResponse response) {
		//1. 파라미터 읽기
		
		//2. 필요한 처리 수행
		
		//3. DAO 메소드의 매개변수 생성
		
		//4. DAO 메소드를 호출
		List<Item> list = itemDao.allItem();
		//5. 결과를 가공
		
		//6. 결과를 저장 - REST API Server의 경우는 request에 저장
		request.setAttribute("list", list);
		
		
		
	}

	@Override
	public void detailItem(HttpServletRequest request, HttpServletResponse response) {
		//요청 주소의 마지막 부분을 가져오기
		//localhost/detail/itemid
		//System.out.println("item.serviceImpl.detailItem 요청 도달");
		String requestURI = request.getRequestURI();
		String [] ar = requestURI.split("/");
		String itemid = ar[ar.length-1];
		//System.out.println("item.serviceImpl.detailItem itemid변수" + itemid);
		Item item = itemDao.detailItem(Integer.parseInt(itemid));
		//결과를 저장
		request.setAttribute("item", item);
		
		
		
	}
}
