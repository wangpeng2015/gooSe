package com.spring.mybatics.dao.goo;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.dao.CommonDAO;
import com.spring.mybatics.domain.goo.Log;

public interface LogDao extends CommonDAO<Log,Integer>{

	void savelog(@Param("name")String name, @Param("operator")String operator, @Param("message")String message);

}
