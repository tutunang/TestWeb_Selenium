package com.elong.air.tests;

import java.util.List;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.AirListsRoundTripPage;
import com.elong.air.PageObject.CheckOrderPage;
import com.elong.air.PageObject.InputInformationPage;
import com.elong.air.PageObject.PaymentMethodPage;
import com.elong.air.PageObject.PaymentPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.base.BaseTestClass;
import com.elong.air.bean.PassenagerInfoBean;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.BaseProvider;
@Listeners({com.elong.air.base.BaseScreenShortListener.class})
public class TestCreateOrder_RoundTrip extends BaseTestClass{
	//HomePage hp;
	public int sheet = 4;	

	@Test(dataProvider="testdp",dataProviderClass = BaseProvider.class)
	public void createSingleOrder(Object caseNum) throws Exception{
		int rowNum = (int)caseNum;
		System.out.println("这里是rowNum"+rowNum);
		SearchInfoBean bean = new SearchInfoBean(sheet, rowNum);
		SearchPage sp=new SearchPage(driver);
		AirListsPage ap=sp.searchFlight(bean);
		AirListsRoundTripPage artp=ap.getFirstBizopt_round();
		InputInformationPage ip=artp.getFirstDiamond();
		List<PassenagerInfoBean> passenagerInfo = bean.getPassenagerInfo();
		CheckOrderPage cop=ip.putPassInfoBean(passenagerInfo);
		PaymentMethodPage pmp=	cop.submitOrder();
		PaymentPage pp=	pmp.chooseCreditCard();
	//	pp.inputBankInformation();
		
		
	}

	}

