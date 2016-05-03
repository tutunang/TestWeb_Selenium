package com.elong.air.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.elong.air.base.BaseBean;

public class SearchInfoBean extends BaseBean {

	public SearchInfoBean() {
		super();
	}

	public SearchInfoBean(int sheet, int caseNum) {
		super(sheet, caseNum);
		initTestData(map);
	}

	public void initTestData(Map<String, String> map) {
		setStartCity(map.get("出发城市"));
		System.out.println("<<<<<" + getStartCity() + ">>>>>");
		setEndCity(map.get("到达城市"));
		setStartDate(map.get("出发日期"));
		setReturnDate(map.get("返程日期"));
		setCabinLevel(map.get("舱位等级"));

		if (StringUtils.isNoneEmpty(map.get("单程")) && map.get("单程").equals("Y")) {
			setOneWay(true);
			System.out.println(getOneWay() + "单程");
		} else {
			setOneWay(false);
		}
		if (StringUtils.isNoneEmpty(map.get("往返")) && map.get("往返").equals("Y")) {
			setRoundWay(true);
		} else {
			setRoundWay(false);
			System.out.println(getRoundWay() + "fan程");
		}

		String[] destName = map.get("乘机人姓名").split(";");
		int peopleCount = destName.length;
		System.out.println("peopleCount------" + peopleCount);
		List<PassenagerInfoBean> passenagerInfo = new ArrayList<PassenagerInfoBean>();
		if (peopleCount > 0) {
			for (int i = 0; i < peopleCount; i++) {
				PassenagerInfoBean cop = new PassenagerInfoBean(map, i);
				passenagerInfo.add(cop);
			}
			setPassenagerInfo(passenagerInfo);
		} else {
			System.out.println("peopleCount error");
		}
		System.out.println(getOneWay() + "ong" + getRoundWay() + "round");
	}

	private String startCity;
	private String endCity;
	private Boolean oneWay;
	private Boolean roundWay;
	private String startDate;
	private String returnDate;
	private String cabinLevel;

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

	public String getCabinLevel() {
		return cabinLevel;
	}

	public void setCabinLevel(String cabinLevel) {
		this.cabinLevel = cabinLevel;
	}

}
