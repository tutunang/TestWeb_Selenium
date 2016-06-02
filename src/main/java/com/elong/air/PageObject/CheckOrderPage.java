package com.elong.air.PageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.base.BasePage;
import com.elong.air.exception.AirException;

public class CheckOrderPage extends BasePage{

	public CheckOrderPage(WebDriver driver) {
		super(driver);
	}
@FindBy(css="#submit")
public WebElement sumbitButton;
@FindBy(css=".com_dialog-content.com_widget-content")
public WebElement noSeatShow;
@FindBy(css=".btn")
public WebElement iKnowButton;
public String paymentPage_submit_success=".order_sus>p>b";


public PaymentMethodPage submitOrder() throws AirException {
	try{
		if(noSeatShow.isDisplayed())
		{
			click(iKnowButton);
		throw new AirException("无座了，请重新预定！");
			
		}}
	catch(Exception air){
		click(sumbitButton);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("handle" + driver.getWindowHandle());
		System.out.println("url" + driver.getCurrentUrl());
		System.out.println("title" + driver.getTitle());
		return new PaymentMethodPage(driver);
	}
	return null;
	
	  
		
		}



public <expectedPageObject> expectedPageObject summitOrder(Class<? extends BasePage> expectedPageObject) throws Exception {
   driver.getWindowHandle();
   driver.getCurrentUrl();
   driver.getTitle();
return null;
}
}
