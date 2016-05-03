package com.elong.air.base;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.elong.air.tools.OptionFile;

public class BasePageObject {

	public static final String NO_SUCH_FRAME = "no-such-frame";
	protected String name = "";

	public WebDriver driver;
	//private Logger log = Logger.getLogger(BasePageObject.class);
	protected Logger log = Logger.getLogger(this.getClass());
	// 全局的超时时间设置
	private final int TIMEOUT = Integer.parseInt(OptionFile.readProperties(
			"./src/main/resources/config.properties", "timeout"));;

	/**
	 * 构造方法时，判断页面title是否正确,使用传入的title判断页面title
	 * 
	 * @param driver
	 * @param title
	 */
	public BasePageObject(WebDriver driver, final String title) {
		log.debug("------使用BasePageObject(WebDriver driver)构造方法开始------");
		
		//final String pagetitle = this.getClass().getCanonicalName();
		this.driver = driver;

		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		try {
			boolean flag = wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver arg0) {
					// TODO Auto-generated method stub
					String acttitle = arg0.getTitle();
					log.debug("------预期页面title是："+title+"，实际页面title是：acttitle");
					return acttitle.equals(title);
				}
			});
		} catch (TimeoutException te) {
			throw new IllegalStateException("当前不是预期页面，当前页面title是："
					+ driver.getTitle());
		}

		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, TIMEOUT), this);

	}

	/***
	 * 构造方法时，判断页面title是否正确,使用pagetitle.properties读取的title判断页面title
	 * 
	 * @param driver
	 */
	public BasePageObject(WebDriver driver) {
		log.debug("------使用BasePageObject(WebDriver driver)构造方法开始------");
		
		final String pagetitle = this.getClass().getCanonicalName();
		final String title = OptionFile.readProperties(
				"./src/main/resources/pagetitle.properties", pagetitle);
		this.driver = driver;
		System.out.println("当前page类是：" + pagetitle + " ;取到的title是：" + title);
		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		try {
			boolean flag = wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver arg0) {
					// TODO Auto-generated method stub
					String acttitle = arg0.getTitle();
					log.debug("------取到的预期页面title是："+title+"，实际页面title是：acttitle");
					return acttitle.equals(title);
				}
			});
		} catch (TimeoutException te) {
			throw new IllegalStateException("当前不是预期页面，当前页面title是："
					+ driver.getTitle());
		}

		PageFactory.initElements(
				new AjaxElementLocatorFactory(driver, TIMEOUT), this);
	}

	public BasePageObject() {
	}

	// -------------------------常用方法封装--------------------------------------------------------------------------------
	public void click(WebElement element) {
		// switchFrame(element);
		takeScreenShot(driver);
		element.click();
	}

	public void setInputText(WebElement element, String text) {
		// switchFrame(element);
		System.out.println("--------sendkeys:"+text);
		//element.clear();//暂时注销，登录密码框会报错
		element.sendKeys(text);
	}

	public void setCheck(WebElement element) {
		if (element.isSelected()) {
			return;
		} else {
			element.click();
		}

	}

	public static void switchFrame(WebDriver driver, String frameName) {
		if (frameName == null) {
			return;
		}
		driver.switchTo().defaultContent();
		if (frameName.equals(NO_SUCH_FRAME)) {
			return;
		}
		if (!"".equals(frameName)) {
			if (frameName.indexOf('|') != -1) {
				String[] subFrames = frameName.split("[|]");
				for (String subFrame : subFrames) {
					WebElement element = driver.findElement(By
							.cssSelector(subFrame));
					if (element == null
							|| !element.getTagName().contains("frame")) {
						return;
					}
					driver.switchTo().frame(element);
				}
			} else {
				String[] subFrames = frameName.split("[.]");
				for (String subFrame : subFrames) {
					driver.switchTo().frame(subFrame);
				}
			}
		}
	}

	public void switchWindow() {
		String currentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String s : handles) {
			if (currentWindow.equals(s)) {
				continue;
			}
			driver.switchTo().window(s);

		}

	}

	public void switchWindow1() {
		String currentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			String handle = it.next();
			if (currentWindow.equals(handle))
				continue;
			driver.switchTo().window(handle);
		}
	}

	// 截图
	public void takeScreenShot(WebDriver driver) {
		final String path = "D:/eclipseWorkSpace/AirGUIDemo/test-output/ScreenShot/";
		try {
			File file = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			Random rd = new Random();
			int tmp = Math.abs(rd.nextInt());
			System.out.print(path + name + "jjjjjjjjjjjjjjjjjjjjjjjjj");
			FileUtils.copyFile(file, new File(path + name, tmp
					+ "screenshopt.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
