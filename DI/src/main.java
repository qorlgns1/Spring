import org.springframework.context.support.GenericXmlApplicationContext;

import controller.GoodController;
import domain.Good;

public class main {

	public static void main(String[] args) {

		GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		
		GoodController goodController = context.getBean(GoodController.class);
		goodController.detail();
		
		context.close();
		
		
		
		
		
		
	}

}
