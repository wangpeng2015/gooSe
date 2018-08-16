package com.spring.mybatics.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haier.result.ServiceResult;
import com.haier.result.exception.BusinessException;
import com.haier.result.exception.ExceptionConstants.BusinessExceptionCode;
import com.spring.mybatics.dao.IUserDao;
import com.spring.mybatics.dao.goo.LogDao;
import com.spring.mybatics.dao.goo.OrderDao;
import com.spring.mybatics.domain.Hair_employee;
import com.spring.mybatics.domain.goo.Employee;
import com.spring.mybatics.service.IUserService;
import com.spring.mybatics.util.PageCond;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Resource
	private IUserDao iUserDao;
	
	@Resource
	private LogDao logDao;
	
	@Resource
	private OrderDao orderDao;

	/*@Override
	public ServiceResult saveUser(Hair_employee user) {
		ServiceResult serviceResult=new ServiceResult();
		if(StringUtils.isBlank(user.getPassWord())){
			throw new BusinessException(BusinessExceptionCode.login_null_username, "请填写密码");
		}
//		自动生产店员或者店长的编码
		user.setUserCode(randomEmpNumber(user.getStoreCode(),user.getRoles()));
		user.setPassWord(MD5Util.getMD5(user.getPassWord()));
		//保存
		try{
			int id=iUserDao.save(user);
			if(id==1){
				serviceResult.setResult(id);
			}else{
				serviceResult.setMessage("保存失败,请重试");
				serviceResult.setResult(id);
			}
			return serviceResult;
		}catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "已存在手机或者理发门店没有选择，请重试");
		}
		
	}*/
	
	/**
	 * 根据用户名和密码查询用户，登录使用
	 * @param user
	 * @return
	 */
	@Override
	public ServiceResult findUserService(Employee user) {
		if(user.getPhoneNumber()==null || "".equals(user.getPhoneNumber())){
			throw new BusinessException(BusinessExceptionCode.login_null_username, "用户名不能为空");
		}
		if(user.getPassWord()==null || "".equals(user.getPassWord())){
			throw new BusinessException(BusinessExceptionCode.login_null_password, "密码不能为空");
		}
		//对密码进行MD5加密
//		user.setPassWord(MD5Util.getMD5(user.getPassWord()));
		user.setPassWord(user.getPassWord());
		PageCond pageCond=new PageCond(); 
		ServiceResult serviceResult=new ServiceResult();
		List<Employee> users=iUserDao.findByParams(user,pageCond);
		if(user!=null && users.size()>0){
			serviceResult.setResult(users.get(0));
		}else{
			throw new BusinessException(BusinessExceptionCode.no_user, "用户名或者密码错误");
		}
		return serviceResult;
		
	}
	
	/**
	 * 根据用户的id删除用户信息
	 * 
	 */
	public ServiceResult deleteUserById(Hair_employee user){
		ServiceResult serviceResult=new ServiceResult();
		if(user==null || user.getId() < 0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请选择要删除的用户");
		}
		try {
			int id=iUserDao.delete(user.getId());
			if(id==1){
				serviceResult.setResult(id);
			}else{
				serviceResult.setMessage("删除失败");
				serviceResult.setResult(id);
			}
			return serviceResult;
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "确认是否有此条信息");
		}
	}

	
	/**
	 * 根据用户的电话删除用户信息
	 * 
	 */
	@Override
	public ServiceResult deleteUserByPhoneNService(Hair_employee user) {
		ServiceResult serviceResult=new ServiceResult();
		if(user==null || user.getPhoneNumber().trim()==""){
			throw new BusinessException(BusinessExceptionCode.param_error, "请选择要删除的用户");
		}
		try {
			int res= iUserDao.deleteUserByPhoneNDAO(user.getPhoneNumber());
			if(res==1){
				serviceResult.setResult(res);
			}else{
				serviceResult.setMessage("删除失败");
				serviceResult.setResult(res);
			}
			return serviceResult;
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "确认是否有此条信息");
		}
		
	}
	
	/**
	 * 根据电话和姓名查询用户信息
	 * 
	 */
	@Override
	public ServiceResult searchUserByphoneOrNameService(Hair_employee user) {
		ServiceResult serviceResult=new ServiceResult();
		if(user.getUserName()==null && user.getPhoneNumber()==null){
			throw new BusinessException(BusinessExceptionCode.param_error, "请输入要查询的用户姓名或者电话信息");
		}
		List<Hair_employee> listUser=null;
		try {
			listUser=iUserDao.findByParamsByphoneOrNameService(user);
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "查询出错!");
		}
		if(listUser!=null && listUser.size()>0){
			serviceResult.setResult(listUser);
		}else{
			throw new BusinessException(BusinessExceptionCode.no_user, "没有查询到符合条件的用户");
		}
		return serviceResult;
	}
	
	/**
	 * 根据用户的id进行更新操作
	 * 
	 */
	/*@Override
	public ServiceResult updateUserByIdService(Hair_employee user) {
		ServiceResult serviceResult=null;
		if(user==null || user.getId()<0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请选择要修改的用户");
		}
		try {
			serviceResult=new ServiceResult();
			int res=iUserDao.update(user);
			if(res==1){
				serviceResult.setResult(res);
			}else{
				serviceResult.setMessage("修改失败");
				serviceResult.setResult(res);
			}
			return serviceResult;
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "更新出错!");
		}
		
	}*/

	/**
	 * 根据门店编码查询门店信息的员工
	 * 
	 */
	@Override
	public ServiceResult findCustomerList(Employee employee,int currentPage,int pageSize) {
		PageCond pageCond=new PageCond(); 
		currentPage=currentPage-1;
		pageCond.setCurrentPage(currentPage * pageSize);
		
		pageCond.setPageSize(pageSize);
		ServiceResult serviceResult=null;
		try {
			serviceResult=new ServiceResult();
			//查询个数
			int count=iUserDao.findCount(employee);
			HashMap<String, String> mm=new HashMap<String, String>();
			mm.put("totalCount", String.valueOf(count));
			List<Map<String, String>> listEmps=iUserDao.findListEmpByStoreCode(employee,pageCond);
			listEmps.add(mm);
			if(listEmps!=null && listEmps.size()>0){
				serviceResult.setResult(listEmps);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "后台有误");
		}
		return serviceResult;
	}
	
	
	@Override
	public ServiceResult updateCustomerService(String userName,String endDate) {
		ServiceResult serviceResult=null;
		try {
			int res=iUserDao.updateCustomerDateDao(userName,endDate);
			serviceResult=new ServiceResult();
			if(res>0){
				serviceResult.setResult(res);
				serviceResult.setMessage("更新成功");
				logDao.savelog(userName, "增加期限成功", "到期时间："+endDate);
				orderDao.saveOrderInfo(userName,endDate,"200"+serviceResult.getResult());
			}else{
				serviceResult.setResultCode("300");
				serviceResult.setMessage("更新失败");
				logDao.savelog(userName, "增加期限失败", "到期时间："+endDate);
				orderDao.saveOrderInfo(userName,endDate,"300"+serviceResult.getResultCode());
			}
		} catch (Exception e) {
			orderDao.saveOrderInfo(userName,endDate,"异常"+e.toString().substring(0, 50));
			throw new BusinessException(BusinessExceptionCode.param_error, "后台有误");
		}
		
		return serviceResult;
	}
	
	
	@Override
	public ServiceResult deleteCustomersByphoneService(String userName) {
		ServiceResult serviceResult=null;
		try {
			int res=iUserDao.deleteCustomersByphoneDao(userName);
			serviceResult=new ServiceResult();
			serviceResult.setResult(res);
			serviceResult.setMessage("删除成功");
			logDao.savelog(userName, "删除成功", "用户："+userName);
		} catch (Exception e) {
			serviceResult.setResultCode("300");
			serviceResult.setMessage("删除失败");
			logDao.savelog(userName, "删除失败", "用户："+userName);
		}
		return serviceResult;
	}
	
	/**
	 * 随机生成员工的编码
	 * @param storeCode
	 * @return
	 */
	public String randomEmpNumber(String storeCode,String roles){
		StringBuffer buffer=new StringBuffer();
		if(storeCode!=null && !"".equals(storeCode)){
			buffer.append(storeCode+"_emp_"+roles+"_");
		}
//		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
//		String format=sf.format(new Date());
//		buffer.append(format);
		buffer.append(System.currentTimeMillis());
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("123");
		buffer.append("456_");
		buffer.append(System.currentTimeMillis());
		System.out.println(buffer.toString());
	}

	/**
	 * @param userName
	 * @return
	 * @see com.spring.mybatics.service.IUserService#resertPhoneCustomersByphoneService(java.lang.String)
	 */
	@Override
	public ServiceResult resertPhoneCustomersByphoneService(String userName) {
		ServiceResult serviceResult=new ServiceResult();
		try {
			int res=iUserDao.resertPhoneCustomersByphoneDao(userName);
			serviceResult.setResult(res);
			serviceResult.setResultCode("200");
			serviceResult.setMessage("重置手机成功");
		}catch (Exception e) {
			serviceResult.setResult(null);
			serviceResult.setResultCode("300");
			serviceResult.setMessage("重置手机失败");
		}
		return serviceResult;
	}

	/**
	 * @param userName
	 * @return
	 * @see com.spring.mybatics.service.IUserService#resertPwdCustomersByphoneService(java.lang.String)
	 */
	@Override
	public ServiceResult resertPwdCustomersByphoneService(String userName) {
		ServiceResult serviceResult=new ServiceResult();
		try {
			int res=iUserDao.resertPwdCustomersByphoneDao(userName);
			serviceResult.setResult(res);
			serviceResult.setResultCode("200");
			serviceResult.setMessage("重置密码成功");
		}catch (Exception e) {
			serviceResult.setResult(null);
			serviceResult.setResultCode("300");
			serviceResult.setMessage("重置密码失败");
		}
		return serviceResult;
	}

	
	
}
