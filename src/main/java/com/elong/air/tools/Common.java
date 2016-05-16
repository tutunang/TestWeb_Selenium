package com.elong.air.tools;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author  QiaoJiafei
 * @version 创建时间：2016年5月10日 下午5:22:19
 * 类说明
 */
public class Common {
	
	/**
	 * 生产随机数字，[0-max）
	 * @param max
	 * @return
	 */
	public static int randomInt(int max) {
		Random random = new Random();
		int number = random.nextInt(max);
		return number;
	}
	/**
	 * row,col从0开始
	 * @param element
	 * @param row
	 * @param col
	 */
	public static WebElement cellOfTable(WebElement element,int row, int col) {
		System.out.println("获取表格单元格内容，行列分别为："+row+";"+col);
		List<WebElement> rows = element.findElements(By.tagName("tr"));
		List<WebElement> cols = rows.get(row).findElements(By.tagName("td"));
		WebElement cell = cols.get(col);
		System.out.println("jin ru celloftalbe"+rows.size()+";"+cols.size());
		return cell;
	}
	
	/**
	 * 得到字符串中的数字
	 * @param source
	 * @return
	 */
	public static String getNumberOfString(String source) {
		String number = Pattern.compile("[^0-9]").matcher(source).replaceAll("");
		return number;
	}
	public static void main(String args[]) {
		for(int i=0;i<20;i++) {
			System.out.println(randomInt(5));
		}
	}
}
