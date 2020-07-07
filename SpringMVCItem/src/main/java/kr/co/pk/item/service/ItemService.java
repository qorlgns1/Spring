package kr.co.pk.item.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서비스의 메소드는 매개변수를 다양하게 만들 수 있습니다.
//모든 곳에서 사용가능하도록 만들고자 하면 HttpServletRequest 와 HttpServletResponse를 갖도록 하면 되고
//상황에 따라 다르게 만들 수도 있습니다.
public interface ItemService {
	//전체 데이터를 가져오는 메소드
	public void allItem(HttpServletRequest request, HttpServletResponse response);
	//상세보기를 위한 메소드
	public void detailItem(HttpServletRequest request, HttpServletResponse response);
}
