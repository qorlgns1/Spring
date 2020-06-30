package dao;

import org.springframework.stereotype.Repository;

import domain.Good;

//bean을 자동생성해 달라고 하는 어노테이션
@Repository
public class GoodDao {
	//메소드
	//상세보기를 위한 메소드
	//기본키를 매개변수로 받아서 하나의 DTO를 생성해서 리턴
	public Good detail(int num) {
		Good good = new Good();
		good.setNum(1);
		good.setName("망고");
		return good;
	}
}
