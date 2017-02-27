package com.elong.air.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestforCenter {
	
	WebDriver wd;
			
@BeforeMethod
public void setUp(){
	wd=new ChromeDriver();
	
}
@AfterMethod
public void close(){
	wd.quit();
}
@Test
public void test(){
wd.get("http://baidu.com");
}
}
