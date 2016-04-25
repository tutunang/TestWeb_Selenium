package com.elong.air.AbstractObject;

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


public class AbstractPageObject {
	public WebDriver driver;
	public static final String NO_SUCH_FRAME = "no-such-frame";
	private Logger log = Logger.getLogger(AbstractPageObject.class);
	protected String name="";
	private static int tryTime = 5;
	public  final static  String path ="D:/eclipseWorkSpace/AirGUIDemo/test-output/ScreenShot/";

	
	private final int TIMEOUT = 15;
	/**
	 * 构造方法时，判断页面title是否正确
	 * @param driver
	 * @param title
	 */
	public AbstractPageObject(WebDriver driver, final String title) {
		this.driver = driver;

		WebDriverWait wait = new WebDriverWait(driver,TIMEOUT);
		try{
			boolean flag = wait.until(new ExpectedCondition<Boolean>(){
				@Override
	            public Boolean apply(WebDriver arg0) {
	                // TODO Auto-generated method stub
					String acttitle = arg0.getTitle();
	                return acttitle.equals(title);	                
	            }});
		}catch(TimeoutException te) {
			throw new IllegalStateException("当前不是预期页面，当前页面title是：" + driver.getTitle());
		}
		
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT) , this);
		
	}
	
	/*
	public AbstractPageObject(WebDriver driver, String key) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
				120);
		PageFactory.initElements(finder, this);
		boolean flag = true;
		int i = 0;
		while (flag && i < tryTime) {
			i++;
			try {
				driver.findElement(By.xpath(key));
				flag = false;
			} catch (NoSuchElementException e) {
				flag = true;
			}
			if (flag) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finder = new AjaxElementLocatorFactory(driver, 120);
				PageFactory.initElements(finder, this);
			}
			//log.info("第" + i + "次尝试。");
		}
		if (flag) {
			log.error("尝试" + i + "次后无法获取航班信息。");
			driver.close();
			driver.quit();

		
		}else{
			log.info("尝试" + i + "次后成功。");
			
		}
	}
	*/

	public AbstractPageObject(WebDriver driver) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
				120);
		PageFactory.initElements(finder, this);
	}

	public void click (WebElement element) {
		// switchFrame(element);
		takeScreenShot(driver);
		element.click();
	}

	public void setInputText(WebElement element, String text) {
		// switchFrame(element);
		element.clear();
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
//截图
	public  void  takeScreenShot(WebDriver driver) {
		try {
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
			Random rd=new Random();
			int tmp = Math.abs(rd.nextInt());	
			System.out.print(path+name+"jjjjjjjjjjjjjjjjjjjjjjjjj");
			FileUtils.copyFile(file, new File(path+name,tmp+"screenshopt.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
}


}
