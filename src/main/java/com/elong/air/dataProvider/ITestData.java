package com.elong.air.dataProvider;

import java.util.Map;

import com.elong.air.tools.OptionFile;


/**
 * @author  qiaojiafei
 * @version 创建时间：2016年4月25日 下午1:55:46
 * 类说明
 */
public class ITestData {
	static String filepaString = OptionFile.readProperties("./conf/Myconfig.properties", "excel");
	public static Map<String, String> getTestData(int sheet, int caseNum) {
		Map<String, String> map = OptionFile.getExcelDataByCaseNum(filepaString, sheet, caseNum);
		return map;
	}
}
