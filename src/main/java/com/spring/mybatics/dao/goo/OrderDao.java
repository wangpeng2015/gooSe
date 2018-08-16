package com.spring.mybatics.dao.goo;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.dao.CommonDAO;
import com.spring.mybatics.domain.goo.Log;

public interface OrderDao extends CommonDAO<Log,Integer>{

	/**
	* @Description:保存订单充值信息 
	* @param userName
	* @param endDate
	* @param string
	*
	* @author wp
	* @date 2018年1月6日 上午11:30:26
	* @throws  
	*/
	int saveOrderInfo(@Param("phoneNumber")String userName, @Param("end_time")String endDate, @Param("reason")String message);


}
