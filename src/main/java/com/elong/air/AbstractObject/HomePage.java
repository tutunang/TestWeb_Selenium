package com.elong.air.AbstractObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.elong.air.PageObject.AirListsPage;

public class HomePage extends AbstractPageObject {
public String searchButton="//input[@id='btnSearch']";
public static String bookingButton="//input[@method='btnBooking']";
public static String flightInfo="//div[@class='list_seo clx']";

	public HomePage(WebDriver driver) {
		super(driver);
		//getContent(driver,x);
	}
	

	public enum Zones {
		MID, TOP, END
	};

	public <T extends AbstractPageObject>  T getContent(WebDriver driver, Zones x) {
		//super(driver);
		String z=x.toString();
		if (z.equals("MID")) {
			System.out.println("mid");
			return (T) new AirListsPage(driver, bookingButton);
			
		}
		if (z.equals("TOP")) {
			System.out.println("top");
		return (T)new TopPage(driver, searchButton);
			
		}
		if (z.equals("END")){
			System.out.println("end");
		return (T)new EndPage(driver, flightInfo);
			}
		return null;
		}
		
		
	}

