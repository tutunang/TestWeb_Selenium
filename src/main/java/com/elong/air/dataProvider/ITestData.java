package com.elong.air.dataProvider;

import java.util.Map;

import com.elong.air.tools.OptionFile;

/**
 * 读取excel测试数据，并将测试数据封装到map中，key是表头，value是测试数据
 * 
 * @author qiaojiafei update by duwenjing
 * @version 创建时间：2016年4月25日 下午1:55:46 类说明
 */
public class ITestData {
	static String filepaString = OptionFile.readProperties(
			"./src/main/resources/config.properties", "excel");

	public static Map<String, String> getTestData(int sheet, int caseNum) {
		Map<String, String> map = OptionFile.getExcelDataByCaseNum(
				filepaString, sheet, caseNum);
		return map;
	}
}
