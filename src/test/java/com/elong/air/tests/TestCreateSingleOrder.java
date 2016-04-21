package com.elong.air.tests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.elong.air.AbstractObject.HomePage;
import com.elong.air.AbstractObject.HomePage.Zones;
import com.elong.air.Bean.PassenagerInfoBean;
import com.elong.air.Bean.SearchInfoBean;
import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.CheckOrderPage;
import com.elong.air.PageObject.InputInformationPage;
import com.elong.air.PageObject.PaymentMethodPage;
import com.elong.air.PageObject.PaymentPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.dataProvider.DataProvider;
import com.elong.air.tools.ConfigDriver;
public class TestCreateSingleOrder extends ConfigDriver{
	HomePage hp;
	
	@BeforeMethod
	public void setUpHomePage(){
		hp=new HomePage(driver);
	}
	
@Test
public void createSingleOrder() throws Exception{
	System.out.println(this.getClass().getSimpleName());
	
	DataProvider dp=new DataProvider();
	SearchInfoBean bean=(SearchInfoBean)dp.getBean(this.getClass().getSimpleName());

	SearchPage sp=new SearchPage(driver,this.getClass().getSimpleName());
		//AirListsPage ap=
	 AirListsPage ap = sp.searchFlight(bean);
 
	InputInformationPage ip=ap.getFirstBizopt();
	List<PassenagerInfoBean> passenagerInfo = bean.getPassenagerInfo();

	CheckOrderPage cop=ip.putPassInfoBean(passenagerInfo);
	PaymentMethodPage pmp=	cop.submitOrder();
	
	PaymentPage pp=	pmp.chooseCreditCard();
	pp.inputBankInformation();
	
	
}

}
