package com.elong.air.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCookie {
	WebDriver driver=null;
	public void testCookie() throws IOException{
		driver=new FirefoxDriver();
	//	driver=new ChromeDriver();
	driver.get("https://secure.elong.com/passport/login_cn.html");
	
	    driver.manage().deleteAllCookies();
	//	driver.findElement(By.cssSelector("#user_display_login>span>a")).click();
		driver.findElement(By.cssSelector("#UserName")).sendKeys("13146388663");
		driver.findElement(By.cssSelector("input[method='ValidatePassword']")).sendKeys("tutu521");
	    driver.findElement(By.cssSelector(".loginbtn")).click();
		driver.get("http://flight.elong.com");
	    Set<Cookie>set=  driver.manage().getCookies();
	    File file_d=new File("F:/TestCookies.txt");
	    FileWriter fw = new FileWriter(file_d);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Cookie c:set) {
            bw.write(c.getDomain()+";"+c.getName()+";"+c.getValue()+";"+c.getPath()+";"+c.getExpiry()+";"+c.isSecure());
        	bw.newLine();
        }
        bw.flush();
        bw.close();
        fw.close();	
	
		
	}
	
	
	
	
	
	
	public void getCookie(WebDriver driver) throws Exception{
		
		    File file = new File("F:/TestCookies.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while((line=br.readLine())!=null) {
            
                
                String [] sz = line.split(";");
                String doman =  sz[0].trim();
                String name = sz[1].trim();
                String value = sz[2].trim();
                String path = sz[3].trim();
                Date date = null;
 
                if(!(sz[4].equals("null"))){   
                    date = new Date(sz[4]);
                    //System.out.println("date="+date);
 
                }
                Boolean bl = Boolean.valueOf(sz[5]);
             
                 
                Cookie ck = new Cookie(name,value,doman,path,date,bl);
                System.out.println("name"+"<<<"+name+">>>");
                System.out.println("value"+"<<<<"+value+">>>>");
                System.out.println("doman"+"<<<<"+doman+">>>>");
                System.out.println("path"+"<<<<"+path+">>>");
                System.out.println("date"+"<<"+date+">>>>");
                System.out.println("bl"+"<<<<"+bl+">>>>>");
                
                driver.manage().addCookie(ck);
            }
		
	}
	public static void main(String[] args) throws IOException {
		TestCookie tc=new TestCookie();
		tc.testCookie();
	}

}
