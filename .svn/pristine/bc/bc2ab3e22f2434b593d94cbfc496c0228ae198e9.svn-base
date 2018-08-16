package com.spring.mybatics.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.domain.Hair_danju;

public interface IOrderDao extends CommonDAO<Hair_danju,Integer>{
	
	/**
	 * 根据门店编码获取门店订单信息
	 * @param storeCode
	 * @return
	 */
	List<Hair_danju> getAllOrderDAO(@Param("storeCode")String storeCode);
	
	/**
	 * 更新审核字段
	 * @param hair_danju
	 * @return
	 */
	int updateDanjuConfirm(@Param("danju_id")int danju_id);

	/**
	 * 根据单据id更新订单
	 * @param danju_id
	 * @return
	 */
	int updateInvalidOrderDao(@Param("danju_id")int danju_id);
	
	/**
	 * 根据订单的id获取订单
	 * 
	 */
	Hair_danju getOrderByID(@Param("danju_id")int danju_id);
	
	/**
	  * 店长获取的店的订单数量和订单金额和店的金额
     */
	HashMap<String, String> getStoreOrderInfoDao(@Param("storeCode")String storeCode);
	
	/**
	 * 店员获取自己订单信息
	 * @return
	 */
	HashMap<String, String> getEmpOrderInfoDao(@Param("storeCode")String storeCode,@Param("userId")String userid);

	/**
	 * 店员获取订单列表
	 * @param storeCode
	 * @param userid
	 * @return
	 */
	List<Hair_danju> getEmpListDao(@Param("storeCode")String storeCode,@Param("userId")String userid);

	/**
	 * 根据单据条件信息获取符合条件的单据
	 * @param storeCode
	 * @param danju
	 * @return
	 */
	List<Hair_danju> getOrderLisyBuanyParamDao(@Param("entity")Hair_danju danju);

}
