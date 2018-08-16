package com.spring.mybatics.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.spring.mybatics.domain.Hair_employee;
import com.spring.mybatics.domain.goo.Employee;
import com.spring.mybatics.util.PageCond;

public interface IUserDao extends CommonDAO<Employee,Integer>{

	/**
	 * 根据电话删除用户信息
	 * @param phoneNumber
	 * @return
	 */
	int deleteUserByPhoneNDAO(String phoneNumber);
	
	/**
	 * 根据电话或者姓名查询用户信息
	 * @param user
	 * @return
	 */
	List<Hair_employee> findByParamsByphoneOrNameService(@Param("entity")Hair_employee user);
	
	/**
	 * 根据门店查询店员信息
	 */
	List<Map<String, String>> findListEmpByStoreCode(@Param("employee")Employee employee,@Param("pageCond") PageCond pageCond);

	/**
	 * 设置更新时间
	 * @param userName
	 * @return
	 */
	int updateCustomerDateDao(@Param("username")String userName,@Param("endDate")String endDate);

	
	/**
	 * 查询总数
	 * @param employee
	 * @return
	 */
	int findCount(@Param("employee")Employee employee);
	
	
	/**
	 * 删除用户信息
	 * IUserDao.deleteCustomersByphoneDao()<BR>
	 * <P>Author :  Administrator </P>  
	 * <P>Date : 2017年12月9日 </P>
	 * @param userName
	 * @return
	 */
	int deleteCustomersByphoneDao(@Param("username")String userName);

	/**
	* @Description: (这里用一句话描述这个方法的作用)  
	* @param userName
	* @return
	*
	* @author wp
	* @date 2018年2月8日 上午11:10:47
	* @throws  
	*/
	int resertPhoneCustomersByphoneDao(@Param("username")String userName);

	/**
	* @Description: (这里用一句话描述这个方法的作用)  
	* @param userName
	* @return
	*
	* @author wp
	* @date 2018年2月8日 上午11:10:52
	* @throws  
	*/
	int resertPwdCustomersByphoneDao(@Param("username")String userName);

}
