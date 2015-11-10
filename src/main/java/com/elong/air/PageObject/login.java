package com.elong.air.PageObject;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.AbstractObject.AbstractPageObject;

public class login extends AbstractPageObject{
	public login(WebDriver driver) {
		super(driver);
	}
	private WebDriver driver;
	


	@FindBy(css = "#kw")
	public WebElement input;
	@FindBy(xpath = "//input[@id='su']")
	public WebElement button;

	public void search(){
		input.clear();
		input.sendKeys("���ľ�");
		button.click();
	}
	public void switchWindow(){
	  String currentWindow=driver.getWindowHandle();
       Set<String>handles=driver.getWindowHandles();
       for(String s:handles){
    	   if(currentWindow.equals(s)){
    		   continue;
    	   }
    	   driver.switchTo().window(s);
    	  
       }
       
	}
	public void switchWindow1(){
		String currentWindow=driver.getWindowHandle();
	Set<String>handles=	driver.getWindowHandles();
	Iterator<String>it=handles.iterator();
	while(it.hasNext()){
		String handle=it.next();
		if(currentWindow.equals(handle))
			continue;
		driver.switchTo().window(handle);
	}
	}

}
