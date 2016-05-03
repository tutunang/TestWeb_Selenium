package com.elong.air.tools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.elong.air.base.BaseEventListener;

/**
 * 用来初始化Webdriver和销毁Webdriver
 * 
 * @author duwenjing update by QiaoJiafei
 * @version 创建时间：2016年4月29日 上午9:26:38 类说明
 */
public class DriverFactory {
	public static WebDriver driver;
	private static final String REMOTEFLAG = OptionFile.readProperties(
			"./src/main/resources/config.properties", "remoteflag");
	private static final String BROWSER = OptionFile.readProperties(
			"./src/main/resources/config.properties", "browser");
	private static final String PLATFORM = OptionFile.readProperties(
			"./src/main/resources/config.properties", "platform");
	private static final int TIMEOUT = Integer
			.parseInt(OptionFile.readProperties(
					"./src/main/resources/config.properties", "timeout"));

	private static Logger log = Logger.getLogger(DriverFactory.class);
	
	/**
	 * 根据配置文件初始化Webdriver
	 * 
	 * @Title: setUpDriver
	 * @return
	 * @return WebDriver
	 * @throws
	 */
	public static WebDriver setUpDriver() {
		log.debug("------启动浏览器，准备测试环境是(系统："+PLATFORM+"，浏览器是："+BROWSER+"，是否使用远程服务："+REMOTEFLAG+"）------");
		if (StringUtils.isNotEmpty(REMOTEFLAG)
				&& REMOTEFLAG.equalsIgnoreCase("N")) {
			if (StringUtils.isNotEmpty(PLATFORM)
					&& PLATFORM.equalsIgnoreCase("WINDOWS")) {
				driver = getLocalDriverOnWINDOW(driver, BROWSER);
			} else if (StringUtils.isNotEmpty(PLATFORM)
					&& PLATFORM.equalsIgnoreCase("MAC")) {
				driver = getLocalDriverOnMAC(driver, BROWSER);
			} else {
				System.out.println("remoteflag n,platform error");
			}
		} else if (StringUtils.isNotEmpty(REMOTEFLAG)
				&& REMOTEFLAG.equalsIgnoreCase("Y")) {

			if (StringUtils.isNotEmpty(PLATFORM)
					&& PLATFORM.equalsIgnoreCase("WINDOWS")) {
				driver = getRemoteDriverOnWINDOW(driver, BROWSER);
			} else if (StringUtils.isNotEmpty(PLATFORM)
					&& PLATFORM.equalsIgnoreCase("MAC")) {
				driver = getRemoteDriverOnMAC(driver, BROWSER);
			} else {
				System.out.println("remoteflag y, platform error");
			}
		} else {
			System.out.println("remoteflag error");
		}

		return driver;
	}

	private static EventFiringWebDriver registerEvent(WebDriver driver) {
		EventFiringWebDriver event = new EventFiringWebDriver(driver);
		BaseEventListener eventlis = new BaseEventListener();
		event.register(eventlis);
		return event;
	}

	/**
	 * 销毁Webdriver
	 * 
	 * @Title: tearDownDriver
	 * @param driver
	 * @return void
	 * @throws
	 */
	public static void tearDownDriver(WebDriver driver) {
		log.debug("------退出浏览器------");
		driver.quit();
	}

	private static void initOption(WebDriver driver, int timeout) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	private static WebDriver getRemoteDriverOnMAC(WebDriver driver,
			String browser) {
		final String hubURL = OptionFile.readProperties(
				"./src/main/resources/config.properties", "remoteflag");
		DesiredCapabilities capability = null;

		switch (browser) {
		case "safari":
			capability = DesiredCapabilities.safari();
			capability.setPlatform(Platform.MAC);
			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
				driver = registerEvent(driver);
				initOption(driver, TIMEOUT);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "chrome":
			capability = DesiredCapabilities.chrome();
			capability.setPlatform(Platform.MAC);
			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
				driver = registerEvent(driver);
				initOption(driver, TIMEOUT);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "firefox":
			capability = DesiredCapabilities.firefox();
			capability.setPlatform(Platform.MAC);
			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
				driver = registerEvent(driver);
				initOption(driver, TIMEOUT);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		return driver;
	}

	private static WebDriver getRemoteDriverOnWINDOW(WebDriver driver,
			String browser) {
		final String hubURL = OptionFile.readProperties(
				"./src/main/resources/config.properties", "remoteflag");
		DesiredCapabilities capability = null;

		switch (browser) {
		case "safari":
			capability = DesiredCapabilities.safari();
			capability.setPlatform(Platform.WINDOWS);
			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
				driver = registerEvent(driver);
				initOption(driver, TIMEOUT);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case "chrome":
			capability = DesiredCapabilities.chrome();
			capability.setPlatform(Platform.WINDOWS);
			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
				driver = registerEvent(driver);
				initOption(driver, TIMEOUT);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		case "firefox":
			capability = DesiredCapabilities.firefox();
			capability.setPlatform(Platform.WINDOWS);
			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
				driver = registerEvent(driver);
				initOption(driver, TIMEOUT);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
		}

		return driver;
	}

	private static WebDriver getLocalDriverOnMAC(WebDriver driver,
			String browser) {
		String key = "";
		String value = "";
		String driverpath = OptionFile.readProperties(
				"./src/main/resources/config.properties", "driverpath");

		switch (browser) {
		case "safari":
			driver = new SafariDriver();
			driver = registerEvent(driver);
			initOption(driver, TIMEOUT);

			break;

		case "chrome":
			key = "webdriver.chrome.driver";
			value = driverpath;
			System.setProperty(key, value);
			driver = new ChromeDriver();
			driver = registerEvent(driver);
			initOption(driver, TIMEOUT);

			break;
		case "firefox":
			key = "webdriver.firefox.bin";
			value = driverpath;
			System.setProperty(key, value);
			driver = new FirefoxDriver();
			driver = registerEvent(driver);
			initOption(driver, TIMEOUT);

			break;
		}
		return driver;
	}

	private static WebDriver getLocalDriverOnWINDOW(WebDriver driver,
			String browser) {
		String key = "";
		String value = "";
		String driverpath = OptionFile.readProperties(
				"./src/main/resources/config.properties", "driverpath");

		switch (browser) {
		case "chrome":
			key = "webdriver.chrome.driver";
			value = driverpath;
			System.setProperty(key, value);
			driver = new ChromeDriver();
			driver = registerEvent(driver);
			initOption(driver, TIMEOUT);

			break;
		case "firefox":
			key = "webdriver.firefox.bin";
			value = driverpath;
			System.setProperty(key, value);
			driver = new FirefoxDriver();
			driver = registerEvent(driver);
			initOption(driver, TIMEOUT);

			break;
		}
		return driver;
	}

}
