package com.elong.air.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.AbstractObject.AbstractPageObject;

public class PaymentMethodPage extends AbstractPageObject{
public String name="";
	public PaymentMethodPage(WebDriver driver,String name) {
		super(driver);
		super.name=name;
	}

@FindBy(xpath="//ul[@id='tabMenu']/li[1]")
public WebElement creditCard;

@FindBy(css=".card_sel.clx input[id='41']")
public WebElement CITIC_Bank;

@FindBy(css="#submit")
public WebElement next_StepButton;

public PaymentPage chooseCreditCard(){
	this.click(creditCard);
	this.setCheck(CITIC_Bank);
	this.click(next_StepButton);
	return new PaymentPage(driver);
	
}
}
