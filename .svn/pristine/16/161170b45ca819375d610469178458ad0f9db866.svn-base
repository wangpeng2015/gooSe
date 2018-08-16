package cpm.spring;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJunitTest {
	
	@Test
	public void test(){
		
		ClassPathXmlApplicationContext xmlContext=new ClassPathXmlApplicationContext(
				"classpath:/spring/spring-core.xml",
				"classpath:/spring/spring-mybatis.xml",
				"classpath:/spring/spring-aop.xml");
		
		xmlContext.start();
	}

}
