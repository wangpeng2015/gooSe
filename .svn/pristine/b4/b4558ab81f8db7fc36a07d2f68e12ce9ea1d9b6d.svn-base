package com.spring.mybatics.service;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.Hair_employee;
import com.spring.mybatics.domain.goo.Employee;

public interface IUserService {
//	public User getUserById(int userId);
	
//	public ServiceResult saveUser(Hair_employee user);
	
	/**
	 * 根据用户名和密码查询用户信息
	 * @param user
	 * @return
	 */
	public ServiceResult findUserService(Employee user);
	
	/**
	 * 根据用户id删除用户
	 * @param id
	 * @return
	 */
	public ServiceResult deleteUserById(Hair_employee user);
	
	/**
	 * 根据用户电话删除用户信息
	 * 
	 */
	public ServiceResult deleteUserByPhoneNService(Hair_employee user);

	/**
	 * 根据电话和名称获取用户信息（可能多条）
	 * @param user
	 * @return
	 */
	public ServiceResult searchUserByphoneOrNameService(Hair_employee user);
	
	/**
	 * 根据用户id进行更新
	 * @param user
	 * @return
	 */
//	public ServiceResult updateUserByIdService(Hair_employee user);
	
	/**
	 * 根据店的code查询店员信息列表
	 */
	public ServiceResult findCustomerList(Employee storeCode,int currentPage,int pageSize);
	
	/**
	 * 更新用户时间
	 * @param userName
	 * @return
	 */
	public ServiceResult updateCustomerService(String userName,String endDate);
	
	/**
	 * 删除用户根据手机号
	 * IUserService.deleteCustomersByphoneService()<BR>
	 * <P>Author :  Administrator </P>  
	 * <P>Date : 2017年12月9日 </P>
	 * @param userName
	 * @return
	 */
	public ServiceResult deleteCustomersByphoneService(String userName);

	/**
	* @Description: 重置手机信息
	* @param userName
	* @return
	*
	* @author wp
	* @date 2018年2月8日 上午11:05:35
	* @throws  
	*/
	public ServiceResult resertPhoneCustomersByphoneService(String userName);

	/**
	* @Description: 重置密码123456
	* @param userName
	* @return
	*
	* @author wp
	* @date 2018年2月8日 上午11:05:52
	* @throws  
	*/
	public ServiceResult resertPwdCustomersByphoneService(String userName);
}
