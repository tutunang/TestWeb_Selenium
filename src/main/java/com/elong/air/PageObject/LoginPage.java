package com.elong.air.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.elong.air.base.BasePageObject;
import com.elong.air.bean.LoginInfoBean;

/** 
 * @author QiaoJiafei 
 * @version 创建时间：2016年5月3日 上午10:30:26 
 * 类说明 
 */
public class LoginPage extends BasePageObject{
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//UserName
	@FindBy(id = "UserName")
	protected WebElement input_username;
	@FindBy(xpath = "//*[@id='password_tip']/input")
	protected WebElement temp_password;
	@FindBy(id = "input_password")
	protected WebElement input_password;
	
	public HomePage validaLogin(LoginInfoBean bean) {
		this.setInputText(input_username, bean.getUsername());
		this.click(temp_password);
		//this.setInputText(input_password, bean.getPassword());
		input_password.sendKeys("123456");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
