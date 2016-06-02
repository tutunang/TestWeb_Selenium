package com.elong.air.bean;

import java.util.Map;

import com.elong.air.base.BaseBean;

/** 
 * @author QiaoJiafei 
 * @version 创建时间：2016年5月3日 上午10:25:23 
 * 类说明 
 */
public class LoginInfoBean extends BaseBean{
	
	public LoginInfoBean(int sheet, int caseNum) {
		super(sheet, caseNum);
		initTestData(map);
	}

	public void initTestData(Map<String, String> map) {
		// TODO Auto-generated method stub
		setUsername(map.get("用户名"));
		setPassword(map.get("密码"));
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private String username;
	private String password;

}
