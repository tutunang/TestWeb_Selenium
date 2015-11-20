package com.elong.air.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.AbstractObject.AbstractPageObject;
import com.elong.air.Bean.SearchInfoBean;

public class SearchPage extends AbstractPageObject {
 String name="";
	public SearchPage(WebDriver driver,String ClassName) {
		super(driver);
		super.name=ClassName;
		this.name=ClassName;
	}
/**
 * */

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

	@FindBy(css = ".com_results li")
	public WebElement key;

	@FindBy(css = ".a_on")
	public WebElement key1;

	public AirListsPage searchFlight(SearchInfoBean bean) {
		if (bean.getOneWay() == true) {
			this.setCheck(oneWayRadio);
		}

		System.out.println("ddd" + bean.getStartCity());
		if (bean.getStartCity() != null && !bean.getStartCity().equals("")) {
			System.out.println(bean.getStartCity());
			this.setInputText(startCity, bean.getStartCity());
			// TakeScreenShot.takeScreenShot(driver);
			click(key);

		}
		if (bean.getEndCity() != null && !bean.getEndCity().equals("")) {
			System.out.println(bean.getEndCity());
			this.setInputText(endCity, bean.getEndCity());
			// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			click(key1);
		}

		if (bean.getStartDate() != null && !bean.getStartDate().equals("")) {
			// click(key1);
			this.setInputText(startDate, bean.getStartDate());
			click(key1);
		}
		if (bean.getRoundWay() == true) {
			this.setCheck(roundRadio);

			if (bean.getReturnDate() != null
					&& !bean.getReturnDate().equals("")) {
				// this.setInputText(returnDate, bean.getReturnDate());
				returnDate.clear();
				click(key1);
				returnDate.sendKeys(bean.getReturnDate());
				// System.out.println(bean.getReturnDate());
				click(key1);
			}
		}
		clickSearchButton();
		try{if(errorMsg.isDisplayed()){
			return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			return new AirListsPage(driver,name);
		}
		
		return new AirListsPage(driver,name);

	
		}

	public String getErrorMsg(){
		return 
			errorMsg.getText();
	}
	public void clickSearchButton(){
		this.click(searchButton);
	}
}
