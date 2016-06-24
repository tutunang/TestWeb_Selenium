package com.elong.air.tests;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.base.BaseTestClass;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
@Listeners({com.elong.air.base.BaseScreenShortListener.class})
public class TestCheckAirLists extends BaseTestClass{
	private Logger log = Logger.getLogger(TestCheckAirLists.class);  
	public int sheet = 6;
	
	
	
	
@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
public void createSingleOrder(Object caseNum) throws Exception{
	int rowNum = (int)caseNum;
	System.out.println("这里是rowNum"+rowNum);
	SearchInfoBean cpsearch = new SearchInfoBean(sheet, rowNum);
	SearchPage sp=new SearchPage(driver);
	AirListsPage airPg=	sp.searchFlight(cpsearch);
     boolean s=	airPg.verfiyAirList().size()>0;
     Assert.assertTrue("列表页无数据", s);
	
}

}
