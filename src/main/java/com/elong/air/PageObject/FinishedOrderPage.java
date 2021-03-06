package com.elong.air.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.base.BasePage;
import com.elong.air.exception.AirException;

public class FinishedOrderPage extends BasePage{

	public FinishedOrderPage(WebDriver driver) {
		super(driver);
	}
@FindBy(css=".btn_view.ml10")
public WebElement viewOrderButton;

public void verify () throws AirException{
	viewOrderButton.isDisplayed();
}
}
