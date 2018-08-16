package com.spring.mybatics.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.domain.Hair_store_income;

public interface IStoreIncomeDao extends CommonDAO<Hair_store_income,Integer>{
	
	
	/**
	 * 充值门店订单的金额
	 * @param danju_storesCode
	 * @param danju_price
	 * @return
	 */
	int updateStore_actOrderMoney(@Param("danju_storesCode")String danju_storesCode, @Param("danju_price")BigDecimal danju_price);
	
	/**
	 * 根据门店获得门店金额
	 * @param danju_stores_code
	 * @return
	 */
	BigDecimal getStoreIncomeByStoreCode(@Param("danju_storesCode")String danju_stores_code);

	/**
	 * 充值门店金额
	 * @param money
	 * @param storeCode
	 * @return
	 */
	int updateStore_actMoney(@Param("money")BigDecimal money,@Param("danju_storesCode") String storeCode);
}
