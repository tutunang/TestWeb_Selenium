package com.elong.air.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.elong.air.AbstractObject.HomePage;
import com.elong.air.AbstractObject.HomePage.Zones;
import com.elong.air.Bean.SearchInfoBean;
import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.dataProvider.DataProvider;
import com.elong.air.tools.ConfigDriver;

public class TestSearchField extends ConfigDriver{
	HomePage hp;
	
	@BeforeMethod
	public void setUpHomePage(){
		hp=new HomePage(driver);
	}
@Test
public void TestField(){
	//数据驱动，获取xml中测试数据，强转成SearchBean类型,通过注释实现扫描
	DataProvider dp=new DataProvider();
	SearchInfoBean bean=(SearchInfoBean)dp.getBean(this.getClass().getSimpleName());
	//在搜索页面设计搜索内容
	SearchPage sp=new SearchPage(driver,this.getClass().getSimpleName());
    sp.searchFlight(bean);
    //验证
    Assert.assertEquals(sp.getErrorMsg(), "出发城市与到达城市不可相同，请重新输入！");


	
}
}
