package com.elong.air.base;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
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
			wait.until(new ExpectedCondition<Boolean>() {
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
			wait.until(new ExpectedCondition<Boolean>() {
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

	/*-------------------------常用方法封装------------------------------------------------------------*/
	public void click(WebElement element) {
		// switchFrame(element);
		takeScreenShot(driver);
		element.click();
	}

	public void setInputText(WebElement element, String text) {
		// switchFrame(element);
		System.out.println("--------sendkeys:"+text);
		element.clear();//暂时注销，登录密码框会报错
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
	/*----------------------------qiaojiafei----------------------------*/
	/**
	 * 刷新页面
	 */
	public void refreshPage() {
		driver.navigate().refresh();
	}
	/**
	 * 浏览器前进
	 */
	public void forwardPage() {
		driver.navigate().forward();
	}
	/**
	 * 浏览器后退
	 */
	public void backPage() {
		driver.navigate().back();
	}
	/**
	 * 确认Alert弹框
	 */
	public void alertConfirm() {
		Alert alert = driver.switchTo().alert();
		try {
			alert.accept();
		} catch (Exception notFindAlert) {
			throw notFindAlert;
		}
	}
	/**
	 * 关闭Alter弹框
	 */
	public void alertDismiss() {
		Alert alert = driver.switchTo().alert();
		try {
			alert.dismiss();
		} catch (Exception notFindAlert) {
			throw notFindAlert;
		}
	}
	/**
	 * 获得Alter弹框文本内容
	 * @return
	 */
	public String getAlertText() {
		Alert alert = driver.switchTo().alert();
		try {
			return alert.getText();
		} catch (Exception notFindAlert) {
			throw notFindAlert;
		}
	}
	/**
	 * 执行JavaScript
	 * @param js
	 */
	public void executorJS(String js) {
		JavascriptExecutor jsexe = (JavascriptExecutor) driver;
		jsexe.executeScript(js);

	}
	/**
	 * 跳转至默认容器，即最顶层Frame
	 */
	public void toDefaultContent() {
		driver.switchTo().defaultContent();
	}
	/**
	 * 跳转至父级(上一层)Frame
	 */
	public void toParentFrame() {
		driver.switchTo().parentFrame();
	}
	/**
	 * 跳转至Frame，可传参数类型：String、Integer、WebElment
	 * @param ob
	 */
	public void toFrame(Object ob) {
		if(ob instanceof  String) {
			String s = (String)ob;
			driver.switchTo().frame(s);
		}else if (ob instanceof  WebElement) {
			WebElement we = (WebElement)ob;
			driver.switchTo().frame(we);
		}else if(ob instanceof  Integer){
			Integer it = (Integer)ob;
			driver.switchTo().frame(it.intValue());
		}else{
			log.debug("");
		}
	}
	/**
	 * 使用JS给富文本框传值
	 * @param element
	 * @param text
	 */
	public void setRichTextBox(WebElement element, String text) {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].innerHTML = \"" + text + "\"", element);
	}
	/**
	 * 使用JS获取富文本框内容
	 * @param element
	 * @param text
	 * @return
	 */
	public String getRichTextBox(WebElement element, String text) {	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String result = (String) js.executeScript(
				"arguments[0].getInnerHTML()", element);
		return result;
	}
	/**
	 * 使用Actions来滑动滚动条到页面底部
	 */
	public void scrollToBottom() {
		Actions a = new Actions(driver);
		a.sendKeys(Keys.END).perform();
	}
	/**
	 * 使用Actions来滑动滚动条到页面顶部
	 */
	public void scrollToTop() {
		Actions a = new Actions(driver);
        a.sendKeys(Keys.HOME).perform();
	}
	/**
	 * 使用Actions来滑动滚动条到指定元素
	 */
	public void scrollToElement(WebElement element) {
		Actions a = new Actions(driver);
	    a.moveToElement(element).perform();
	}
	
	public void selectByIndex(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);;
	}
	
	public void selectByValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	public void selectByVisibleText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	/**
	 * 得到Select对象
	 * @param element
	 * @return
	 */
	public Select getSelect(WebElement element) {		
		Select select = new Select(element);		
		return select;
	}
	
	public void selectByIndex(Select select, int index) {
		select.selectByIndex(index);;
	}
	
	public void selectByValue(Select select, String value) {
		select.selectByValue(value);
	}
	public void selectByVisibleText(Select select, String text) {
		select.selectByVisibleText(text);
	}
	/**
	 * 判断元素是否可见
	 * @param by
	 * @param time
	 * @return
	 */
	public boolean isElementPresent(final By by, int time) {
        boolean isPresent = false;
        WebDriverWait wait = new WebDriverWait(driver, time);
        isPresent = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver dr) {
                return dr.findElement(by);
            }
        }).isDisplayed();
        return isPresent;
	}
	/**
	 * 切换浏览器标签至新打开窗口，当前浏览器打开多个(>2)浏览器标签时
	 * @param set
	 */
	public void switchToWindow(Set<String> set) {
	   Set<String> setCur = driver.getWindowHandles();
	   if(setCur.containsAll(set)) {
		   setCur.removeAll(set);
	   }
	   String newwindow = Arrays.toString(setCur.toArray());
	   driver.switchTo().window(newwindow);
	}
	/**
	 * 切换浏览器标签至新打开窗口
	 * @param set
	 */
	public void switchToWindow() {
	   String curW = driver.getWindowHandle();
	   Set<String> set = driver.getWindowHandles();
	   for(String s:set) {
		   if(!s.equals(curW)) {
			   driver.switchTo().window(s);
		   }
	   }
	}
	public void testriver() {
	  
	}
	/*----------------------------qiaojiafei----------------------------*/
}
