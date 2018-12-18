package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;

/**
 * <p>
 * 用户注册相关的一些接口
 * </p>
 * 
 * @author Snailclimb
 */
public interface RegisterService {

	/**
	 * 根据特定的查询条件在数据库中查询用户的方法，供下面register方法调用
	 * 
	 * @param param
	 *            数据的内容（比如手机号，用户名，邮箱）
	 * @param type
	 *            要查询的数据类型
	 * @return
	 */
	E3Result checkData(String param, int type);

	/**
	 * 
	 * @param user
	 *            用户对象
	 * @return 200（代表添加成功）
	 */
	E3Result register(TbUser user);
}
