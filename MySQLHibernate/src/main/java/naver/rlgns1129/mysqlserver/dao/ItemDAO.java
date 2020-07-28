package naver.rlgns1129.mysqlserver.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import naver.rlgns1129.mysqlserver.domain.Item;

@Repository
public class ItemDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	//검색 항목과 검색어 그리고 페이지 번호를 이용해서 해당하는 데이터를 목록으로 리턴하는 메소드
	//페이징을 할 때 오라클은 시작번호와 종료번호를 알아야 하지만
	//MySQL은 시작번호와 데이터 개수를 알아야 합니다.
	//오라클은 1부터 시작하지만 MySQL은 0부터 시작합니다.
	//검색항목과 검색어 그리고 시작번호 페이지당 데이터 개수를
	//하나의 Map으로 묶어서 사용
	
	public List<Item> list(Map<String, Object> map){
		//결과를 저장할 list
		List<Item> list = new ArrayList<>();
		
		//검색항목 - serchtype, 검색어 - value
		//시작번호 - start 페이지 당 데이터 개수 - size
		
		//파라미터 읽기
		String searchtype = (String)map.get("searchtype");
		String value = (String)map.get("value");
		int start = (Integer)map.get("start");
		int size = (Integer)map.get("size");
		
		if(value != null) {
			//like 검색을 하고자 하는 경우
			//대소문자 구분하지 않고 검색하기 위해서 toLowerCase 사용
			value= "%" + value.toLowerCase() + "%";
		}
		
		//검색 항목이 없는 경우
		//itemname에서 검색, description에서 검색
		//itemname 과 description에서 검색
		if(searchtype == null) {
			//select * from item limit 0,2
			list = sessionFactory.getCurrentSession().createNativeQuery("select * from item limit " + start + "," + size ).getResultList();
		}else if(searchtype.equals("itemname")) {
			//select * from item where lower(itemname) like '포도' limit 0,2)
			list = sessionFactory.getCurrentSession().createNativeQuery("select * from item where lower(itemname) like \'" + value + "\' limit " + start + "," + size).getResultList();
		}else if(searchtype.equals("description")) {
			//select * from item where lower(description) like '맛있는 포도' limit 0,2)
			list = sessionFactory.getCurrentSession().createNativeQuery("select * from item where lower(description) like \'" + value + "\' limit " + start + "," + size).getResultList();
		}else if(searchtype.equals("both")) {
			//select * from item where lower(description) like '맛있는 포도' or lower(itemname) like '포도' limit 0,2)
			list = sessionFactory.getCurrentSession().createNativeQuery("select * from item where lower(description) like \'" + value + " " + " or lower(itemname) like \'" + value + "\' limit " + start + "," + size).getResultList();
		}
		
		return list;
	}
	
	//검색 결과의 데이터 개수를 리턴하는 메소드
	//하이버네이트에서는 정수를 리턴할 때는 BigInteger로 리턴됩니다.
	
	public BigInteger count(Map<String, Object> map) {
		//파라미터 읽기
		String searchtype = (String)map.get("searchtype");
		String value = (String)map.get("value");
		
		List<BigInteger> list = null;
		
		if(value != null) {
			//like 검색을 하고자 하는 경우
			//대소문자 구분하지 않고 검색하기 위해서 toLowerCase 사용
			value= "%" + value.toLowerCase() + "%";
		}
		
		if(searchtype == null) {
			//select count(*) from item
			list = sessionFactory.getCurrentSession().createNativeQuery("select count(*) from item").getResultList();
		}else if(searchtype.equals("itemname")) {
			//select count(*) from item where itemname like '%포도%'
			list = sessionFactory.getCurrentSession().createNativeQuery("select count(*) from item where lower(itemname) like \'" + value + "\'").getResultList();
		}else if(searchtype.equals("description")) {
			//select count(*) from item where itemname like '%맛있는 포도%'
			list = sessionFactory.getCurrentSession().createNativeQuery("select count(*) from item where lower(description) like \'" + value + "\'").getResultList();
		}else if(searchtype.equals("both")) {
			//select count(*) from item where lower(description) like '맛있는 포도' or lower(itemname) like '포도'
			list = sessionFactory.getCurrentSession().createNativeQuery("select count(*) from item where lower(description) like \'" + value + "\'" + " or lower(itemname) like \'" + value + "\'").getResultList();
		}
		
		return list.get(0);
		
	}
	
	//itemid를 가지고 데이터 1개를 찾아오는 메소드
	//itemid가 기본키일 경우에만 사용.
	public Item detail(int itemid) {
		Item item = sessionFactory.getCurrentSession().get(Item.class, itemid);
		
		return item;										
	}
	
	//가장 큰 itemid를 찾아오는 메소드
	public int maxid() {
		List<Integer> list = sessionFactory.getCurrentSession().createNativeQuery("select max(itemid) from item").getResultList();
		
		return list.get(0);
	}
	
	//데이터를 삽입하는 메소드
	public void insert(Item item) {
		sessionFactory.getCurrentSession().save(item);
	}
	
	
	
}
