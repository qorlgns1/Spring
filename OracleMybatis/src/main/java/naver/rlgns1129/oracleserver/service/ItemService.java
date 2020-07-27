package naver.rlgns1129.oracleserver.service;

import javax.servlet.http.HttpServletRequest;

public interface ItemService {
	public void list(HttpServletRequest request);
	public void detail(HttpServletRequest request);
}
