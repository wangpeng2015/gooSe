package com.spring.mybatics.service;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.Hair_danju;

public interface IOrderService {
	
	public ServiceResult getAllOrder(String storeCode);

	
	/**
	 * 保存单据
	 * @param danju
	 * @return
	 */
	public ServiceResult saveDanjuService(Hair_danju danju,String customer_pass);

	/**
	 * 处理订单
	 * @param danju
	 * @return
	 */
	public ServiceResult dealOrderService(int danju);

	/**
	 * 作废订单
	 * @return
	 */
	public ServiceResult updateInvalidOrderService(int id);

	/**
	  * 店长获取的店的订单数量和订单金额和店的金额
     */
	public ServiceResult getStoreOrderInfoService(String storeCode);

	
	/**
	 * 店员获取自己的订单信息
	 * @param storeCode
	 * @param userCode
	 * @return
	 */
	public ServiceResult getEmpOrderInfoService(String storeCode,
			String userId);

	
	/**
	 * 店员获取订单列表
	 * @param storeCode
	 * @param userId
	 * @return
	 */
	public ServiceResult getEmpOrderListService(String storeCode, String userId);

	
	/**
	 * 根据各种条件查询订单
	 * @param danju 
	 * @param storeCode 
	 * @return
	 */
	public ServiceResult getOrderLisyBuanyParamService(Hair_danju danju);

	/**
	 * 获得25天前的客户订单信息
	 * @return
	 */
	ServiceResult get25DaysCustomerOrdersService(int application_id,String storeCode);

}
