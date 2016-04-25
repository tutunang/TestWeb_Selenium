package com.elong.air.AbstractObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * @author  qiaojiafei
 * @version 创建时间：2016年4月25日 下午1:03:16
 * 类说明
 */
public class BaseTestClass {
	protected WebDriver driver;   

	protected BaseTestClass() {
		
	}
	
	protected BaseTestClass(WebDriver driver) {
		this.driver = driver;
	}
	
	protected void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	protected WebDriver getDriver() {  
		return driver;  
	} 
	
	/** 
	* @Title: takeScreenShort 
	* @param result
	* @return void
	* @throws 
	*/
	protected void takeScreenShort(ITestResult result) {
	     Object currentClass = result.getInstance();  
	     WebDriver webDriver = ((BaseTestClass) currentClass).getDriver();  
	  
	     if (webDriver != null) {  
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String dateString = formatter.format(currentTime);
			File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			try {
				String filename = dateString+result.getName()+ ".png";
				File screenshot = new File("screenshotimg/" + filename);
				FileUtils.copyFile(scrFile,screenshot);
				
				Reporter.setCurrentTestResult(result);
				String filepath = screenshot.getAbsolutePath();								
				Reporter.log("<a href=\"" + filepath + "\" target=\"_blank\">Failed Screen Shot</a>",true);
				
				//HTMLReporter html = new HTMLReporter();
				//Reporter r = html.generateReport(List<XmlSuite> xmlSuites,List<ISuite> suites, "sdfsdf");
				//r.log("<a href=\"" + filepath + "\" target=\"_blank\">Failed Screen Shot</a>",true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	               
	     } 
	}
}
