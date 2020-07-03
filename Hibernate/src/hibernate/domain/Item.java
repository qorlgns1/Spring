package hibernate.domain;

import java.util.Date;

import lombok.Data;
@Data
public class Item {
	private int code;
	private String name;
	private String manufacture;
	private int price;
	private Date builtdate;
}
