package com.elong.air.Bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
//xml中的根节点，给包名.类名起个别名叫DataBean
@XStreamAlias("DataBean")
public class SearchInfoBean {
	private String startCity;
	private String endCity;
	private Boolean oneWay;
	private Boolean roundWay;
	private String startDate;
	private String returnDate;
	//XStream在这里提供了一个@XStreamImplicit(itemFieldName=***)的annotation来满足用户想将List的根节点去掉和改变列表名字的需求，
 @XStreamImplicit()  
	private List<PassenagerInfoBean> passenagerInfo;
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getEndCity() {
		return endCity;
	}
	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}
	public Boolean getOneWay() {
		return oneWay;
	}
	public void setOneWay(Boolean oneWay) {
		this.oneWay = oneWay;
	}
	public Boolean getRoundWay() {
		return roundWay;
	}
	public void setRoundWay(Boolean roundWay) {
		this.roundWay = roundWay;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public List<PassenagerInfoBean> getPassenagerInfo() {
		return passenagerInfo;
	}
	public void setPassenagerInfo(List<PassenagerInfoBean> passenagerInfo) {
		this.passenagerInfo = passenagerInfo;
	}
	
}
