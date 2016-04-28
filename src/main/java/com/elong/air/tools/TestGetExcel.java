package com.elong.air.tools;

import java.util.List;
import java.util.Map;

public class TestGetExcel {

	public static void main(String[] args) {
//	OptionFile op=new OptionFile();
//	String s=op.getExcel("D:/eclipseWorkSpace/AirGUIDemo/src/test/resources/testdata.xls", 1, 3, 4);
//System.out.println(s);
//Map <String ,String>dd=op.getExcelDataByCaseNum("D:/eclipseWorkSpace/AirGUIDemo/src/test/resources/testdata.xls", 1, 3);
//System.out.println(dd.get("出发城市"));
//System.out.println(dd.get("email"));
List list=OptionFile.getExcelPriority("D:/eclipseWorkSpace/AirGUIDemo/src/test/resources/testdata.xls", 1, "P1");
System.out.println(list.get(0));
System.out.println(list.get(1));

	}

}
