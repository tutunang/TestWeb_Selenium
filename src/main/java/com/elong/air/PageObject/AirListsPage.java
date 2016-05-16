package com.elong.air.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.base.BasePage;
import com.elong.air.exception.AirException;
import com.elong.air.tools.TakeScreenShot;

public class AirListsPage extends BasePage{
	public AirListsPage(WebDriver driver,String name) {
		super(driver,name);
		super.name=name;

	}
	public String inputInformation_Sub_button="//input[@id='btnSaveOrder']";
	public static String key1="//input[@method='btnBooking']";
	@FindBy(css = "#btnSearch")
	public  WebElement searchButton;
	
	@FindBy(xpath = "//div[@class='combination' and @style='display: block;']//input")
	public WebElement bookButton;
	
	@FindBy(xpath = "//div[@class='combination' and @style='display: block;']//ul/li[3]/div[1]")
	public WebElement insuranceCheckBox;
	
	@FindBy(xpath = "//div[@class='combination' and @style='display: block;']//ul/li[3]/div[2]")
	public WebElement scoreCheckBox;

	@FindBy(css = "#flightlist .flightlist .fltable-div  .bizopt")
	public List<WebElement> bizopt;
	
	@FindBy(xpath = "//table[@class='fltable']/tbody//td[1]/span")
	public List<WebElement> airList;
	//商务优选
	public InputInformationPage getFirstBizopt() throws AirException {
		for (WebElement e : airList) {
			//WebElement span = e.findElement(By.xpath(".//td[1]/span"));
			String name = e.getAttribute("class");
			if (name.equals("bizopt")) {
				WebElement book = e.findElement(By.xpath("../..//td[7]/div/input"));
				//driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); 
				TakeScreenShot.takeScreenShot(driver);
				click(book);
				return new InputInformationPage(driver,inputInformation_Sub_button);
			}

		}
		throw new AirException("Can't not find the airlist");

	}
	
	//商务优选_往返
	public AirListsRoundTripPage getFirstBizopt_round() throws Exception {
		for (WebElement e : airList) {
			//WebElement span = e.findElement(By.xpath(".//td[1]/span"));
			String name = e.getAttribute("class");
			if (name.equals("bizopt")) {
				WebElement book = e.findElement(By.xpath("../..//td[7]/div/input"));
				//driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); 
				TakeScreenShot.takeScreenShot(driver);
				click(book);
				return new AirListsRoundTripPage(driver);
			}

		}
		throw new Exception("Can't not find the airlist");

	}
	//旅行优选
	public InputInformationPage getFirstTripopt(boolean score,boolean insurance) throws Exception {
		for (WebElement e : airList) {
			//WebElement span = e.findElement(By.xpath(".//td[1]/span"));
			String name = e.getAttribute("class");
			if (name.equals("tripopt")) {
				WebElement book = e.findElement(By.xpath("../..//td[7]/div/input"));
				//driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); 
				TakeScreenShot.takeScreenShot(driver);
				click(book);
							}

		}
		if(score==true){
		setCheck(scoreCheckBox);
		}
		if(insurance==true){
			setCheck(insuranceCheckBox);
		}
		click(bookButton);
		
		return new InputInformationPage(driver,inputInformation_Sub_button);

	}
	
}
