package com.elong.air.dataProvider;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.elong.air.AbstractObject.AbstractPageObject;

public class ListPraseXml {
	
	public ListPraseXml(String path) throws DocumentException{
		getValue("src/testDate/"+path+".xml");
		
	}
	 //已知属性名和元素名称情况下
	public void getValueByAttributeName(String path) throws DocumentException{
		 SAXReader reader = new SAXReader();
		  File file = new File("books.xml");
		  Document document = reader.read(file);
		  Element root = document.getRootElement();
		  List<Element> childElements = root.elements();
		  for (Element child : childElements) {
			   System.out.println("id: " + child.attributeValue("id"));
			   System.out.println("title" + child.elementText("title"));
			   System.out.println("author" + child.elementText("author"));
		  }
	}
	//位置属性名和元素名字
	public void getValue(String path) throws DocumentException{
		SAXReader reader = new SAXReader();
		  File file = new File(path);
		  Document document = reader.read(file);
		  Element root = document.getRootElement();
		  List<Element> childElements = root.elements();
		  for (Element child : childElements) {
			  //未知属性名情况下
			 List<Attribute> attributeList = child.attributes();
			   for (Attribute attr : attributeList) {
			    System.out.println(attr.getName() + "::: " + attr.getValue());
			    
			   }
			 //未知子元素名情况下
			   List<Element> elementList = child.elements();
			   for (Element ele : elementList) {
			    System.out.println(ele.getName() + ": " + ele.getText());
			   }
			   System.out.println();
		  }
		
	}
	
	public <expectedBean> expectedBean getTestDate(Class<?> exceptedBean,String path) throws Exception {
		getValue(path);
	
		return null;
	
	}
		
		public static void main(String[] args) throws Exception {
//		ListPraseXml s=new ListPraseXml();
//		s.getValue();
	 }
}
