package com.spring.mybatics.dao.goo;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.dao.CommonDAO;
import com.spring.mybatics.domain.goo.Goo;

public interface GooDAO extends CommonDAO<Goo,Integer>{
	
	Map<String, String> findUserMsgDao(@Param("name")String name, @Param("passwd")String passwd);

	Map<String, String> findUserByName(@Param("name")String name);

	int saveSignUserMsgDao(@Param("name")String name, @Param("passwd")String passwd, @Param("confirmPass")String confirmPass,@Param("tuijianCode")String tuijianCode);
	
	int updateUid(@Param("name")String name,@Param("uid")String uid);

	/**
	 * @return 
	* @Description:
	*
	* @author wp
	* @date 2018年1月1日 下午8:47:52
	* @throws  
	*/
	Map<String, String> getLBMessageDao();

	/**
	* @Description: (这里用一句话描述这个方法的作用)  
	* @param name
	* @param passwd
	* @param confirmPass
	* @param tuijianCode
	* @param uid
	* @return
	*
	* @author wp
	* @date 2018年1月16日 上午9:18:50
	* @throws  
	*/
	int saveIosSignUserMsgDao(@Param("name")String name, @Param("passwd")String passwd, @Param("confirmPass")String confirmPass,@Param("tuijianCode")String tuijianCode, @Param("uid")String uid);

	/**
	* @Description: (这里用一句话描述这个方法的作用)  
	* @param phone
	* @return
	*
	* @author wp
	* @date 2018年1月20日 上午11:48:45
	* @throws  
	*/
	int findUserMsgDao(String phone);

	/**
	* @Description: (这里用一句话描述这个方法的作用)  
	* @param phone
	* @return
	*
	* @author wp
	* @date 2018年1月20日 上午11:53:42
	* @throws  
	*/
	Integer isPhoneInLangBoDao(@Param("phone")String phone);

	/**
	* @Description: (这里用一句话描述这个方法的作用)  
	* @param version
	* @return
	*
	* @author wp
	* @date 2018年3月4日 下午7:55:20
	* @throws  
	*/
	Map<String, String> findLatestVersionDao(@Param("version")String version);
}


