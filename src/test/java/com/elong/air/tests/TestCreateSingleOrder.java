package com.elong.air.tests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.elong.air.Bean.PassenagerInfoBean;
import com.elong.air.Bean.SearchInfoBean;
import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.CheckOrderPage;
import com.elong.air.PageObject.HomePage;
import com.elong.air.PageObject.InputInformationPage;
import com.elong.air.PageObject.PaymentMethodPage;
import com.elong.air.PageObject.PaymentPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.dataProvider.BaseProvider;
import com.elong.air.dataProvider.DataProvider;
import com.elong.air.dataProvider.ITestData;
import com.elong.air.tools.ConfigDriver;
public class TestCreateSingleOrder extends ConfigDriver{
	HomePage hp;
	public int sheet = 1;	
	
	@BeforeMethod
	public void setUpHomePage(){
		hp=new HomePage(driver);
	}
	
@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
public void createSingleOrder(Object caseNum) throws Exception{
	int rowNum = (int)caseNum;
	System.out.println("这里是rowNum"+rowNum);
	
//	DataProvider dp=new DataProvider();
//	SearchInfoBean bean=(SearchInfoBean)dp.getBean(this.getClass().getSimpleName());
	SearchInfoBean bean=ITestData.getTestDataConvertToBean(sheet,rowNum);
	SearchPage sp=new SearchPage(driver,this.getClass().getSimpleName());
	AirListsPage ap = sp.searchFlight(bean);
 
	InputInformationPage ip=ap.getFirstBizopt();
	List<PassenagerInfoBean> passenagerInfo = bean.getPassenagerInfo();

	CheckOrderPage cop=ip.putPassInfoBean(passenagerInfo);
	PaymentMethodPage pmp=	cop.submitOrder();
	
	PaymentPage pp=	pmp.chooseCreditCard();
	pp.inputBankInformation();
	
	
}

}
