package naver.rlgns1129.mysqlserver.service;

import java.io.FileOutputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import naver.rlgns1129.mysqlserver.dao.ItemDAO;
import naver.rlgns1129.mysqlserver.domain.Item;
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDAO itemDao;

	@Override
	@Transactional
	public void list(HttpServletRequest request) {
		//파라미터 읽기
		String searchtype = request.getParameter("searchtype");
		String value = request.getParameter("value");
		String pageno = request.getParameter("pageno");
		System.out.println("itemserviceimpl.list.searchtype : " + searchtype);
		System.out.println("itemserviceimpl.list.value : " + value);
		System.out.println("itemserviceimpl.list.pageno : " + pageno);
		
		//작업을 수행
		//한 페이지에 보여질 데이터 개수
		int size = 3;
		//시작 위치 번호를 저장할 변수
		//MySQL은 데이터 번호가 0부터 시작합니다.
		int start = 0;
		if(pageno != null) {
			start = (Integer.parseInt(pageno)-1) *size;
		}
		
		//DAO 메소드의 파라미터를 만들기
		Map<String, Object> map = new HashMap<>();
		map.put("searchtype", searchtype);
		map.put("value", value);
		map.put("start", start);
		map.put("size", size);

		//DAO 메소드를 호출해서 결과를 저장
		List<Item> list = itemDao.list(map);
		request.setAttribute("list", list);
		int count = itemDao.count(map).intValue();
		request.setAttribute("count", count);
			
		
	}

	@Override
	@Transactional
	public void detail(HttpServletRequest request) {
		String itemid = request.getParameter("itemid");
		System.out.println("itemserviceimpl.detail.itemid : " + itemid);
		Item item = itemDao.detail(Integer.parseInt(itemid));
		System.out.println("itemserviceimpl.detail.item : " + item);
		request.setAttribute("item", item);
	}

	@Override
	@Transactional
	public void insert(MultipartHttpServletRequest request) {
		//itemid, itemname, price, description
		//pictureurl을 만들어서 데이터를 삽입
		int itemid = 1;
		//데이터 개수 가져오기
		int count = itemDao.count(
				new HashMap<String, Object>())
				.intValue();
		//데이터가 존재하면 가장 큰 itemid의 값에 +1
		if(count != 0) {
			itemid = itemDao.maxid() + 1;
		}
		
		String itemname = request.getParameter("itemname");
		int price = Integer.parseInt(request.getParameter("price"));
		String description = request.getParameter("description");
		
		System.out.println("ItemServiceImpl.insert.itemname : " + itemname);
		System.out.println("ItemServiceImpl.insert.price : " + price);
		System.out.println("ItemServiceImpl.insert.description : " + description);
		
		//파일의 기본값을 설정
		String pictureurl = "default.jpg";
		//파일 파라미터 가져오기
		MultipartFile image = 
				request.getFile("pictureurl");
		//전송된 파일이 존재하면 
		if(image != null && image.isEmpty() == false) {
			//파일을 저장할 디렉토리 경로 가져오기
			String filePath = request.getRealPath("/img");
			//새로운 파일명 만들기
			pictureurl = UUID.randomUUID() + 
					image.getOriginalFilename();
			//실제 파일 경로 만들기
			filePath = filePath + "/" + pictureurl;
			try {
				//파일을 기록할 출력 스트림 생성
				FileOutputStream fos = 
						new FileOutputStream(filePath);
				//파일 업로
				fos.write(image.getBytes());
				fos.close();
			}catch(Exception e) {
				System.out.println(
					"파일 저장 예외:" + e.getMessage());
			}
			
			Item item = new Item();
			item.setItemid(itemid);
			item.setItemname(itemname);
			item.setPrice(price);
			item.setDescription(description);
			item.setPictureurl(pictureurl);
			
			itemDao.insert(item);
			
			request.setAttribute("insert", true);
			
		}

	}


}
