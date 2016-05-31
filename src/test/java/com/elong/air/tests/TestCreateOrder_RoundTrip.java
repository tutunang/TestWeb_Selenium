package com.elong.air.tests;

import java.util.List;

import org.testng.annotations.Test;

import com.elong.air.PageObject.AirListsPage;
import com.elong.air.PageObject.AirListsRoundTripPage;
import com.elong.air.PageObject.CheckOrderPage;
import com.elong.air.PageObject.InputInformationPage;
import com.elong.air.PageObject.PaymentMethodPage;
import com.elong.air.PageObject.PaymentPage;
import com.elong.air.PageObject.SearchPage;
import com.elong.air.bean.PassenagerInfoBean;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.dataProvider.DataProvider;
import com.elong.air.tools.ConfigDriver;

public class TestCreateOrder_RoundTrip extends ConfigDriver{
	@Test
	public void createSingleOrder() throws Exception{
		String  s=this.getClass().getSimpleName();
		
		System.out.println(this.getClass().getSimpleName());
		DataProvider dp=new DataProvider();
		SearchInfoBean bean=(SearchInfoBean)dp.getBean(this.getClass().getSimpleName());
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

