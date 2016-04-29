package com.elong.air.tests;

import org.testng.annotations.Test;

import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
import com.elong.air.dataProvider.ITestData;



public class TestLogin {
	public int sheet = 3;	

	@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
	public void testlogin(Object caseNum) {
		int rowNum = (int)caseNum;
		System.out.println(rowNum+"第几行");
		SearchInfoBean sb=	new SearchInfoBean(sheet,rowNum);
System.out.println(	sb.getCabinLevel());
System.out.println(	sb.getPassenagerInfo().get(0).getPassengerName());
System.out.println(	sb.getPassenagerInfo().get(0).getCardType());
System.out.println(	sb.getPassenagerInfo().get(1).getEmailAddress());

	}}
