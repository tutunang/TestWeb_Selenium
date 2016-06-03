package com.elong.air.PageObject;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.elong.air.base.BasePage;
import com.elong.air.tools.ReadFromProperty;
import com.elong.air.tools.SelectUtils;

public class PaymentPage extends BasePage{

	public PaymentPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css="#txtVerificationCode")
	public WebElement VerificationCodeTextField;
	
	@FindBy(css="#txtCreditCard")
	public WebElement creditCardInputTextField;
	
	@FindBy(css="#txtCardName")
	public WebElement accountNameInputTextField;
	
	@FindBy(css="#selValidityMonth")
	public WebElement monthTextField;
	
	@FindBy(css="#selCertificateType")
	public WebElement Cardtype;
	
	@FindBy(css="#selValidityYear")
	public WebElement yearTextField;
	
	@FindBy(css="#txtCertificate")
	public WebElement cardIdInputTextField;
	
	@FindBy(css="#btnPaymentSumbit")
	public WebElement summitButton;
	
	public FinishedOrderPage inputBankInformation() throws Exception {
		ReadFromProperty rfp = new ReadFromProperty();
		Map<String, String> bankaInfo = rfp.readBankInfo();
		System.out.print("名字" + bankaInfo.get("AccountName"));
		System.out.print("银行" + bankaInfo.get("AccountNum"));
		System.out.print("身份证" + bankaInfo.get("cardId"));
		System.out.print("类型" + bankaInfo.get("selCertificateType"));
		System.out.print("三位数" + bankaInfo.get("code"));
		this.setInputText(creditCardInputTextField, bankaInfo.get("AccountNum"));
		this.setInputText(accountNameInputTextField,
				bankaInfo.get("AccountName"));
		SelectUtils su = new SelectUtils();
		su.selectByValue(monthTextField, bankaInfo.get("month"));
		su.selectByValue(yearTextField, bankaInfo.get("year"));
		System.out.println(Cardtype+">>>>>>");
		Select s=new Select(Cardtype);
		s.selectByValue(bankaInfo.get("selCertificateType"));
		//su.selectByValue(Cardtype, bankaInfo.get("selCertificateType"));
		this.setInputText(cardIdInputTextField, bankaInfo.get("cardId"));
		this.setInputText(VerificationCodeTextField, bankaInfo.get("code"));
		this.click(summitButton);
return new FinishedOrderPage(driver);
	}
//	public static void main(String[] args) throws Exception {
//		WebDriver f=new FirefoxDriver();
//		f.get("https://secure.elong.com/EPAY/cn/creditcardextend/056604839200492137flight");
//		PaymentPage p=new PaymentPage(f);
//		p.inputBankInformation();
//		
//	}

}
