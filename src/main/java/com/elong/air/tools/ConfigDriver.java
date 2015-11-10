package com.elong.air.tools;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ConfigDriver {
public	WebDriver driver;

@BeforeClass
public void setUpDriver() throws IOException{
       ProfilesIni allProfiles = new ProfilesIni();
       FirefoxProfile firefoxProfile = allProfiles.getProfile("default");
       driver = new FirefoxDriver(firefoxProfile);
   	   driver.get("http://flight.elong.com");
	}
@AfterClass
	public void closeWebDriver(){
	
		driver.close();
		driver.quit();
	}
}
