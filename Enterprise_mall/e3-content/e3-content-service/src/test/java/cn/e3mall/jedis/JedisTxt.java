package cn.e3mall.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTxt {

	@Test
	public void testJedis() throws Exception {
		// 创建一个连接Jedis对象，参数：host、port
		Jedis jedis = new Jedis("192.168.25.130", 6379);
		// 直接使用jedis操作redis。所有jedis的命令都对应一个方法。
		jedis.set("bwcx9393", "欢迎关注微信公众号：Java面试通关手册");
		String string = jedis.get("bwcx9393");
		System.out.println(string);//输出内容：欢迎关注微信公众号：Java面试通关手册
		// 关闭连接
		jedis.close();
		
	}

	@Test
	public void testJedisPool() throws Exception {
		// 创建一个连接池对象，两个参数host、port
		JedisPool jedisPool = new JedisPool("192.168.25.130", 6379);
		// 从连接池获得一个连接，就是一个jedis对象。
		Jedis jedis = jedisPool.getResource();
		// 使用jedis操作redis
		String string = jedis.get("bwcx9393");
		System.out.println(string);//输出内容：欢迎关注微信公众号：Java面试通关手册
		// 关闭连接，每次使用完毕后关闭连接。连接池回收资源。
		jedis.close();
		// 关闭连接池。
		jedisPool.close();
	}

	@Test
	public void testJedisCluster() throws Exception {
		// 创建一个JedisCluster对象。有一个参数nodes是一个set类型。set中包含若干个HostAndPort对象。
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.130", 7001));
		nodes.add(new HostAndPort("192.168.25.130", 7002));
		nodes.add(new HostAndPort("192.168.25.130", 7003));
		nodes.add(new HostAndPort("192.168.25.130", 7004));
		nodes.add(new HostAndPort("192.168.25.130", 7005));
		nodes.add(new HostAndPort("192.168.25.130", 7006));
		JedisCluster jedisCluster = new JedisCluster(nodes);
		// 直接使用JedisCluster对象操作redis。
		jedisCluster.set("test", "123");
		String string = jedisCluster.get("test");
		System.out.println(string);
		// 关闭JedisCluster对象
		jedisCluster.close();
	}

	@Test
	public void hello() {
		System.out.println("hello");
	}
}
