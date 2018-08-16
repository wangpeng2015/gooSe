package com.spring.mybatics.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.domain.Hair_employee_dan;

public interface IEmployeeDanDao extends  CommonDAO<Hair_employee_dan,Integer>{

	int saveEmployeeDanDao(@Param("danju_applicant_id")int danju_applicant_id,@Param("employee_danjuCode")String danju_code,@Param("danju_price")BigDecimal danju_price);
	
	List<HashMap<String, String>> get25DaysCustomerOrdersDao(@Param("applicant_id")int applicant_id,@Param("storeCode")String storeCode);

}
