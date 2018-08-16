package com.spring.mybatics.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 顾客(会员)信息表
 * @author Administrator
 *
 */
public class Hair_customers implements Serializable{
	
	private static final long serialVersionUID = -5131774118457834218L;
	
	private int customer_id;
	private String customer_name;//顾客姓名
	private String customer_password;//顾客密码
	private String customer_code;//顾客编码
	private String customer_phoneNumber;//顾客电话
	private String customer_address;//顾客地址
	private BigDecimal customer_money;//顾客金钱
	private String customer_isVip;//顾客vip等级
	private BigDecimal customer_integral;//顾客积分
	private String customer_storeCode;//门店编码外键
	private Date createTime;//客户创建时间
	private Date updateTime;//顾客更新时间
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_phoneNumber() {
		return customer_phoneNumber;
	}
	public void setCustomer_phoneNumber(String customer_phoneNumber) {
		this.customer_phoneNumber = customer_phoneNumber;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public BigDecimal getCustomer_money() {
		return customer_money;
	}
	public void setCustomer_money(BigDecimal customer_money) {
		this.customer_money = customer_money;
	}
	public String getCustomer_isVip() {
		return customer_isVip;
	}
	public void setCustomer_isVip(String customer_isVip) {
		this.customer_isVip = customer_isVip;
	}
	public BigDecimal getCustomer_integral() {
		return customer_integral;
	}
	public void setCustomer_integral(BigDecimal customer_integral) {
		this.customer_integral = customer_integral;
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
	
	public String getCustomer_storeCode() {
		return customer_storeCode;
	}
	public void setCustomer_storeCode(String customer_storeCode) {
		this.customer_storeCode = customer_storeCode;
	}
	
	public String getCustomer_password() {
		return customer_password;
	}
	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}
	@Override
	public String toString() {
		return "Hair_customers [customer_id=" + customer_id
				+ ", customer_name=" + customer_name + ", customer_password="
				+ customer_password + ", customer_code=" + customer_code
				+ ", customer_phoneNumber=" + customer_phoneNumber
				+ ", customer_address=" + customer_address
				+ ", customer_money=" + customer_money + ", customer_isVip="
				+ customer_isVip + ", customer_integral=" + customer_integral
				+ ", customer_storeCode=" + customer_storeCode
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
}
