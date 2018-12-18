package cn.e3mall.common.jedis;

import java.util.List;

/**
 * <p>
 * redis数据库相关操作的接口。这个接口主要是为了方便我们在项目中实现redis单机版与集群版的无缝切换。 （因为我们只需要注入一个接口就好）
 * </p>
 * 
 * @author Snailclimb
 * @version 1.0
 */
public interface JedisClient {
	// String数据类型的相关处理接口
	// 保存数据
	String set(String key, String value);

	// 获取数据
	String get(String key);

	// 判断某个key是否存在
	Boolean exists(String key);

	// 设置过期时间的接口
	Long expire(String key, int seconds);

	Long ttl(String key);

	Long incr(String key);

	// Hash数据类型的相关处理接口
	Long hset(String key, String field, String value);

	String hget(String key, String field);

	Long hdel(String key, String... field);

	Boolean hexists(String key, String field);

	List<String> hvals(String key);

	Long del(String key);
}
