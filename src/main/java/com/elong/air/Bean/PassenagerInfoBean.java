package com.elong.air.Bean;


//@XStreamAlias("passenagerInfo")  
public class PassenagerInfoBean {
	
public String passengerName;
public String passenagerId;
public String birthDate;
public boolean insurance;
public String phoneNum;
public String emailAddress;
public int cardType;
public int insuranceAmount;

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


}
