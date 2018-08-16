package com.spring.mybatics.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.spring.mybatics.service.IOrderService;
import com.spring.mybatics.util.MD5Util;



@Service("orderService")
public class OrderServiceImpl implements IOrderService{

	/**
	 * 
	 * 订单
	 */
	@Resource
	private IOrderDao iOrderDao;
	/**
	 * 客户vip
	 */
	@Resource
	private ICustomerDAO iCustomerDao;
	/**
	 * 员工与单据
	 */
	@Resource
	private IEmployeeDanDao iEmployeeDanDao;
	/**
	 * 门店收入
	 */
	@Resource
	private IStoreIncomeDao iStoreIncomeDao;
	
	/**
	 * 
	 * 查询所有单据
	 */
	@Override
	public ServiceResult getAllOrder(String storeCode) {
		ServiceResult serviceResult=new ServiceResult();
		//查询订单
		List<Hair_danju> listDanju=iOrderDao.getAllOrderDAO(storeCode);
		if(listDanju!=null && listDanju.size()>0){
			serviceResult.setResult(listDanju);
		}else{
			serviceResult.setMessage("没有查询到订单信息");
		}
		return serviceResult;
	}

	/**
	 * 
	 * 保存单据,在保存订单的时候就完成了审核
	 * 如果是会员，则进行客户金钱扣减，店面金额累加，员工单据的保存
	 * 如果是非会员，则进行店面金额的累加和员工单据的保存
	 * 
	 */
	@Override
	public ServiceResult saveDanjuService(Hair_danju danju,String customer_pass) {
		ServiceResult serviceResult=null;
		//随机生成订单的编码
		danju.setDanju_code(randomOrderNumber(danju.getDanju_storesCode()));
		if(danju.getDanju_applicant_id()<=0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请填写申请人信息");
		}
		BigDecimal price=danju.getDanju_price();
		int cop=price.compareTo(BigDecimal.ZERO);
		if(cop<=0){
			throw new BusinessException(BusinessExceptionCode.param_error, "消费金额不能小于等于0");
		}
		
		danju.setDanju_isConfirm("已完成");
		//会员
		if("1".equals(danju.getDanju_isVip()) && !StringUtils.isBlank(customer_pass)){
			//判断客户的金钱是否足够
			Hair_customers moneyAndPass=iCustomerDao.findCustomerMoneyByPhoneNumber(danju.getCustomer_phone(),danju.getDanju_storesCode());
			if(moneyAndPass==null){
				throw new BusinessException(BusinessExceptionCode.param_error,"客户是否是会员");
			}
			//客户的密码
			String custPass=moneyAndPass.getCustomer_password();
			if(!MD5Util.getMD5(customer_pass).equals(custPass)){
				throw new BusinessException(BusinessExceptionCode.param_error,"客户密码错误,请重新输入");
			}
			BigDecimal money=moneyAndPass.getCustomer_money();
			//用户金钱减去单据价格
			double resDouble=money.subtract(danju.getDanju_price()).doubleValue();
			if(resDouble<0){
				throw new BusinessException(BusinessExceptionCode.param_error,"余额不足,剩余:"+money+"元");
			}
			//更新用户金钱
			try {
				int uu=iCustomerDao.updateCustomer(new BigDecimal(Double.valueOf(resDouble)),danju.getCustomer_phone());
				if(uu!=1){
					throw new BusinessException(BusinessExceptionCode.param_error,"更新用户金额失败");
				}
				//保存店员和单据价格字段
				int empdan=iEmployeeDanDao.saveEmployeeDanDao(danju.getDanju_applicant_id(),danju.getDanju_code(),danju.getDanju_price());
				if(empdan!=1){
					throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
				}
				serviceResult=new ServiceResult();
				//所有的订单金额都保存在store_actOrderMoney中
				int income=iStoreIncomeDao.updateStore_actOrderMoney(danju.getDanju_storesCode(),danju.getDanju_price());
				if(income!=1){
					throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
				}
			} catch (Exception e) {
				throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
			}
		//非会员
		}else if("0".equals(danju.getDanju_isVip())){
			try {
				//保存店员和单据价格字段
				int empdan=iEmployeeDanDao.saveEmployeeDanDao(danju.getDanju_applicant_id(),danju.getDanju_code(),danju.getDanju_price());
				if(empdan!=1){
					throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
				}
				serviceResult=new ServiceResult();
				//所有的订单都保存在store_actOrderMoney中
				int income=iStoreIncomeDao.updateStore_actOrderMoney(danju.getDanju_storesCode(),danju.getDanju_price());
				if(income!=1){
					throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
				}
			} catch (Exception e) {
				throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
			}
			
		//无身份人员
		}else{
			throw new BusinessException(BusinessExceptionCode.param_error,"请明确客户的身份，是否会员,如果是会员则必须填写密码");
		}
		//保存单据
		try {
			int id=iOrderDao.save(danju);
			if(id!=1){
				throw new BusinessException(BusinessExceptionCode.param_error,"保存单据失败!");
			}else{
				serviceResult.setResult(id);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error,"保存单据失败!");
		}
		return serviceResult;
	}

