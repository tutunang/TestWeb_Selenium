package com.elong.air.tests;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.CheckOrderPage;
import com.elong.air.PageObject.InputInformationPage;
import com.elong.air.PageObject.PaymentMethodPage;
import com.elong.air.PageObject.PaymentPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.base.BaseTestClass;
import com.elong.air.bean.PassenagerInfoBean;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
import com.elong.air.tools.DriverFactory;


/** 
 * @author QiaoJiafei 
 * @version 创建时间：2016年4月29日 上午9:52:44 
 * 类说明 
 */
@Listeners({com.elong.air.base.BaseScreenShortListener.class})
public class TestDemoCreateSingleOrder extends BaseTestClass {
	
	public int sheet = 1;
	
	@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
	public void createSingleOrder(Object caseNum) throws Exception{
		int rowNum = (int)caseNum;
		System.out.println("这里是rowNum"+rowNum);
		System.out.println(5/0);
		
		//使用下面两行的方式构造bean对象和页面对象
		SearchInfoBean cpsearch = new SearchInfoBean(sheet, rowNum);
		SearchPage sp=new SearchPage(driver);
		//
		
		AirListsPage ap = sp.searchFlight(cpsearch);
	 
		InputInformationPage ip=ap.getFirstBizopt();
		List<PassenagerInfoBean> passenagerInfo = cpsearch.getPassenagerInfo();
	
		CheckOrderPage cop=ip.putPassInfoBean(passenagerInfo);
		PaymentMethodPage pmp=	cop.submitOrder();
		
		PaymentPage pp=	pmp.chooseCreditCard();
		pp.inputBankInformation();
		
		
	}
}
