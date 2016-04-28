package com.elong.air.dataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.elong.air.Bean.PassenagerInfoBean;
import com.elong.air.Bean.SearchInfoBean;
import com.elong.air.tools.OptionFile;


/**
 * @author  qiaojiafei update by duwenjing
 * @version 创建时间：2016年4月25日 下午1:55:46
 * 类说明
 */
public class ITestData {
	static String filepaString = OptionFile.readProperties("D:/eclipseWorkSpace/AirGUIDemo/src/main/resources/config.properties", "excel");
	public static Map<String, String> getTestData(int sheet, int caseNum) {
		Map<String, String> map = OptionFile.getExcelDataByCaseNum(filepaString, sheet, caseNum);
		return map;
	}
	
	
	public static SearchInfoBean getTestDataConvertToBean(int sheet, int caseNum) {
		Map<String, String> map = OptionFile.getExcelDataByCaseNum(filepaString, sheet, caseNum);
		SearchInfoBean searchBean=new SearchInfoBean();	
		List<PassenagerInfoBean> passenagerInfo=new ArrayList<>();
		searchBean.setPassenagerInfo(passenagerInfo);
		
		
		searchBean.setStartCity(map.get("出发城市"));
		System.out.println("<<<<<"+searchBean.getStartCity()+">>>>>");
		searchBean.setEndCity(map.get("到达城市"));	
		searchBean.setStartDate(map.get("出发日期"));	
		searchBean.setReturnDate(map.get("返程日期"));
		searchBean.setCabinLevel(map.get("舱位等级"));
		

		if(StringUtils.isNoneEmpty(map.get("单程"))&&map.get("单程").equals("Y")){
			searchBean.setOneWay(true);
		System.out.println(	searchBean.getOneWay()+"单程");
		}	else{
			searchBean.setOneWay(false);
		}
		if(StringUtils.isNoneEmpty(map.get("往返"))&&map.get("往返").equals("Y")){
			searchBean.setRoundWay(true);
		}else{
			searchBean.setRoundWay(false);
			System.out.println(	searchBean.getRoundWay()+"fan程");
		}
			
		map.get("单程").equalsIgnoreCase("true");
		
		String[] destName = map.get("乘机人姓名").split(";");
		String[] destTpye = map.get("证件类型").split(";");
		String[] destCardNum = map.get("证件号码").split(";");
		String[] destBirthDate = map.get("出生日期").split(";");
		String[] destInsuranceAmount = map.get("购买保险").split(";");
		int peopleCount=destName.length;
		PassenagerInfoBean passenagerInfoBean=null;
		for (int i = 0; i < peopleCount; i++) {
		    passenagerInfoBean =new PassenagerInfoBean();
			String name = destName[i];
			String type = destTpye[i];
			int parseInt = Integer.parseInt(type);
			String cardNum = destCardNum[i];
			String birthDate=destBirthDate[i];
			String amount=destInsuranceAmount[i];
			int amountInsurance = Integer.parseInt(amount);
			
			passenagerInfoBean.setPassengerName(name);
			passenagerInfoBean.setCardType(parseInt);
			passenagerInfoBean.setPassenagerId(cardNum);
			passenagerInfoBean.setBirthDate(birthDate);
			passenagerInfoBean.setInsuranceAmount(amountInsurance);
			passenagerInfoBean.setPhoneNum(map.get("手机号"));
			passenagerInfoBean.setEmailAddress(map.get("email"));
			searchBean.getPassenagerInfo().add(passenagerInfoBean);
		}
		
		
		System.out.println(searchBean.getOneWay()+"ong"+searchBean.getRoundWay()+"round");

		return searchBean;
	}
	public static void main(String[] args) {
		 String originalString = new String("5;8;6;9;12;56;3");
		   String[]  destString = originalString.split(";"); 
		   for(int i=0; i < destString.length; i++)  
		     System.out.print(destString[i]+" ");
		    
		     System.out.println( );  
		
	}
}
