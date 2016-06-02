package com.elong.air.base;

import java.util.Map;

import com.elong.air.dataProvider.ITestData;

/**
 * 顶层Bean类，com.elong.air.bean下面的所有bean类都应继承该类，且子类构造方法调用该类构造方法，用来初始化从测试数据中读取到的Map
 * 
 * @author QiaoJiafei
 * @version 创建时间：2016年4月28日 下午1:20:35 类说明
 */
public abstract class BaseBean {
	public Map<String, String> map;

	public BaseBean() {
	}

	public BaseBean(int sheet, int caseNum) {
		map = ITestData.getTestData(sheet, caseNum);

	}

	/**
	 * 根据传入的Map用来初始化测试数据，子类要重写该方法
	 * 
	 * @Title: initTestData
	 * @param map
	 * @return void
	 * @throws
	 */
//	public abstract void initTestData(Map<String, String> map);
}
