package com.elong.air.dataProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.elong.air.tools.OptionFile;
import com.elong.air.tools.Priority;

/**
 * @author  qiaojiafei
 * @version 创建时间：2016年4月25日 下午1:11:02
 * 类说明
 */
public class BaseProvider {
	@DataProvider(name="testdp")
	public static Iterator<Object[]> createData(Method method, ITestContext context) throws Exception{
		
		//ʹ��java������ƣ��Զ���ȡ���еĲ����࣬����ø���ĳ�Ա������ֵ
		String testclass = method.getDeclaringClass().getName();
		System.out.println("�����õ���class��"+testclass);
		Class clzz = Class.forName(testclass);
		Object object = clzz.newInstance();
		Field field = clzz.getDeclaredField("sheet");
		String testsheet = field.get(object).toString();
		System.out.println("�����õ���sheet��"+testsheet);
		
		int case_count = 0;
		int case_start = 4;
		int sheet = Integer.parseInt(testsheet);
		Priority p = null;
		final String P_FROM_PROPERTY = OptionFile.readProperties("./conf/Myconfig.properties", "priority");
		System.out.println("��ȡ�������ȼ�Ϊ��"+P_FROM_PROPERTY);
		
		//---------------��ȡexcel����ȡִ�е�����ʼ--------------
		
		String filepaString = OptionFile.readProperties("./conf/Myconfig.properties", "excel");
		List<Integer> listtemp = OptionFile.getExcelPriority(filepaString, sheet, P_FROM_PROPERTY);
		//System.out.println("OptionFile��list��С"+listtemp.size());
		if (listtemp.size()<1) {
			//log.debug("��ȡ�����������ȼ��ĸ���Ϊ0��ע���������ִ��");
		}
		
		//log.debug("��õ��Ĳ����������Ϊ��"+listtemp.size());
		
		List<Object[]> list = new ArrayList<Object[]>();
		for(Integer itg:listtemp) {
			list.add(new Object[]{itg});
		}
		
		return list.iterator();
		
		//---------------��ȡexcel����ȡִ�е��������--------------
	}
}
