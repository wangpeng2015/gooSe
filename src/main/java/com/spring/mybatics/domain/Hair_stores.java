package com.spring.mybatics.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 理发的门店
 * @author Administrator
 *
 */
public class Hair_stores implements Serializable{

	private static final long serialVersionUID = -3175220475379229681L;
	
	private int store_id;//id 不是主键
	private String store_code;//门店编码  主键
	private String store_name;//门店名称
	private String store_phone;//门店电话
	private String store_address;//门店地址
	private Date  store_createTime;//门店创建时间
	private Date  store_updateTime;//门店更新时间
	
	public int getStore_id() {
		return store_id;
	}
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	public String getStore_code() {
		return store_code;
	}
	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_phone() {
		return store_phone;
	}
	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}
	public String getStore_address() {
		return store_address;
	}
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}
	public Date getStore_createTime() {
		return store_createTime;
	}
	public void setStore_createTime(Date store_createTime) {
		this.store_createTime = store_createTime;
	}
	public Date getStore_updateTime() {
		return store_updateTime;
	}
	public void setStore_updateTime(Date store_updateTime) {
		this.store_updateTime = store_updateTime;
	}
	
	@Override
	public String toString() {
		return "Hair_stores [store_id=" + store_id + ", store_code="
				+ store_code + ", store_name=" + store_name + ", store_phone="
				+ store_phone + ", store_address=" + store_address
				+ ", store_createTime=" + store_createTime
				+ ", store_updateTime=" + store_updateTime + "]";
	}
}
