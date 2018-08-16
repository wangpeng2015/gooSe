package com.spring.mybatics.service;

import java.math.BigDecimal;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.Hair_customers;

public interface ICustomerService {

	ServiceResult saveCustomer(Hair_customers customers);

	ServiceResult searchCustomerByStoreCode(String storeCode);

	ServiceResult deleteCustomerByphoneService(String phoneNumber);

	ServiceResult updateCustomerByphoneService(Hair_customers customers);

	ServiceResult findCustomerListService(String phoneNumber,String customerName);

	ServiceResult updateCustomerByidService(Hair_customers customers);

	/**
	 * 充值
	 * @param money
	 * @param customer_phoneNumber
	 * @return
	 */
	ServiceResult updateCustomerByphoneService2(int applicant_id,String userName,BigDecimal money,String customer_phoneNumber,String storeCode);

	
	/**
	 * 充值，仅仅是客户的钱增加
	 * @param customer_phoneNumber
	 * @param money
	 * @param applicant_id
	 * @param application_name
	 * @return
	 */
	ServiceResult doUpdateCustomerByPhone3Service(String customer_phoneNumber,
			BigDecimal money, int applicant_id, String application_name,String storeCode);
	/**
	 * 结算，仅仅是客户的钱的剪发
	 * @param customer_phoneNumber
	 * @param money
	 * @param applicant_id
	 * @param application_name
	 * @return
	 */
	ServiceResult jieSuanForCustomerByPhone3Service(String customer_phoneNumber,
			BigDecimal money, int applicant_id, String application_name,String storeCode);

}
