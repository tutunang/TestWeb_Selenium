package com.elong.air.tools;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot {
//	WebDriver driver;
	public  final static  String path ="D:/eclipseWorkSpace/AirGUIDemo/test-output/ScreenShot";
	public static void  takeScreenShot(WebDriver driver) {
		try {
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
			Random rd=new Random();
			int tmp = Math.abs(rd.nextInt());		
			FileUtils.copyFile(file, new File(path,tmp+"screenshopt.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	public static void main(String[] args) {
		
		//System.out.println(tmp);
	}
}