package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.GoodDao;

//bean을 자동생성해 달라고 하는 어노테이션
@Service
public class GoodServiceImpl implements GoodService {
	//DAO 변수를 선언
	//setter를 만들어주는 기능과 동일한 자료형의 bean을 주입받아주는 어노테이션
	@Autowired
	private GoodDao goodDao;

	@Override
	public void detail() {
		System.out.println(goodDao.detail(1));
		
	}

}
