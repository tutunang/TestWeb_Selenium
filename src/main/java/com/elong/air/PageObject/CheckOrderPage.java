package com.elong.air.PageObject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.AbstractObject.AbstractPageObject;

public class CheckOrderPage extends AbstractPageObject{

	public CheckOrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
@FindBy(css="#submit")
public WebElement sumbitButton;

public PaymentMethodPage submitOrder() {
		click(sumbitButton);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("handle" + driver.getWindowHandle());
		System.out.println("url" + driver.getCurrentUrl());
		System.out.println("title" + driver.getTitle());
		return new PaymentMethodPage(driver);
}

public <expectedPageObject> expectedPageObject summitOrder(Class<? extends AbstractPageObject> expectedPageObject) throws Exception {
   driver.getWindowHandle();
   driver.getCurrentUrl();
   driver.getTitle();
return null;
}
}
