package com.elong.air.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.elong.air.base.BasePage;
import com.elong.air.bean.PassenagerInfoBean;
import com.elong.air.tools.WaitTools;

public class CopyOfInputInformationPage  extends BasePage{

	public CopyOfInputInformationPage(WebDriver driver) {
		super(driver);
		WaitTools.waitToElement(phoneInputTextField, driver);
	}
@FindBy(css="li[method='Passenger']> #txtPassengerName" )
public WebElement passengerNameInputTextField;

@FindBy(css="#txtCertificateNo")
public WebElement passengerIdInputTextField;

@FindBy(css=".j-insure-box>p>input")
public WebElement insuranceCheckBox;

@FindBy(css="#dlCerType")
public WebElement CardType;

@FindBy(css="#txtMobilePhone")
public WebElement phoneInputTextField;

@FindBy(css="#btnSaveOrder")
public WebElement summitButton;

@FindBy(css="#txtBirthday")
public WebElement birthTextField;

@FindBy(css=".btn")
public WebElement iKnowButton;

public List<WebElement> passinfoList;

public void chooseDocType(int index){
	Select select=new Select(CardType);
	select.selectByIndex(index);
}
public CheckOrderPage setPassengerInfo(PassenagerInfoBean bean){
	this.setInputText(passengerNameInputTextField, bean.getPassengerName());
	chooseDocType(bean.getCardType());
	this.setInputText(birthTextField, bean.getBirthDate());
	this.setInputText(passengerIdInputTextField, bean.getPassenagerId());
	this.setInputText(phoneInputTextField, bean.getPhoneNum());
	summbitOrder();
	return new CheckOrderPage(driver);
}

public void putPassInfoBean (List<PassenagerInfoBean>s){
	for(PassenagerInfoBean bean :s){
		setPassengerInfo(bean);
	}
}

public void ss(){

	for(WebElement e:passinfoList){
		
	}
	
}

public void summbitOrder(){
	this.click(summitButton);
//	if(iKnowButton.isDisplayed()){
//     Alert al=driver.switchTo().alert();
//	 al.accept();
//	 driver.quit();  
//	}
}
}
