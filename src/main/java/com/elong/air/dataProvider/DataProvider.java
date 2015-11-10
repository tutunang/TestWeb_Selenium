package com.elong.air.dataProvider;

import java.io.File;
import com.elong.air.Bean.PassenagerInfoBean;
import com.elong.air.Bean.SearchInfoBean;
import com.thoughtworks.xstream.XStream;

public class DataProvider {

	public Object getBean(String path) {
		 String xmlPath="src/test/resources/"+path+".xml";
		File file = new File(xmlPath);
		Object entity = toEntity(file, Object.class, new Class[] {
				PassenagerInfoBean.class, SearchInfoBean.class });
		return entity;
	}
//	public static void main(String[] args) {
//		String classloderPath=Thread.currentThread().getContextClassLoader().getResource("/").getPath();
//		String xmlPath = classloderPath + "testData/TestCreateSingleOrder.xml";
//		System.out.println(xmlPath);
//	}

	/**
	 * 
	 * @param file
	 * @param clazz
	 *            主要是为了确定返回类型的
	 * @param annotationClazzs
	 *            为了扫描注解
	 * @return
	 */
	public static <T> T toEntity(File file, Class<T> clazz,Class... annotationClazzs) {
		XStream xs = new XStream();
		xs.setMode(XStream.NO_REFERENCES);
		// 注册使用了注解的VO
		xs.processAnnotations(annotationClazzs);
		T t = (T) xs.fromXML(file);
		return t;
	}

}
