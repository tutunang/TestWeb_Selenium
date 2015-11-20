package com.elong.air.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.elong.air.AbstractObject.AbstractPageObject;
import com.elong.air.Bean.PassenagerInfoBean;
import com.elong.air.exception.AirException;
import com.elong.air.tools.WaitTools;

public class InputInformationPage  extends AbstractPageObject{
public String name="";
	public InputInformationPage(WebDriver driver,String name) {
		super(driver);
		super.name=name;
		this.name=name;
		WaitTools.waitToElement(phoneInputTextField, driver);
	}
@FindBy(css="li[method='Passenger']> #txtPassengerName" )
public List<WebElement> passengerNameInputTextField;

@FindBy(css="#txtCertificateNo")
public List<WebElement> passengerIdInputTextField;

@FindBy(css=".j-insure-box>p>input")
public List<WebElement> insuranceCheckBox;

@FindBy(css="#dlCerType")
public List<WebElement> cardType;

@FindBy(css="#txtMobilePhone")
public WebElement phoneInputTextField;

@FindBy(css="#btnSaveOrder")
public WebElement summitButton;

@FindBy(css="#txtBirthday")
public List<WebElement> birthTextField;

@FindBy(css=".btn")
public List<WebElement> iKnowButton;

@FindBy(css=".add.clx>a")
public WebElement addPassenger;


public CheckOrderPage putPassInfoBean (List<PassenagerInfoBean>s) throws AirException {
	this.addPassenger(s.size());
	for(int i=0;i<s.size();i++){
		PassenagerInfoBean bean =s.get(i);
		Select select=new Select(cardType.get(i));
		select.selectByIndex(bean.getCardType());
		this.setInputText(passengerNameInputTextField.get(i), bean.getPassengerName());
		this.setInputText(birthTextField.get(i), bean.getBirthDate());
		this.setInputText(passengerIdInputTextField.get(i), bean.getPassenagerId());
		this.setInputText(phoneInputTextField, bean.getPhoneNum());
			summbitOrder();
		
	}
	return new CheckOrderPage(driver,name);
}

public void addPassenger(int i){
	for (int j = 0; j < i-1; j++) {
		click(addPassenger);
	}
}

public void summbitOrder() throws AirException{
	if(summitButton.isEnabled())
	click(summitButton);
	else{
throw new AirException("预定按钮不可用");
}
}
}
