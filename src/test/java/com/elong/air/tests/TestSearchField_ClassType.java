package com.elong.air.tests;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.base.BaseTestClass;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
@Listeners({com.elong.air.base.BaseScreenShortListener.class})
public class TestSearchField_ClassType extends BaseTestClass{
	public int sheet = 3;	
	
@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
public void testSearchField(Object caseNum) throws Exception{
	int rowNum = (int)caseNum;
	System.out.println("这里是rowNum"+rowNum);
	SearchInfoBean cpsearch = new SearchInfoBean(sheet, rowNum);
	SearchPage sp=new SearchPage(driver);
	AirListsPage alp=sp.searchFlight(cpsearch);
	Assert.assertEquals(alp.verfiyClassTypeM(), true);



	
}
}
