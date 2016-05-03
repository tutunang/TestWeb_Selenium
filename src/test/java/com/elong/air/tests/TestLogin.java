package com.elong.air.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.elong.air.PageObject.LoginPage;
import com.elong.air.base.BaseTestClass;
import com.elong.air.bean.LoginInfoBean;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
import com.elong.air.dataProvider.ITestData;


@Listeners({com.elong.air.base.BaseScreenShortListener.class})
public class TestLogin extends BaseTestClass{
	public int sheet = 1;	

	@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
	public void testlogin(Object caseNum) {
		int rowNum = (int)caseNum;
		System.out.println(rowNum+"第几行");
		LoginInfoBean loginbean = new LoginInfoBean(sheet, rowNum);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.validaLogin(loginbean);
	}}
