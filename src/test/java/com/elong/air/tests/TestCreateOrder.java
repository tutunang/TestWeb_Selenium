package com.elong.air.tests;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.CheckOrderPage;
import com.elong.air.PageObject.InputInformationPage;
import com.elong.air.PageObject.PaymentMethodPage;
import com.elong.air.PageObject.PaymentPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.bean.PassenagerInfoBean;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
import com.elong.air.dataProvider.DataProvider;
import com.elong.air.tools.ConfigDriver;
public class TestCreateOrder extends ConfigDriver{
	private Logger log = Logger.getLogger(TestCreateOrder.class);  
	
	@BeforeMethod
	public void Before(){
		//执行case前置方法
	}
	@AfterMethod
	public void After(){
		//执行case后置方法
	}
@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
public void createSingleOrder() throws Exception{
	log.info("13213131asdasd");
    String s =this.getClass().getSimpleName();
	System.out.println(this.getClass().getSimpleName());
	DataProvider dp=new DataProvider();
	SearchInfoBean bean=(SearchInfoBean)dp.getBean(this.getClass().getSimpleName());

	SearchPage sp=new SearchPage(driver);
	AirListsPage ap=sp.searchFlight(bean);
	InputInformationPage ip=ap.getFirstBizopt();
	List<PassenagerInfoBean> passenagerInfo = bean.getPassenagerInfo();

	CheckOrderPage cop=ip.putPassInfoBean(passenagerInfo);

	PaymentMethodPage pmp=	cop.submitOrder();
	PaymentPage pp=	pmp.chooseCreditCard();
	pp.inputBankInformation();
//	Assert.assertEquals(actual, ture);
	
	
	
}

}
