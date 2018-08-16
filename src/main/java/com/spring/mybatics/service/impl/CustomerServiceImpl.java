package com.spring.mybatics.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haier.result.ServiceResult;
import com.haier.result.exception.BusinessException;
import com.haier.result.exception.ExceptionConstants.BusinessExceptionCode;
import com.spring.mybatics.dao.ICustomerDAO;
import com.spring.mybatics.dao.IEmployeeDanDao;
import com.spring.mybatics.dao.IOrderDao;
import com.spring.mybatics.dao.IStoreIncomeDao;
import com.spring.mybatics.domain.Hair_customers;
import com.spring.mybatics.domain.Hair_danju;
import com.spring.mybatics.service.ICustomerService;
import com.spring.mybatics.util.MD5Util;


@Service("iCustomerService")
public class CustomerServiceImpl implements ICustomerService{
	/**
	 * 门店收入
	 */
	@Resource
	private IStoreIncomeDao iStoreIncomeDao;

	@Autowired
	private ICustomerDAO iCustomerDAO;
	/**
	 * 客户vip
	 */
	@Resource
	private ICustomerDAO iCustomerDao;
	/**
	 * 
	 * 订单
	 */
	@Resource
	private IOrderDao iOrderDao;
	/**
	 * 员工与单据
	 */
	@Resource
	private IEmployeeDanDao iEmployeeDanDao;
	
	
	/**
	 * 录入客户金钱，还要在门店中添加金钱
	 */
	@Override
	public ServiceResult saveCustomer(Hair_customers customers) {
		ServiceResult serviceResult=null;
		if(StringUtils.isBlank(customers.getCustomer_code())){
			customers.setCustomer_code(randomCustomerNumber(customers.getCustomer_storeCode()));
		}
		if(!StringUtils.isBlank(customers.getCustomer_password())){
			customers.setCustomer_password(MD5Util.getMD5(customers.getCustomer_password()));
		}
		try {
			serviceResult=new ServiceResult();
			int res=iCustomerDAO.save(customers);
			if(res!=1){
				throw new BusinessException(BusinessExceptionCode.param_error, "保存客户信息失败!");
			}
			serviceResult.setResult(res);
			//增加店面的金额
			/*int income=iStoreIncomeDao.updateStore_actMoney(customers.getCustomer_money(),customers.getCustomer_storeCode());
			if(income!=1){
				throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
			}*/
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "保存客户信息失败:手机号不能重复!");
		}
		return serviceResult;
	}
	
	/**
	 * 根据门店编码查询门店顾客信息
	 */
	@Override
	public ServiceResult searchCustomerByStoreCode(String storeCode) {
		ServiceResult serviceResult=null;
		if(StringUtils.isBlank(storeCode)){
			throw new BusinessException(BusinessExceptionCode.param_error, "请填写门店编码!");
		}
		serviceResult=new ServiceResult();
		List<Hair_customers> list_customer=iCustomerDAO.searchCustomerListByStoreCode(storeCode);
		if(list_customer!=null && list_customer.size()>0){
			serviceResult.setResult(list_customer);
		}
		return serviceResult;
	}

	/**
	 * 根据电话进行用户删除
	 */
	@Override
	public ServiceResult deleteCustomerByphoneService(String phoneNumber) {
		ServiceResult serviceResult=null;
		if("".equals(phoneNumber) || null==phoneNumber){
			throw new BusinessException(BusinessExceptionCode.param_error, "请填写要删除的用户的电话信息!");
		}
		try {
			serviceResult=new ServiceResult();
			int deleId=iCustomerDAO.deleteCustomerByphoneDao(phoneNumber);
			if(deleId==1){
				serviceResult.setResult(deleId);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "删除客户信息失败!");
		}
		return serviceResult;
	}
	
	/**
	 * 根据电话进行用户信息更新
	 */
	@Override
	public ServiceResult updateCustomerByphoneService(Hair_customers customers) {
		ServiceResult serviceResult=null;
		if(StringUtils.isBlank(customers.getCustomer_phoneNumber())){
			throw new BusinessException(BusinessExceptionCode.param_error, "电话信息不能为空!");
		}
		try {
			serviceResult=new ServiceResult();
			int res=iCustomerDAO.updateCustomerByPhone(customers);
			if(res==1){
				serviceResult.setResult(res);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "更新客户信息失败!");
		}
		return serviceResult;
	}
	
	/**
	 * 根据电话进行用户充值
	 */
	@Override
	public ServiceResult updateCustomerByphoneService2(int applicant_id,String userName,BigDecimal money,String customer_phoneNumber,String storeCode) {
		ServiceResult serviceResult=null;
		if(StringUtils.isBlank(storeCode)){
			throw new BusinessException(BusinessExceptionCode.param_error, "门店信息不完整");
		}
		if(StringUtils.isBlank(customer_phoneNumber)){
			throw new BusinessException(BusinessExceptionCode.param_error, "请填写要删除的用户的电话信息!");
		}
		int big=money.compareTo(BigDecimal.ZERO);
		if(big<=0){
			throw new BusinessException(BusinessExceptionCode.param_error, "充值无效!");
		}
		//取得客户的钱
		Hair_customers moneyCu=iCustomerDAO.findCustomerMoneyByPhoneNumber(customer_phoneNumber,storeCode);
		if(moneyCu==null){
			throw new BusinessException(BusinessExceptionCode.param_error, "客户是否存在");
		}
		//用户金钱加上充值的钱
		BigDecimal resDouble=moneyCu.getCustomer_money().add(money);
		serviceResult=new ServiceResult();
		int res=iCustomerDAO.updateCustomerByPhone2(resDouble,customer_phoneNumber,storeCode);
		if(res==1){
			//充值成功，店的金额累加
			int income=iStoreIncomeDao.updateStore_actMoney(money,storeCode);
			if(income!=1){
				//充值失败
				throw new BusinessException(BusinessExceptionCode.param_error, "充值失败:门店步骤");
			}
			//保存订单
			Hair_danju danju=new Hair_danju();
			danju.setDanju_isConfirm("已完成");
			danju.setDanju_price(money);
			danju.setDanju_applicant_id(applicant_id);
			danju.setDanju_applicant(userName);
			danju.setCustomer_phone(customer_phoneNumber);
			danju.setDanju_code(randomOrderRechargeNumber(storeCode));
			danju.setDanju_isVip("1");
			danju.setDanju_storesCode(storeCode);
			int id=iOrderDao.save(danju);
			if(id!=1){
				throw new BusinessException(BusinessExceptionCode.param_error, "订单保存失败");
			}
			//保存店员和单据价格字段
			int empdan=iEmployeeDanDao.saveEmployeeDanDao(applicant_id,danju.getDanju_code(),money);
			if(empdan!=1){
				throw new BusinessException(BusinessExceptionCode.param_error,"定远和订单保存失败");
			}
			serviceResult.setResult(empdan);
		}else{
			//充值失败
			throw new BusinessException(BusinessExceptionCode.param_error, "充值失败!");
		}
		return serviceResult;
	}


	/**
	 * 根据电话或者用户姓名查询客户列表
	 * 
	 */
	@Override
	public ServiceResult findCustomerListService(String phoneNumber,String customerName) {
		ServiceResult serviceResult=null;
		if((null==phoneNumber || "".equals(phoneNumber)) && (null==customerName || "".equals(customerName))){
			throw new BusinessException(BusinessExceptionCode.param_error, "请填写要查询的条件姓名或者电话");
		}
		try {
			serviceResult=new ServiceResult();
			List<Hair_customers> listCustomer=iCustomerDAO.findCustomerListDao(phoneNumber,customerName);
			if(listCustomer!=null){
				serviceResult.setResult(listCustomer);
			}else{
				serviceResult.setMessage("没有查询到符合条件的用户");
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "查询客户信息失败!");
		}
		return serviceResult;
	}
	
	/**
	 * 
	 * 根据id更新客户的信息
	 */
	@Override
	public ServiceResult updateCustomerByidService(Hair_customers customer) {
		ServiceResult serviceResult=null;
		if(customer.getCustomer_id() <= 0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请选择用户");
		}
		try {
			serviceResult=new ServiceResult();
			int res=iCustomerDAO.updateCustomerByiddao(customer);
			if(res!=1){
				serviceResult.setMessage("更新客户信息失败!");
			}else{
				serviceResult.setResult(res);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "更新客户信息失败!");
		}
		return serviceResult;
	}
	
	/**
	 * 根据电话进行充值for 门店
	 * 
	 */
	@Override
	public ServiceResult doUpdateCustomerByPhone3Service(
			String customer_phoneNumber, BigDecimal money, int applicant_id,
			String application_name,String storeCode) {
		ServiceResult serviceResult=null;
		//更新用户的金钱
		if(applicant_id<0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请登录");
		}
		int big=money.compareTo(BigDecimal.ZERO);
		if(big<=0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请输入正确的充值金额!");
		}
		//判断客户的金钱是否足够
		Hair_customers moneyAndPass=iCustomerDao.findCustomerMoneyByPhoneNumber(customer_phoneNumber,storeCode);
		if(moneyAndPass==null){
			throw new BusinessException(BusinessExceptionCode.param_error,"客户是否是会员");
		}
		BigDecimal mm=moneyAndPass.getCustomer_money();
		//用户金钱减去单据价格
		BigDecimal resDouble=mm.add(money);
		serviceResult=new ServiceResult();
		try {
			int uu=iCustomerDao.updateCustomer(resDouble,customer_phoneNumber);
			if(uu!=1){
				serviceResult.setResult(uu);
			}else{
				serviceResult.setResult(uu);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error,"充值失败");
		}
		return serviceResult;
	}
	/**
	 * 根据门电话进行消费结算 门店
	 */
	@Override
	public ServiceResult jieSuanForCustomerByPhone3Service(
			String customer_phoneNumber, BigDecimal money, int applicant_id,
			String application_name,String storeCode) {
		ServiceResult serviceResult=null;
		if(applicant_id<0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请登录");
		}
		int big=money.compareTo(BigDecimal.ZERO);
		if(big<=0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请输入正确的结算金额!");
		}
		//判断客户的金钱是否足够
		Hair_customers moneyAndPass=iCustomerDao.findCustomerMoneyByPhoneNumber(customer_phoneNumber,storeCode);
		if(moneyAndPass==null){
			throw new BusinessException(BusinessExceptionCode.param_error,"客户是否是会员");
		}
		//用户的金钱
		BigDecimal mm=moneyAndPass.getCustomer_money();
		//用户金钱减去单据价格，剩下的钱
		double resDouble=mm.subtract(money).doubleValue();
		if(resDouble<0){
			throw new BusinessException(BusinessExceptionCode.param_error,"余额不足,剩余:"+mm+"元");
		}
		//结算用户的金钱
		serviceResult=new ServiceResult();
		try {
			int uu=iCustomerDao.updateCustomer(new BigDecimal(Double.valueOf(resDouble)),customer_phoneNumber);
			if(uu!=1){
				serviceResult.setResult(uu);
			}else{
				serviceResult.setResult(uu);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error,"结算失败");
		}
		return serviceResult;
	}
	
	
	/**
	 * 随机生成顾客的编码
	 * @param storeCode
	 * @return
	 */
	public String randomCustomerNumber(String storeCode){
		StringBuffer buffer=new StringBuffer();
		if(storeCode!=null && !"".equals(storeCode)){
			buffer.append(storeCode+"_cume_");
		}
		buffer.append(System.currentTimeMillis());
		return buffer.toString();
	}
	
	/**
	 * 随机生成订单number(针对充值订单)
	 * 
	 */
	private String randomOrderRechargeNumber(String storeCode){
		StringBuffer buffer=new StringBuffer();
		if(storeCode!=null && !"".equals(storeCode)){
			buffer.append(storeCode+"orderRecharge");
		}
		buffer.append(System.currentTimeMillis());
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
