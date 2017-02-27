package com.elong.air.tools;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
	 * @return 
	 */
	
	 public static void deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
	            for (int i=0; i<children.length; i++) {
	              deleteDir(new File(dir, children[i]));
	            }
	        }
	        // 目录此时为空，可以删除
	        dir.delete();
	    }
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

	/**
	 * 得到当前日期
	 * @param source
	 * @return
	 */
	
	
	public static String getToday_Date(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式


		String today_date = dateFormat.format( now ); 
		System.out.println(today_date); 
		return today_date;
	}
	/**
	 * 得到当前日期+i
	 * @param source
	 * @return
	 */
	public static String getAfter_today_Date(int i){
		Calendar c=Calendar.getInstance();
        //当前的day_of_month加一就是明天时间
        c.add(Calendar.DAY_OF_MONTH,i);
        String after_today_date=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        System.out.println(i+"天后的日期"+after_today_date);
		
		return after_today_date;
	}
	
	
	
	public static void main(String args[]) {
	Common.getAfter_today_Date(5);
	}

}
