package com.company.module.parse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 资源解析器
 */
public abstract class BaseParse<T extends List,E> {
	/**
	 * 建立线程池
	 */
	private ExecutorService executorService;

	/**
	 * 获取页码
	 * @param html
	 * @return
	 */
	public abstract int getPager(String html);

	/**
	 * 获取网页内容
	 * @param html
	 * @return
	 */
	public abstract T getMessage(String html);

	/**
	 * 第二次获取详细信息
	 * @return
	 */
	public abstract String getDetail(E obj);

	/**
	 * 页码选择器
	 * @param url
	 * @param index
	 * @return
	 */
	public abstract String switchPager(String url,int index);

	/**
	 * 开始执行
	 */
	public abstract void execute();

	/**
	 * 返回并新建一个线程池对象
	 * @return
	 */
	protected ExecutorService getExecutorService() {
		if(executorService==null){
			executorService= Executors.newCachedThreadPool();
		}
		return executorService;
	}
}
