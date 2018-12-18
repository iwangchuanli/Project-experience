package cn.e3mall.jedis;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Txt {
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	/**
	 * 该测试启动之后就会初始化一个spring容器
	 * 测试发布服务，保持该测试方法运行，即使没有把e3-manager-service运行在服务器
	 *e3-manager-web依然能够接收到该service层提供的服务
	 * @throws IOException 
	 */	
	@Test
	public void publishService() throws InterruptedException, IOException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
       System.out.println("e3-manager-service服务已经启动，输入任何内容即可结束服务");
       System.in.read();
       System.out.println("服务关闭~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
}

