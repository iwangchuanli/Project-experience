package cn.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import javax.imageio.spi.ServiceRegistry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.mapper.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.RegisterService;

/**
 * <p>
 * 用户注册相关的一些接口的实现
 * </p>
 * 
 * @author Snailclimb
 */

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private TbUserMapper userMapper;

	/**
	 * 根据特定的查询条件在数据库中查询用户的方法，供下面register方法调用
	 * 
	 * @param param
	 *            数据的内容（比如手机号，用户名，邮箱）
	 * @param type
	 *            要查询的数据类型
	 */
	@Override
	public E3Result checkData(String param, int type) {
		// 根据不同的type生成不同的查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// type类型：1：用户名 2：手机号 3：邮箱
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else if (type == 3) {
			criteria.andEmailEqualTo(param);
		} else {
			return E3Result.build(400, "数据类型错误");
		}
		// 根据特定查询条件执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		// 判断结果中是否包含数据
		if (list != null && list.size() > 0) {
			// 如果有数据返回false
			return E3Result.ok(false);
		}
		// 如果没有数据返回true
		return E3Result.ok(true);
	}

	/**
	 * 用户注册方法（包括检验用户输入是否有效以及将用户信息插入数据库的操作）
	 */
	@Override
	public E3Result register(TbUser user) {
		// 数据有效性校验
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())
				|| StringUtils.isBlank(user.getPhone())) {
			return E3Result.build(400, "用户数据不完整，注册失败");
		}
		// type:1：用户名 2：手机号 3：邮箱
		// 检查用户的姓名是否已经被注册
		E3Result result = checkData(user.getUsername(), 1);
		if (!(boolean) result.getData()) {
			return E3Result.build(400, "此用户名已经被占用");
		}
		// 检查用户的手机号是否已经被注册
		result = checkData(user.getPhone(), 2);
		if (!(boolean) result.getData()) {
			return E3Result.build(400, "手机号已经被占用");
		}
		// ------如果走到说明这里用户输入的用户名以及密码都没有问题------
		// 补全pojo的属性（创建时间和更新时间）
		user.setCreated(new Date());
		user.setUpdated(new Date());
		// 对密码进行md5加密，注意：md5加密是单向加密只能通过密码得到加密后的数据而不能通过加密后的数据得到密码
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		// 把用户数据插入到数据库中
		userMapper.insert(user);
		// 返回添加成功
		return E3Result.ok();
	}

}
