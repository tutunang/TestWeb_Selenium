package com.elong.air.tests;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
public class TestRemot {
	public static String browserName="chrome";
	//public static String version;
	public static String platform="WINDOWS";

	public   WebDriver getRemoteWebDriver() throws MalformedURLException {
		if (platform.equalsIgnoreCase("WINDOWS")) {
			switch (browserName) {
			case "chrome":// chrome浏览器直接忽略版本信息
//				File file = new File("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");  
//				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());  
				DesiredCapabilities capability0 = DesiredCapabilities.chrome();
				//capability0.setJavascriptEnabled(true);
				System.setProperty("webdriver.chrome.driver","D:/chromedriver.exe");
			//	ChromeDriver driver = new ChromeDriver(caps);

		     	String 	version="ANY";
			    capability0.setVersion(version);
				capability0.setBrowserName("chrome");
				capability0.setPlatform(Platform.WINDOWS);
				WebDriver driver0 = new RemoteWebDriver(new URL(
						"http://localhost:4444/wd/hub"), capability0);
				return driver0;
			case "internet explorer":// 由客户端决定使用浏览器32/64位数
				DesiredCapabilities capability1 = DesiredCapabilities
						.internetExplorer();
				capability1
						.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
				capability1.setBrowserName("internet explorer");
				//capability1.setVersion(version);
				capability1.setPlatform(Platform.WINDOWS);
				WebDriver driver1 = new RemoteWebDriver(new URL(
						"http://localhost:4444/wd/hub"), capability1);
				return driver1;
			case "firefox":// firefox浏览器直接忽略版本信息
				DesiredCapabilities capability2 = DesiredCapabilities.firefox();
				capability2.setBrowserName("firefox");
				capability2.setPlatform(Platform.WINDOWS);
				WebDriver driver2 = new RemoteWebDriver(new URL(
						"http://localhost:4444/wd/hub"), capability2);
				return driver2;
			}
		}else if (platform.equalsIgnoreCase("MAC")){
			switch (browserName) {
			case "chrome":// chrome浏览器直接忽略版本信息
				DesiredCapabilities capability0 = DesiredCapabilities.chrome();
				capability0.setBrowserName("chrome");
				capability0.setPlatform(Platform.MAC);
				WebDriver driver0 = new RemoteWebDriver(new URL(
						"http://localhost:4444/wd/hub"), capability0);
				return driver0;
			case "firefox":// firefox浏览器直接忽略版本信息
				DesiredCapabilities capability2 = DesiredCapabilities.firefox();
				capability2.setBrowserName("firefox");
				capability2.setPlatform(Platform.MAC);
				WebDriver driver2 = new RemoteWebDriver(new URL(
						"http://localhost:4444/wd/hub"), capability2);
				return driver2;
			}
		}
		return null;
	}

	
	

public static void main(String[] args) throws Exception{
	TestRemot t=new TestRemot();
	WebDriver wdd=t.getRemoteWebDriver();
//	DesiredCapabilities ffDesiredcap = DesiredCapabilities.firefox();  
////	DesiredCapabilities chromeDesiredcap = DesiredCapabilities.chrome();  
////	DesiredCapabilities ieDesiredcap = DesiredCapabilities.internetExplorer();  
//	WebDriver wdd = new RemoteWebDriver(new URL("http://localhost:4555/wd/hub"), ffDesiredcap); 
	wdd.get("http://www.baidu.com");
	Thread.sleep(1200);  
	System.out.println(wdd.getCurrentUrl());  
	wdd.quit(); 
}
}