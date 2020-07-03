package hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import hibernate.domain.Item;

@Repository
public class ItemDAO {
	@Autowired
	//하이버네이트 사용 객체
	private SessionFactory sessionFactory;
	
	//데이터 삽입
	@Transactional
	public void insert() {
		Item item = new Item();
		item.setCode(3);
		item.setName("전복");
		item.setManufacture("완도");
		item.setPrice(10000);
		item.setBuiltdate(new Date());
		
		sessionFactory.getCurrentSession().save(item);
	}
	
	@Transactional
	public void update() {
		Item item = new Item();
		item.setCode(3);
		item.setName("포도");
		item.setManufacture("음성");
		item.setPrice(3000);
		item.setBuiltdate(new Date());
		
		sessionFactory.getCurrentSession().update(item);
	}
	
	@Transactional
	public void delete() {
		Item item = new Item();
		item.setCode(3);
		sessionFactory.getCurrentSession().delete(item);
	}
	
	//전체 데이터 가져오기
	@Transactional
	public void list() {
		//Criteria 사용
		//List<Item> list =(List<Item>)sessionFactory.getCurrentSession().createCriteria(Item.class).list();
				
		//SQL 사용
		List<Item> list = sessionFactory.getCurrentSession().createSQLQuery("select * from item").addEntity(Item.class).list();
		
		//데이터 출력
		for(Item item : list) {
			System.out.println(item);
		}
	}
	
	//기본키를 가지고 데이터 1개 가져오기
	@Transactional
	public void getItem() {
		//Criteria 사용
		Item item = sessionFactory.getCurrentSession().get(Item.class, 1);
		System.out.println(item);
				
	
		}
	}
