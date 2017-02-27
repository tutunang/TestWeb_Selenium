package com.elong.air.PageObject;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.PageObject.HomePage.Zones;
import com.elong.air.base.BasePage;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.exception.AirException;
import com.elong.air.tools.Common;

public class SearchPage extends BasePage {

	public SearchPage() {
	}

	/**
	 * 如果从配置文件读取页面title，应使用该构造方法
	 * 
	 * @param driver
	 */
	public SearchPage(WebDriver driver) {
		super(driver);
	}

//	public SearchPage(WebDriver driver, String title) {
//		super(driver);
//	}

	@FindBy(xpath = "//input[@class='radio' and @method='oneway']")
	public WebElement oneWayRadio;
	@FindBy(xpath = "//input[@class='radio' and @method='round']")
	public WebElement roundRadio;

	@FindBy(xpath = "//input[@id='btnSearch']")
	public WebElement searchButton;

	@FindBy(css = "#Round_DepartCity")
	public WebElement startCity;

	@FindBy(css = "#Round_ArriveCity")
	public WebElement endCity;

	@FindBy(xpath = "//input[@name='DepartDate']")
	public WebElement startDate;

	@FindBy(xpath = "//li[@method='Round']/input[@class='intxt return']")
	public WebElement returnDate;

	@FindBy(xpath = "//div[@id='m_contentend']//div[@class='w']")
	public WebElement errorMsg;

	@FindBy(css =".com_results li")
	public WebElement key;

	@FindBy(xpath ="//div[@id='sugContainer']")
	public WebElement listOptions;

	@FindBy(css = ".a_on")
	public WebElement key1;

	public AirListsPage searchFlight(SearchInfoBean bean) throws InterruptedException {
		System.out.println(bean.getOneWay() + "in Searchpage");
		if (bean.getOneWay() == true) {

			this.setCheck(oneWayRadio);
		}

		System.out.println("ddd" + bean.getStartCity());
		if(StringUtils.isNotEmpty(bean.getStartCity()))
		 {
			System.out.println(bean.getStartCity());
			this.setInputText(startCity, bean.getStartCity());
			String str = "document.getElementById(\"Round_DepartCity\").citythreesign= '"+bean.getStartCity()+"'";
			
//			String js="$('#sugContent').find('li:first').click()";
			((JavascriptExecutor)driver).executeScript(str);
			
			// ((JavascriptExecutor)driver).executeScript();
			//getOptionFromLists(bean.getStartCity());
			//startCity.sendKeys(Keys.values());

		}
		if (StringUtils.isNotEmpty(bean.getEndCity())) {
			System.out.println(bean.getEndCity());
			this.setInputText(endCity, bean.getEndCity());
			String str = "document.getElementById(\"Round_ArriveCity\").citythreesign= '"+bean.getEndCity()+"'";
		//	String js="$('#sugContent').find('li:first').click()";
			((JavascriptExecutor)driver).executeScript(str);
			//Thread.sleep(5000);
			//getOptionFromLists(bean.getEndCity());
		}

		if (StringUtils.isNotEmpty(Common.getAfter_today_Date(3))) {
			// click(key1);
			this.setInputText(startDate, Common.getAfter_today_Date(3));
			click(key1);
		}
		if (bean.getRoundWay() == true) {
			this.setCheck(roundRadio);

			if (StringUtils.isNotEmpty(Common.getAfter_today_Date(5))) {
				// this.setInputText(returnDate, bean.getReturnDate());
				returnDate.clear();
				click(key1);
				returnDate.sendKeys(Common.getAfter_today_Date(5));
				// System.out.println(bean.getReturnDate());
				click(key1);
			}
		}
		if(StringUtils.isNotEmpty(bean.getCabinLevel())&&bean.getCabinLevel().equals("M")){
		String	classType = "document.getElementById(\"ClassType\").value= '"+bean.getCabinLevel()+"'";
		((JavascriptExecutor)driver).executeScript(classType);
		}
		
		clickSearchButton();
		try {
			if (errorMsg.isDisplayed()) {
				return null;
			}
		} catch (Exception e) {
			return new HomePage(driver).getContent(driver, Zones.MID);
		}

		return null;
		// new AirListsPage(driver,name);

	}

	public String getErrorMsg() {
		return errorMsg.getText();
	}

	public void clickSearchButton() {
		this.click(searchButton);
	}

	public void getOptionFromLists(String target) {
		System.out.println(target+"targets");
	//	List<WebElement> li=listOptions.findElements(By.xpath("//li[@title='"+target+"']"));
		List<WebElement> li=this.findElements(By.xpath("//div[@id='sugContainer']//li[@title='"+target+"']"));
		System.out.println("------------------------------------size:"+li.size());
		if(li.size()>0){
		for (WebElement e : li) {
		//	System.out.println("<<<<<"+e.getText());
			if (e.getText().trim().contains(target)) {
			click(e);
				break;
			} else {
				try {
					throw new AirException("Can't find the optionList");
				} catch (AirException e1) {
					e1.printStackTrace();
				}
			}
		}}

	}
	

	
}
