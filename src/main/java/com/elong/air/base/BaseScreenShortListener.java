package com.elong.air.base;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * 监听异常截图类，所有测试类都要在类前面加入@Listeners({com.elong.air.base.BaseScreenShortListener.
 * class})
 * 
 * @version 创建时间：2016年4月25日 下午1:06:39 类说明
 */
public class BaseScreenShortListener extends TestListenerAdapter {
	BaseTestClass bc = new BaseTestClass();

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		bc.takeScreenShort(result);
	}
}
