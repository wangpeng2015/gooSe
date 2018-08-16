package com.spring.mybatics.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.domain.Hair_stores;

public interface IStoresDao extends CommonDAO<Hair_stores,Integer>{

	void updateStoresDao(@Param("danju_stores_code")String danju_stores_code, @Param("danju_price")BigDecimal danju_price);
	
	List<Hair_stores> finaAllStores();

}
