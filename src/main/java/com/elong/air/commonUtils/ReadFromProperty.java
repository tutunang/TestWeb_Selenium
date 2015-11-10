package com.elong.air.commonUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReadFromProperty {

	
	public  Map readBankInfo() throws Exception{
//		FileInputStream aFileInputStream=new FileInputStream("src/resource/bank.properties");
//		Properties zProperties=new Properties();
//		zProperties.load(aFileInputStream);
//		String zString=zProperties.getProperty("bankAccountName");
//	    String xmString = new String(zString.toString().getBytes("UTF-8"));
//		System.out.println(xmString);
		
		Properties properties = new Properties();   
		InputStream inputStream = this.getClass().getResourceAsStream("/bank.properties");   
		BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));   
		properties.load(bf);
		
	    String bankAccountName=	properties.getProperty("bankAccountName");
	  //  String bankAccountName = new String(properties.getProperty("bankAccountName").getBytes("iso-8859-1"), "utf-8");
	    String bankAccountNum=	properties.getProperty("bankAccountNum");
	    String cardId=	properties.getProperty("cardId");
	    String month=	properties.getProperty("month");
	    String year=	properties.getProperty("year");
	    String selCertificateType=	properties.getProperty("selCertificateType");
		System.out.println(bankAccountName+bankAccountNum+cardId);  
		Map<String,String> map=new HashMap();
	    map.put("AccountName", bankAccountName);
	    map.put("AccountNum", bankAccountNum);
	    map.put("cardId", cardId);
	    map.put("month", month);
	    map.put("year", year);
	    map.put("selCertificateType", selCertificateType);
	    System.out.println(bankAccountName+"@@"+bankAccountNum+"@@"+cardId+"@@"+month+"@@"+year+"@@"+selCertificateType);
		return map;

	}
	public static void main(String[] args) throws Exception {
		ReadFromProperty s=new ReadFromProperty();
		s.readBankInfo();
	}
}
