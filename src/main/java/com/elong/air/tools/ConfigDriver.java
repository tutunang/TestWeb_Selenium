package com.elong.air.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ConfigDriver {
public static	WebDriver driver;
public static String browserName="firefox";
//public static String version;
public static String platform="WINDOWS";
public static String visitURL="http://flight.elong.com";

@BeforeClass
public void setUpDriver() throws IOException{
       ProfilesIni allProfiles = new ProfilesIni();
       FirefoxProfile firefoxProfile = allProfiles.getProfile("default");
       driver = new FirefoxDriver(firefoxProfile);
   	   driver.get(visitURL);
	}
public void setUpDriver1(){
	if(platform.equalsIgnoreCase("WINDOWS")){
		switch(browserName){
		case "chrome":
			driver=new ChromeDriver();
			driver.get(visitURL);
		case "internet explorer" :
			driver=new InternetExplorerDriver();
			driver.get(visitURL);
		case "firfox":
			 ProfilesIni allProfiles = new ProfilesIni();
		       FirefoxProfile firefoxProfile = allProfiles.getProfile("default");
		       driver = new FirefoxDriver(firefoxProfile);
		   	   driver.get(visitURL);
			//mac没有进行过测试
		}}else if(platform.equalsIgnoreCase("MAC")){
			switch(browserName){
			case "chrome":
				DesiredCapabilities capability0 = DesiredCapabilities.chrome();
				capability0.setBrowserName("chrome");
				capability0.setPlatform(Platform.MAC);
				driver=new ChromeDriver(capability0);
			case "firefox":
				DesiredCapabilities capability2 = DesiredCapabilities.firefox();
				capability2.setBrowserName("firefox");
				capability2.setPlatform(Platform.MAC);
				driver=new FirefoxDriver(capability2);
			}
		}
	}
	

//for remoting
public  WebDriver getRemoteWebDriver() throws MalformedURLException {
	if (platform.equalsIgnoreCase("WINDOWS")) {
		switch (browserName) {
		case "chrome":// chrome浏览器直接忽略版本信息
			DesiredCapabilities capability0 = DesiredCapabilities.chrome();
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

@AfterClass
	public void closeWebDriver(){
	
		driver.close();
		driver.quit();
	}
}
