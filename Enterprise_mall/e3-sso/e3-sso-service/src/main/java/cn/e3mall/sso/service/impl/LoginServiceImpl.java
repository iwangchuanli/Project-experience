package cn.e3mall.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.LoginService;

/**
 * <p>
 * Description: 用户登录相关处理
 * </p>
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;

	@Override
	public E3Result userLogin(String username, String password) {
		// 1、判断用户和密码是否正确
		// 根据用户名查询用户信息
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			// 返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
		// 2、如果能走到这里说明已经取用户信息
		TbUser user = list.get(0);
		// DigestUtils工具类的md5DigestAsHex方法：返回给定字节的MD5摘要的十六进制字符串表示形式。
		// 该方法需要传入的参数字节数组
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			// 返回登录失败
			return E3Result.build(400, "用户名或密码错误");
		}
		// 3、生成token信息。
		String token = UUID.randomUUID().toString();
		// 4、把用户信息写入redis，key：token value：用户信息
		// 保存用户信息到redis时，不应该保存用户的密码，所以这里设置用户密码为空
		user.setPassword(null);
		// 使用redis的string数据结构，前面可以加前缀便于区分
		jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(user));
		// 5、redis的expire()方法设置Session的过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		// 6、把token返回

		return E3Result.ok(token);
	}

}
