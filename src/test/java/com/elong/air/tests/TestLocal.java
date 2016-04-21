package com.elong.air.tests;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class TestLocal {
public void test() throws  Exception{

}
public static void main(String[] args) throws Exception{
	  
//	DesiredCapabilities ffDesiredcap = DesiredCapabilities.firefox();  
//	DesiredCapabilities chromeDesiredcap = DesiredCapabilities.chrome();  
//	DesiredCapabilities ieDesiredcap = DesiredCapabilities.internetExplorer(); 
	 System.setProperty("webdriver.chrome.driver","D:/chromedriver.exe");
	//File file = new File("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");  
	//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());  
	WebDriver wdd = new ChromeDriver(); 
	wdd.get("http://www.baidu.com");
	Thread.sleep(1200);  
	System.out.println(wdd.getCurrentUrl());  
	wdd.quit(); 
}
}