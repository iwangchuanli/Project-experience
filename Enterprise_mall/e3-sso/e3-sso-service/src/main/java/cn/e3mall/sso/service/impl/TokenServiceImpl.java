package cn.e3mall.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;

/**
 * <p>Description: 根据token取用户信息</p>
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private JedisClient jedisClient;
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	
	/**
	 * 根据token获取用户信息
	 */
	@Override
	public E3Result getUserByToken(String token) {
		//根据token到redis中取用户信息
		String json = jedisClient.get("SESSION:" + token);
		//通过StringUtils工具类的isBlank方法判断取到的用户信息是否为空
		//如果json为空说明没有取到用户信息，说明用户登录已经过去（redis中通过expire方法设置了过期时间）
		if (StringUtils.isBlank(json)) {
			return E3Result.build(201, "用户登录已经过期");
		}
		//取到用户信息的话就通过redis的expire()方法更新token的过期时间
		jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
		//返回结果，E3Result其中包含TbUser对象（将取到的json数据转换为pojo对象）
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		return E3Result.ok(user);
	}

}
