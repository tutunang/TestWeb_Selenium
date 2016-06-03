package com.elong.air.tests;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.elong.air.PageObject.SearchPage;
import com.elong.air.base.BaseTestClass;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
@Listeners({com.elong.air.base.BaseScreenShortListener.class})
public class TestSearchField extends BaseTestClass{
	public int sheet = 5;	
	
@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
public void testSearchField(Object caseNum) throws Exception{
	int rowNum = (int)caseNum;
	System.out.println("这里是rowNum"+rowNum);
	SearchInfoBean cpsearch = new SearchInfoBean(sheet, rowNum);
	SearchPage sp=new SearchPage(driver);
	sp.searchFlight(cpsearch);
    Assert.assertEquals(sp.getErrorMsg(), "出发城市与到达城市不可相同，请重新输入！");


	
}
}
