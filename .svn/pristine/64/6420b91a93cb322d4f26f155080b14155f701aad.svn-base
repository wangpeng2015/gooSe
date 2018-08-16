package com.spring.mybatics.service.goo;

import com.haier.result.ServiceResult;

public interface GooService {
	
	ServiceResult findUserMsg(String name,String passwd,String uid);

	ServiceResult addSignUserMsg(String name, String passwd, String confirmPass,String tuijianCode);

	/**
	* @Description:获取狼播信息  
	* @return
	*
	* @author wp
	* @date 2018年1月1日 下午8:46:56
	* @throws  
	*/
	ServiceResult getLBMessageService();

	/**
	* @Description:ios注册
	* @param name
	* @param passwd
	* @param confirmPass
	* @param tuijianCode
	* @return
	*
	* @author wp
	* @date 2018年1月16日 上午9:16:29
	* @throws  
	*/
	ServiceResult addIosSignUserMsg(String name, String passwd, String confirmPass, String tuijianCode,String uid);

	/**
	* @Description:
	* @param trim
	*
	* @author wp
	* @date 2018年1月20日 上午11:46:09
	* @throws  
	*/
	ServiceResult isPhoneInLangBoService(String trim);

	/**
	* @Description: (这里用一句话描述这个方法的作用)  
	* @param version
	* @return
	*
	* @author wp
	* @date 2018年3月4日 下午7:52:49
	* @throws  
	*/
	ServiceResult findLatestVersion(String version);

}
