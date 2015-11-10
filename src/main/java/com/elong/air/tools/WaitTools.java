package com.elong.air.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTools {

	public static void waitToElement( final WebElement element, WebDriver driver) {
		 WebDriverWait wait=new WebDriverWait(driver,10);
		 wait.until(new ExpectedCondition<Boolean>(){
		 public Boolean apply(WebDriver driver ){
		 return element.isDisplayed();
		
		 }
		 });


	}
}
