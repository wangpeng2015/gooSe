package com.spring.mybatics.domain.goo;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String userCode;//用户编码
    private String userName;//用户名称
    private String passWord;//密码
    private String phoneNumber;//电话号码
    private String roles;//角色  0代表客户，1代表店主
//    private String isVip;//是否是会员 1是,0不是 
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", userCode=" + userCode + ", userName="
				+ userName + ", passWord=" + passWord + ", phoneNumber="
				+ phoneNumber + ", roles=" + roles + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
