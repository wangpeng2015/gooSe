/**
* @Company 青鸟软通   
* @Title: LoginContext.java 
* @Package com.haier.hrportal.common.context 
* @author Liu Wenjie   
* @date 2014-3-4 下午2:28:14 
* @version V1.0   
*/ 
package com.spring.mybatics.util;

import java.io.Serializable;

import com.spring.mybatics.domain.Hair_employee;
import com.spring.mybatics.domain.goo.Employee;


/** 
 * @ClassName: LoginContext 
 * @Description: 
 */
public class LoginContext implements Serializable{

	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = -7229115125574271893L;
	private String uuid;
	private String thisTimeLoginName; //本次登录使用的登录名
	private String password; 	//用户的登录密码，（源码）
	private String soles;//角色
	
	private Employee loginUser;
	
	/*=========================getter and setter ===================*/
	
	/**
	 * @Description: 属性 password 的get方法 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @Description: 属性 password 的set方法 
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @Description: 属性 uuid 的get方法 
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @Description: 属性 uuid 的set方法 
	 * @param uuid 
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @Description: 属性 thisTimeLoginName 的get方法 
	 * @return thisTimeLoginName
	 */
	public String getThisTimeLoginName() {
		return thisTimeLoginName;
	}
	/**
	 * @Description: 属性 thisTimeLoginName 的set方法 
	 * @param thisTimeLoginName 
	 */
	public void setThisTimeLoginName(String thisTimeLoginName) {
		this.thisTimeLoginName = thisTimeLoginName;
	}
	/**
	 * @Description: 属性 loginUser 的get方法 
	 * @return loginUser
	 */
	public Employee getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(Employee loginUser) {
		this.loginUser = loginUser;
	}
	public String getSoles() {
		return soles;
	}
	public void setSoles(String soles) {
		this.soles = soles;
	}
	@Override
	public String toString() {
		return "LoginContext [uuid=" + uuid + ", thisTimeLoginName="
				+ thisTimeLoginName + ", password=" + password + ", soles="
				+ soles + ", loginUser=" + loginUser + "]";
	}
	
	
	
}
