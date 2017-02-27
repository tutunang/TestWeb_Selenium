package com.elong.air.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.elong.air.base.BasePage;
import com.elong.air.bean.PassenagerInfoBean;
import com.elong.air.exception.AirException;
import com.elong.air.tools.WaitTools;

public class InputInformationPage  extends BasePage{

	public InputInformationPage(WebDriver driver) {
		super(driver);		
	}

@FindBy(css=".passenger-info-nameValue" )
public List<WebElement> passengerNameInputTextField;

@FindBy(css=".passenger-info-certValue")
public List<WebElement> passengerIdInputTextField;

@FindBy(css=".j-insure-box>p>input")
public List<WebElement> insuranceCheckBox;

@FindBy(css=".passenger-info-certType")
public List<WebElement> cardType;

@FindBy(css=".linkMethod-phone")
public WebElement phoneInputTextField;

@FindBy(css=".orderSubmit.orderSubmitHover")
public WebElement summitButton;

@FindBy(css=".passenger-info-birthdayValue")
public List<WebElement> birthTextField;

@FindBy(css=".btn")
public List<WebElement> iKnowButton;

@FindBy(css=".add.clx>a")
public WebElement addPassenger;

@FindBy(css="span[method='advance']")
public WebElement advanceLink;


public CheckOrderPage putPassInfoBean (List<PassenagerInfoBean>s) throws AirException {
	this.addPassenger(s.size());
	for(int i=0;i<s.size();i++){
		PassenagerInfoBean bean =s.get(i);
		Select select=new Select(cardType.get(i));
		select.selectByIndex(bean.getCardType());
		if(bean.getCardType()!=0){
			this.setInputText(birthTextField.get(i), bean.getBirthDate());
		}
		
		this.setInputText(passengerNameInputTextField.get(i), bean.getPassengerName());
		this.setInputText(passengerIdInputTextField.get(i), bean.getPassenagerId());
		this.setInputText(phoneInputTextField, bean.getPhoneNum());
	}
	summbitOrder();
	return new CheckOrderPage(driver);
}

public void addPassenger(int i){
	for (int j = 0; j < i-1; j++) {
		click(addPassenger);
	}
}

	public void summbitOrder() throws AirException {
		if (summitButton.isEnabled())
			click(summitButton);
		else {
			throw new AirException("预定按钮不可用");
		}
	}
}
