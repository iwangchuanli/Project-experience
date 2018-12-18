package cn.e3mall.testgetperson;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUserExample;

public class Getperson {
	@Test
	public void getperson() {
		// 初始化spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-activemq.xml");
	}
}
