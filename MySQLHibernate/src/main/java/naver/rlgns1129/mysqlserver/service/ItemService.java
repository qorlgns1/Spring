package naver.rlgns1129.mysqlserver.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ItemService {
	//검색어를 가지고 검색하는 메소드
	public void list(HttpServletRequest request);
	//itemid를 이용해서 하나의 데이터를 가져오는 메소드
	public void detail(HttpServletRequest request);
	//데이터를 삽입하는 메소드
	//파일을 업로드 할 때는 MultipartHttpServletRequest로 사용해야함
	//일반 HttpServletRequest로는 사용 불가
	public void insert(MultipartHttpServletRequest request);
}
