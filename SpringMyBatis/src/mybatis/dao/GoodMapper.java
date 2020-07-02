package mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mybatis.domain.Good;

//MyBatis Mapper로 사용할 인터페이스
public interface GoodMapper {
	//전체 데이터를 가져오는 메소드
	@Select("select code, name, regdate from goods")
	public List<Good> allGood();
	
	//code를 가지고 하나의 데이터를 찾아오는 메소드
	@Select("select code, name, regdaate from goods where code=#{code}")
	public Good getGood(int code);
	
	//데이터를 삽입하는 메소드
	@Insert("insert into goods(code, name, regdate) values(#{code},#{name},#{regdate})")
	public int insertGood(Good good);
	
	//데이터를 수정하는 메소드
	@Update("update goods set name = #{name}, regdate = #{regdate} where code = #{code}")
	public int updateGood(Good good);
	
	//데이터를 삭제하는 메소드
	@Delete("delete from goods where code=#{code}")
	public int deleteGood(int code);
}
