package com.spring.mybatics.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户表（店员和店长）
 * @author Administrator
 *
 */
public class Hair_employee implements Serializable{
	
	private static final long serialVersionUID = -8393447995276303795L;

	private int id;
	private String userCode;//用户编码
    private String userName;//用户名称
    private String confirmPass;//确认密码，后台不使用，校验使用
    private String passWord;//密码
    private String email;//电子邮件
    private String phoneNumber;//电话号码
    private String roles;//角色  0代表客户，1代表店主
    private int realMoney;//金额
    private int integral;//积分
    private BigDecimal salary;//工资
    private BigDecimal jixiao;//绩效
    private String storeCode;//门店编码   外键
    private String storeName;//门店名称
    private String idCard;//身份证
//    private String isVip;//是否是会员 1是,0不是 
    private Date createTime;//创建时间
    private Date updateTime;//修改时间
    
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(int realMoney) {
		this.realMoney = realMoney;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getJixiao() {
		return jixiao;
	}

	public void setJixiao(BigDecimal jixiao) {
		this.jixiao = jixiao;
	}
	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Override
	public String toString() {
		return "Hair_employee [id=" + id + ", userCode=" + userCode
				+ ", userName=" + userName + ", confirmPass=" + confirmPass
				+ ", passWord=" + passWord + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", roles=" + roles
				+ ", realMoney=" + realMoney + ", integral=" + integral
				+ ", salary=" + salary + ", jixiao=" + jixiao + ", storeCode="
				+ storeCode + ", storeName=" + storeName + ", idCard=" + idCard
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

	/*public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}*/

	
}