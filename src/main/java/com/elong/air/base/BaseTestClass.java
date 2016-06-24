package com.elong.air.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.elong.air.tools.Common;
import com.elong.air.tools.DriverFactory;
import com.elong.air.tools.OptionFile;

/**
 * 顶层Test类，com.elong.air.tests下面的所有test类都应继承该类，该类调用了WebDriver的初始化及销毁方法
 * 
 * @author qiaojiafei
 * @version 创建时间：2016年4月25日 下午1:03:16 类说明
 */
public class BaseTestClass {
	protected WebDriver driver;
	protected Logger log = Logger.getLogger(this.getClass());
	
	protected BaseTestClass() {

	}

	public BaseTestClass(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	@BeforeClass
	public void befclass() {
		log.debug("------执行beforeclass开始------");
		driver = DriverFactory.setUpDriver();
		visitorURL();
		log.debug("------执行beforeclass结束------");
	}

	@AfterClass
	public void aftclass() {
		log.debug("------执行afterclass开始------");
		DriverFactory.tearDownDriver(driver);
		log.debug("------执行afterclass结束------");
	}
	
	@BeforeSuite
	public void cleanScreenShot() {
		log.debug("------执行@BeforeSuites开始------");
        Common.deleteDir(new File("ExceptionScreenshotImg"));
		log.debug("------执行@BeforeSuite结束------");
	}
	
	private void visitorURL() {
		String url = OptionFile.readProperties("./src/test/resources/logininfo.properties", "URL");
		driver.get(url);
	}

	/**
	 * 自动截图实现方法，以测试类名为文件夹存储失败的截图
	 * 
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
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd-HH-mm-ss");
			String dateString = formatter.format(currentTime);
			File scrFile = ((TakesScreenshot) webDriver)
					.getScreenshotAs(OutputType.FILE);
			try {
				String fileclass = result.getMethod().getRealClass().getName();
				String filename = fileclass + "-" + result.getName() + "-"
						+ dateString + ".png";

				File file = new File("ExceptionScreenshotImg/" + fileclass);
				if (!file.exists()) {
					file.mkdir();
				}
				File screenshot = new File(file, filename);
				FileUtils.copyFile(scrFile, screenshot);

				Reporter.setCurrentTestResult(result);
				String filepath = screenshot.getAbsolutePath();
				//System.out.println("-------------失败截图路径----------------"
				//		+ filepath);
				log.debug("------发生异常，自动截图，图片存放路径是："+filepath+"------");
				// Reporter.log("<a href=\"" + filepath +
				// "\" target=\"_blank\">Failed Screen Shot</a>",true);
				Reporter.log("Failed Screen Shot Path：" + filepath, true);

				// HTMLReporter html = new HTMLReporter();
				// Reporter r = html.generateReport(List<XmlSuite>
				// xmlSuites,List<ISuite> suites, "sdfsdf");
				// r.log("<a href=\"" + filepath +
				// "\" target=\"_blank\">Failed Screen Shot</a>",true);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