	/**
	 * 
	 * 处理订单，需要优化
	 * 
	 */
	@Override
	public ServiceResult dealOrderService(int danju_id) {
		//判断订单是否存在
		if(danju_id<=0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请确认订单是否存在:缺少订单id");
		}
		//根据单据的id获取单据的信息
		Hair_danju danju=iOrderDao.getOrderByID(danju_id);
		if(danju==null){
			throw new BusinessException(BusinessExceptionCode.param_error, "请确认订单是否存在:没有查询到");
		}
		if(null==danju.getCustomer_phone()){
			throw new BusinessException(BusinessExceptionCode.param_error, "请完善单据,需要客户电话信息");
		}
		BigDecimal price=danju.getDanju_price();
		if(null!=price){
			int cop=price.compareTo(BigDecimal.ZERO);
			if(cop==0){
				throw new BusinessException(BusinessExceptionCode.param_error, "订单金额不可以为0元");
			}
			if(cop<0){
				throw new BusinessException(BusinessExceptionCode.param_error, "请完善单据,需要订单金额");
			}
		}else{
			throw new BusinessException(BusinessExceptionCode.param_error, "请完善单据,需要订单金额");
		}
		if(null==danju.getDanju_storesCode()){
			throw new BusinessException(BusinessExceptionCode.param_error, "请完善单据,需要门店编码");
		}
		ServiceResult serviceResult=new ServiceResult();
		
		//更新审核字段
		int isSave=0;
		try {
			isSave=iOrderDao.updateDanjuConfirm(danju_id);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		if(isSave==1){
			//判断是会员
			if("1".equals(danju.getDanju_isVip())){
				//判断客户的金钱是否足够
				Hair_customers moneyAndPass=iCustomerDao.findCustomerMoneyByPhoneNumber(danju.getCustomer_phone(),danju.getDanju_storesCode());
				if(moneyAndPass==null){
					throw new BusinessException(BusinessExceptionCode.param_error,"客户是否是会员");
				}
				//客户的密码
				String custPass=moneyAndPass.getCustomer_password();
				BigDecimal money=moneyAndPass.getCustomer_money();
				//用户金钱减去单据价格
				double resDouble=money.subtract(danju.getDanju_price()).doubleValue();
				if(resDouble<=0){
					throw new BusinessException(BusinessExceptionCode.param_error,"余额不足,剩余:"+money+"元");
				}
				//更新用户金钱
				try {
					int uu=iCustomerDao.updateCustomer(new BigDecimal(Double.valueOf(resDouble)),danju.getCustomer_phone());
					if(uu==1){
						//保存店员和单据价格字段
						int empdan=iEmployeeDanDao.saveEmployeeDanDao(danju.getDanju_applicant_id(),danju.getDanju_code(),danju.getDanju_price());
						if(empdan==1){
							//更新店的金额
							BigDecimal storeMoney=iStoreIncomeDao.getStoreIncomeByStoreCode(danju.getDanju_storesCode());
							BigDecimal addStoreMoney=storeMoney.add(danju.getDanju_price());
							//所有的订单金额都保存在store_actOrderMoney中
							int income=iStoreIncomeDao.updateStore_actOrderMoney(danju.getDanju_storesCode(),addStoreMoney);
							if(income!=1){
								throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
							}
							serviceResult.setResultCode("200");
							serviceResult.setResult(income);
							serviceResult.setMessage("success");
						}else{
							throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
						}
					}
				} catch (Exception e) {
					throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
				}
			//不是会员
			}else{
				//保存店员和单据价格字段
				int empdan=iEmployeeDanDao.saveEmployeeDanDao(danju.getDanju_applicant_id(),danju.getDanju_code(),danju.getDanju_price());
				if(empdan==1){
					//更新店的金额
					BigDecimal storeMoney=iStoreIncomeDao.getStoreIncomeByStoreCode(danju.getDanju_storesCode());
					BigDecimal addStoreMoney=storeMoney.add(danju.getDanju_price());
					//所有的订单都保存在store_actOrderMoney中
					int income=iStoreIncomeDao.updateStore_actOrderMoney(danju.getDanju_storesCode(),addStoreMoney);
					if(income!=1){
						throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
					}
					serviceResult.setResultCode("200");
					serviceResult.setResult(income);
					serviceResult.setMessage("success");
				}else{
					throw new BusinessException(BusinessExceptionCode.param_error,"处理订单失败");
				}
			}
		}else{
			throw new BusinessException(BusinessExceptionCode.param_error,"审核失败");
		}
		return serviceResult;
	}
	
	
	/**
	 * 
	 * 根据id作废订单
	 */
	@Override
	public ServiceResult updateInvalidOrderService(int danju_id) {
		ServiceResult serviceResult=null;
		//判断订单是否存在
		if(danju_id<=0){
			throw new BusinessException(BusinessExceptionCode.param_error, "请确认作废订单是否存在");
		}
		//根据单据的id获取单据的信息
		Hair_danju danju=iOrderDao.getOrderByID(danju_id);
		if(danju==null){
			throw new BusinessException(BusinessExceptionCode.param_error, "请确认订单是否存在:没有查询到");
		}
		if(danju.getDanju_isConfirm().equals("已完成")){
			throw new BusinessException(BusinessExceptionCode.param_error, "抱歉,已经完成的订单无法作废");
		}
		try {
			serviceResult=new ServiceResult();
			int res=iOrderDao.updateInvalidOrderDao(danju_id);
			if(res==1){
				serviceResult.setResult(res);
			}else{
				serviceResult.setMessage("操作失败");
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionCode.param_error, "操作失败!");
		}
		
		return serviceResult;
	}
	
	/**
	  * 店长获取的店的订单数量和订单金额和店的金额
      */
	@Override
	public ServiceResult getStoreOrderInfoService(String storeCode) {
		ServiceResult serviceResult=null;
		HashMap<String, String> map=null;
		map=iOrderDao.getStoreOrderInfoDao(storeCode);
		if(map!=null){
			serviceResult=new ServiceResult();
			serviceResult.setResult(map);
		}
		return serviceResult;
	}
	
	/**
	 * 店员获取自己的订单信息
	 */
	@Override
	public ServiceResult getEmpOrderInfoService(String storeCode,
			String userId) {
		ServiceResult serviceResult=null;
		HashMap<String, String> map=null;
		map =iOrderDao.getEmpOrderInfoDao(storeCode,userId);
		if(map!=null){
			serviceResult=new ServiceResult();
			serviceResult.setResult(map);
		}
		return serviceResult;
	}
	
	/**
	 * 查询员工自己的订单
	 */
	@Override
	public ServiceResult getEmpOrderListService(String storeCode, String userId) {
		ServiceResult serviceResult=null;
		List<Hair_danju> listDanju=iOrderDao.getEmpListDao(storeCode,userId);
		if(listDanju!=null && listDanju.size()>0){
			serviceResult=new ServiceResult();
			serviceResult.setResult(listDanju);
		}
		return serviceResult;
	}
	
	/**
	 * 根据各种条件查询订单
	 */
	@Override
	public ServiceResult getOrderLisyBuanyParamService(Hair_danju danju) {
		ServiceResult serviceResult=null;
		List<Hair_danju> listDanju=iOrderDao.getOrderLisyBuanyParamDao(danju);
		if(listDanju!=null){
			serviceResult=new ServiceResult();
			serviceResult.setResult(listDanju);
		}
		return serviceResult;
	}
	
	/**
	 * 获得25天前的那一天的客户订单
	 */
	public ServiceResult get25DaysCustomerOrdersService(int application_id,String storeCode){
		if(application_id<0){
			throw new BusinessException(BusinessExceptionCode.param_error, "确认是否登录!");
		}
		ServiceResult serviceResult=null;
		List<HashMap<String, String>> listmap=null;
		listmap=iEmployeeDanDao.get25DaysCustomerOrdersDao(application_id, storeCode);
		if(listmap!=null){
			serviceResult=new ServiceResult();
			serviceResult.setResult(listmap);
		}
		return serviceResult;
	}
	
	/**
	 * 随机生成订单number
	 * 
	 */
	private String randomOrderNumber(String storeCode){
		StringBuffer buffer=new StringBuffer();
		if(storeCode!=null && !"".equals(storeCode)){
			buffer.append(storeCode+"order");
		}
		buffer.append(System.currentTimeMillis());
		return buffer.toString();
	}
}
