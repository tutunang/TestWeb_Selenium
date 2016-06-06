package com.elong.air.bean;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.elong.air.base.BaseBean;

public class PassenagerInfoBean extends BaseBean {

	PassenagerInfoBean() {
	}

	/**
	 * 如果没有业务关联，要初始化该bean，应使用该构造方法
	 * 
	 * @param sheet
	 * @param caseNum
	 */
	PassenagerInfoBean(int sheet, int caseNum) {
		super(sheet, caseNum);
		//initTestData(map);
	}

//	PassenagerInfoBean(Map<String, String> map) {
//		initTestData(map);
//	}

	PassenagerInfoBean(Map<String, String> map, int index) {
		initTestData(map, index);
	}
	private String passengerName;
	private String passenagerId;
	private String birthDate;
	private boolean insurance;
	private String phoneNum;
	private String emailAddress;
	private int cardType;
	private int insuranceAmount;

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(int insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getPassenagerId() {
		return passenagerId;
	}

	public void setPassenagerId(String passenagerId) {
		this.passenagerId = passenagerId;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 该方法专门用于SerchInfoBean调用
	 * 
	 * @Title: initTestData
	 * @param map
	 * @param index
	 * @return void
	 * @throws
	 */
	private void initTestData(Map<String, String> map, int index) {
		// TODO Auto-generated method stub
		//String[] destName = map.get("乘机人姓名").split(";");
		if(StringUtils.isNotEmpty(map.get("乘机人姓名"))){
			String[] destName = map.get("乘机人姓名").split(";");
			String name = destName[index];
			setPassengerName(name);
		}
		if(StringUtils.isNotEmpty(map.get("证件类型"))){
			String[] destTpye = map.get("证件类型").split(";");
			String type = destTpye[index];
			int parseInt = Integer.parseInt(type);
			setCardType(parseInt);
		}
		if(StringUtils.isNotEmpty(map.get("证件号码"))){
			String[] destCardNum = map.get("证件号码").split(";");
			String cardNum = destCardNum[index];
			setPassenagerId(cardNum);
		}
			if(StringUtils.isNotEmpty(map.get("出生日期"))){
				String[] destBirthDate = map.get("出生日期").split(";");
				String birthDate = destBirthDate[index];
				setBirthDate(birthDate);
			}
			if(StringUtils.isNotEmpty(map.get("购买保险"))){
				String[] destInsuranceAmount = map.get("购买保险").split(";");
				String amount = destInsuranceAmount[index];
				int amountInsurance = Integer.parseInt(amount);
				setInsuranceAmount(amountInsurance);
			}
	//	String[] destTpye = map.get("证件类型").split(";");
	//	String[] destCardNum = map.get("证件号码").split(";");
	//	String[] destBirthDate = map.get("出生日期").split(";");
	//	String[] destInsuranceAmount = map.get("购买保险").split(";");

	//	String name = destName[index];
	//	String type = destTpye[index];
	//	int parseInt = Integer.parseInt(type);
	//	String cardNum = destCardNum[index];
		//String birthDate = destBirthDate[index];
	//	String amount = destInsuranceAmount[index];
	//	int amountInsurance = Integer.parseInt(amount);

	//	setPassengerName(name);
	//	setCardType(parseInt);
	//	setPassenagerId(cardNum);
	//	setBirthDate(birthDate);
	//	setInsuranceAmount(amountInsurance);
			if(StringUtils.isNotEmpty(map.get("手机号"))){
		        setPhoneNum(map.get("手机号"));
		}
			if(StringUtils.isNotEmpty(map.get("email"))){
		        setPhoneNum(map.get("email"));
		}
	//	setEmailAddress(map.get("email"));

	}


//	@Override
//	public void initTestData(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		String destName = map.get("乘机人姓名");
//		String destTpye = map.get("证件类型");
//		String destCardNum = map.get("证件号码");
//		String destBirthDate = map.get("出生日期");
//		String destInsuranceAmount = map.get("购买保险");
//
//		setPassengerName(destName);
//		setCardType(Integer.parseInt(destTpye));
//		setPassenagerId(destCardNum);
//		setBirthDate(destBirthDate);
//		setInsuranceAmount(Integer.parseInt(destInsuranceAmount));
//		setPhoneNum(map.get("手机号"));
//		setEmailAddress(map.get("email"));
//
//	}

}
