package com.elong.air.tools;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectUtils{
//extends AbstractPageObject{

//public SelectUtils(WebDriver driver) {
//		super(driver);
//	}


public void selectByIndex(WebElement e ,int index){
//	this.click(e);
	Select s=new Select(e);
	s.selectByIndex(index);
	
}

public void selectByIndex(WebElement e ,String index){
//	this.click(e);
	Select s=new Select(e);
	s.selectByIndex(Integer.parseInt(index));
	
}
public void selectByText(WebElement e,String text){
	Select s=new Select(e);
	s.deselectByVisibleText(text);
}

public void selectByValue(WebElement e,String target){
//	this.click(e);
	Select s=new Select(e);
	s.selectByValue(target);
}
}
