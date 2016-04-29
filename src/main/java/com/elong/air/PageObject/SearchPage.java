package com.elong.air.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.PageObject.HomePage.Zones;
import com.elong.air.base.BasePageObject;
import com.elong.air.bean.SearchInfoBean;
import com.elong.air.exception.AirException;

public class SearchPage extends BasePageObject {

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

	public SearchPage(WebDriver driver, String title) {
		super(driver, title);
	}

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

	@FindBy(xpath = "//div[@id='sugContainer']")
	public List<WebElement> listOptions;

	@FindBy(css = ".a_on")
	public WebElement key1;

	public AirListsPage searchFlight(SearchInfoBean bean) {
		System.out.println(bean.getOneWay() + "in Searchpage");
		if (bean.getOneWay() == true) {

			this.setCheck(oneWayRadio);
		}

		System.out.println("ddd" + bean.getStartCity());
		if (bean.getStartCity() != null && !bean.getStartCity().equals("")) {
			System.out.println(bean.getStartCity());
			this.setInputText(startCity, bean.getStartCity());
			// TakeScreenShot.takeScreenShot(driver);
			getOptionFromLists(bean.getStartCity());
			// click(key);

		}
		if (bean.getEndCity() != null && !bean.getEndCity().equals("")) {
			System.out.println(bean.getEndCity());
			this.setInputText(endCity, bean.getEndCity());
			// driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			// SelectUtils s=new SelectUtils();
			// s.selectByText(listOption, bean.getEndCity());
			// click(key1);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getOptionFromLists(bean.getEndCity());
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
		for (WebElement e : listOptions) {
			if (e.getText().trim().contains(target)) {
				e.click();
			} else {

				try {
					throw new AirException("Can't find the optionList");
				} catch (AirException e1) {
					e1.printStackTrace();
				}
			}
		}

	}
}
