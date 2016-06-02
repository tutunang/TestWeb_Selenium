package com.elong.air.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.base.BasePage;
import com.elong.air.tools.TakeScreenShot;
import com.elong.air.tools.WaitTools;

public class AirListsRoundTripPage extends BasePage{

	public AirListsRoundTripPage(WebDriver driver) {
		super(driver);
		WaitTools.waitToElement(searchButton, driver);
	}
	
//	@FindBy(css=".a_on")
//	public WebElement key1;
	@FindBy(css = "#btnSearch")
	public WebElement searchButton;
	@FindBy(xpath = "//div[@class='combination' and @style='display: block;']//input")
	public WebElement bookButton;

	@FindBy(xpath = "//div[@class='combination' and @style='display: block;']//ul/li[3]/div[1]")
	public WebElement insuranceCheckBox;

	@FindBy(xpath = "//div[@class='combination' and @style='display: block;']//ul/li[3]/div[2]")
	public WebElement scoreCheckBox;

	@FindBy(css = "#flightlist .flightlist .fltable-div  .bizopt")
	public List<WebElement> bizopt;

	@FindBy(xpath = "//table[@class='fltable']/tbody//td[1]/span")
	public List<WebElement> airList2;
	//商务优选
	public InputInformationPage getFirstDiamond() throws Exception {
		for (WebElement e : airList2) {
			//WebElement span = e.findElement(By.xpath(".//td[1]/span"));
			String name = e.getAttribute("class");
			if (name.equals("diamond")) {
				WebElement book = e.findElement(By.xpath("../..//td[7]/div/input"));
				click(book);
				
				return new InputInformationPage(driver);
			}

		}
		throw new Exception("Can't not find the airlist");

	}
	
//	//商务优选_往返
//	public InputInformationPage getFirstBizopt_round() throws Exception {
//		for (WebElement e : airList) {
//			//WebElement span = e.findElement(By.xpath(".//td[1]/span"));
//			String name = e.getAttribute("class");
//			if (name.equals("bizopt")) {
//				WebElement book = e.findElement(By.xpath("../..//td[7]/div/input"));
//				//driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS); 
//				TakeScreenShot.takeScreenShot(driver);
//				click(book);
//				return new InputInformationPage(driver);
//			}
//
//		}
//		throw new Exception("Can't not find the airlist");
//
//	}
	//旅行优选
	public InputInformationPage getFirstTripopt(boolean score,boolean insurance) throws Exception {
		for (WebElement e : airList2) {
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
		
		return new InputInformationPage(driver);

	}
	
}
