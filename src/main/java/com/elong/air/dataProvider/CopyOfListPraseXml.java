package com.elong.air.dataProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class CopyOfListPraseXml {
	  List<Map<String,String>>mm=new ArrayList();
	//位置属性名和元素名字
	public void getValue(String path) throws DocumentException{
		SAXReader reader = new SAXReader();
		  File file = new File(path);
		  Document document = reader.read(file);
		  Element root = document.getRootElement();
		  List<Element> childElements = root.elements();
		

		
		  //未知属性名情况下

		  for (Element child : childElements) {
			 List<Attribute> attributeList = child.attributes();
			   for (Attribute attr : attributeList) {
			    System.out.println(attr.getName() + "::: " + attr.getValue()+":::::"+attr.getText()+attr.getNamespacePrefix());
			    if(attr.getValue().equals("true")){
			    	  System.out.println("title" +":"+ child.elementText("test"));
			             getValue(child.elementText("test"));
			    	  
			    }
			   
			   }
			 //未知子元素名情况下
			// List<String>s=new ArrayList();
			   Map<String ,String>li=new HashMap();
			   
			   List<Element> elementList = child.elements();
			   
			   for (Element ele : elementList) {
			
			    System.out.println(ele.getName() + ": " + ele.getText());
			 li.put(ele.getName() , ele.getText());

			   }
			   mm.add(li);
			   System.out.println();
		  }
	
		
	}
	
	public void getTestData(String s) throws Exception {
       mm.get(0);
	
	}
		
		public static void main(String[] args) throws Exception {
		CopyOfListPraseXml s=new CopyOfListPraseXml();
		s.getValue("src/testDate/TestCreateSingleOrder.xml");
	 }
}
