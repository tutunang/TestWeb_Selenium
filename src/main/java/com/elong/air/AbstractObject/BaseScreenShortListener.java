package com.elong.air.AbstractObject;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


/**
 * @author  qiaojiafei
 * @version 创建时间：2016年4月25日 下午1:06:39
 * 类说明
 */
public class BaseScreenShortListener extends TestListenerAdapter{
	BaseTestClass bc = new BaseTestClass();
	
	@Override  
    public synchronized void onTestFailure(ITestResult result) { 
		bc.takeScreenShort(result);
	}
}
