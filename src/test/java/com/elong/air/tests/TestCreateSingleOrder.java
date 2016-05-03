package com.elong.air.tests;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import test.inheritance.testng739.BaseClass;

import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.CheckOrderPage;
import com.elong.air.PageObject.HomePage;
import com.elong.air.PageObject.InputInformationPage;
import com.elong.air.PageObject.PaymentMethodPage;
import com.elong.air.PageObject.PaymentPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.base.BaseTestClass;
import com.elong.air.bean.PassenagerInfoBean;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
import com.elong.air.dataProvider.DataProvider;
import com.elong.air.dataProvider.ITestData;
import com.elong.air.tools.ConfigDriver;
@Listeners({com.elong.air.base.BaseScreenShortListener.class})
public class TestCreateSingleOrder extends BaseTestClass{
	HomePage hp;
	public int sheet = 1;	
	
	@BeforeMethod
	public void setUpHomePage() throws IOException{
		ConfigDriver cfd = new ConfigDriver();
		driver = cfd.setUpDriver();
		
	}
	
	@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
	public void createSingleOrder(Object caseNum) throws Exception{
		int rowNum = (int)caseNum;
		System.out.println("这里是rowNum"+rowNum);
		System.out.println(5/0);
		SearchInfoBean cpsearch = new SearchInfoBean(sheet, rowNum);
		SearchPage sp=new SearchPage(driver,"testtitle");
		AirListsPage ap = sp.searchFlight(cpsearch);
	 
		InputInformationPage ip=ap.getFirstBizopt();
		List<PassenagerInfoBean> passenagerInfo = cpsearch.getPassenagerInfo();
	
		CheckOrderPage cop=ip.putPassInfoBean(passenagerInfo);
		PaymentMethodPage pmp=	cop.submitOrder();
		
		PaymentPage pp=	pmp.chooseCreditCard();
		pp.inputBankInformation();
		
		
	}

}
