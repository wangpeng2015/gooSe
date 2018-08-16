package com.haier.kj.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.Hair_customers;
import com.spring.mybatics.service.ICustomerService;
import com.spring.mybatics.util.LoginContext;
import com.spring.mybatics.util.LoginContextUtil;

/**
 * 顾客操作controller
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping(value = "/customerController")
public class CustomerController {

	private static Logger logger = Logger.getLogger(CustomerController.class);

	@Autowired
	private ICustomerService iCustomerService;

	/**
	 * 展示客户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showCustomerView")
	@ResponseBody
	public ModelAndView showCustomerView() {
		ModelAndView modelAndView = new ModelAndView("customers");
		logger.info("--------------showCustomerView---------------");
		return modelAndView;
	}

	/**
	 * 增加顾客
	 * 
	 * @param user
	 * @return 对应数据库表为 meifa.hair_customers
	 */
	@RequestMapping(value = "/doSaveCustomer")
	@ResponseBody
	public Object doSaveCustomer(Hair_customers customers,
			HttpServletRequest request) {
		logger.info("--------------doSaveCustomer---------------");
		// 统一声明返回类型
		ServiceResult serviceResult = iCustomerService.saveCustomer(customers);
		return serviceResult;
	}

	/**
	 * 根据电话删除顾客
	 */
	@RequestMapping(value = "/doDelCustomerByPhone")
	@ResponseBody
	public Object doDelCustomerByPhone(
			@RequestParam("phoneNumber") String phoneNumber,
			HttpServletRequest request) {
		ServiceResult serviceResult = new ServiceResult();
		// 统一声明返回类型
		LoginContext loginContext = LoginContextUtil.getLoginContext(request
				.getSession());
		String roles = loginContext.getSoles();
		if (roles.equals("2")) {
			serviceResult = iCustomerService
					.deleteCustomerByphoneService(phoneNumber);
		} else {
			serviceResult.setMessage("抱歉，只有店长有权限删除");
		}
		return serviceResult;
	}

	/**
	 * 根据电话修改顾客信息
	 */
	@RequestMapping(value = "/doUpdateCustomerByPhone")
	@ResponseBody
	public Object doUpdateCustomerByPhone(Hair_customers customer) {
		return iCustomerService.updateCustomerByphoneService(customer);
	}

	/**
	 * 根据电话充值
	 */
	/*@RequestMapping(value = "/doUpdateCustomerByPhone2")
	@ResponseBody
	public Object doUpdateCustomerByPhone2(
			@RequestParam(value = "applicant_id") int applicant_id,
			@RequestParam(value = "money") BigDecimal money,
			@RequestParam(value = "customer_phoneNumber") String customer_phoneNumber,
			HttpServletRequest request) {
		ServiceResult serviceResult = new ServiceResult();
		// 统一声明返回类型
		LoginContext loginContext = LoginContextUtil.getLoginContext(request
				.getSession());
		String storeCode = loginContext.getLoginUser().getStoreCode();
		String userName = loginContext.getLoginUser().getUserName();
		String roles = loginContext.getSoles();
		if (roles.equals("2") || roles.equals("1")) {
			return serviceResult = iCustomerService
					.updateCustomerByphoneService2(applicant_id, userName,
							money, customer_phoneNumber, storeCode);
		} else {
			serviceResult.setMessage("抱歉，只有店长有权限充值");
		}
		return serviceResult;
	}*/

	/**
	 * 根据id修改顾客信息
	 */
	@RequestMapping(value = "/doUpdateCustomerById")
	@ResponseBody
	public Object doUpdateCustomerById(Hair_customers customer) {
		return iCustomerService.updateCustomerByidService(customer);
	}

	/**
	 * 根据门店编码查询顾客(门店的)
	 */
	/*@RequestMapping(value = "/dofindCustomerByStoreCode")
	@ResponseBody
	public Object dofindCustomerByStoreCode(
			@RequestParam(value = "storeCode", required = false) String storeCode,
			HttpServletRequest request) {
		if (storeCode == null || "".equals(storeCode)) {
			LoginContext loginContext = LoginContextUtil
					.getLoginContext(request.getSession());
			storeCode = loginContext.getLoginUser().getStoreCode();
		}
		ServiceResult listCustomer = iCustomerService
				.searchCustomerByStoreCode(storeCode);
		return listCustomer;
	}*/

	/**
	 * 根据电话或者姓名查询用户列表
	 */
	@RequestMapping(value = "/dofindCustomerList")
	@ResponseBody
	public Object dofindCustomerList(
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
			@RequestParam(value = "customerName", required = false) String customerName) {
		logger.info("----dofindCustomerList----phoneNumber=" + phoneNumber
				+ "---customerName=" + customerName);
		ServiceResult listCustomer = iCustomerService.findCustomerListService(
				phoneNumber, customerName);
		return listCustomer;
	}

	/**
	 * 充值for 门店008
	 */
	/*@RequestMapping(value = "/doUpdateCustomerByPhone3")
	@ResponseBody
	public Object doUpdateCustomerByPhone3(
			@RequestParam(value = "applicant_id") int applicant_id,
			@RequestParam(value = "money") BigDecimal money,
			@RequestParam(value = "customer_phoneNumber") String customer_phoneNumber,
			HttpServletRequest request) {
		logger.info("jieSuanMoneyForCustomer=" + customer_phoneNumber);
		// 统一声明返回类型
		LoginContext loginContext = LoginContextUtil.getLoginContext(request
				.getSession());
		String storeCode = loginContext.getLoginUser().getStoreCode();
		String application_name = loginContext.getLoginUser().getUserName();
		return iCustomerService.doUpdateCustomerByPhone3Service(customer_phoneNumber,money,applicant_id,application_name,storeCode);
	}*/

	/**
	 * 结算for 门店008
	 */
	/*@RequestMapping(value = "/jieSuanMoneyForCustomer")
	@ResponseBody
	public Object jieSuanForCustomer(
			@RequestParam(value = "applicant_id") int applicant_id,
			@RequestParam(value = "money") BigDecimal money,
			@RequestParam(value = "customer_phoneNumber") String customer_phoneNumber,
			HttpServletRequest request) {
		logger.info("jieSuanMoneyForCustomer=" + customer_phoneNumber);
		// 统一声明返回类型
		LoginContext loginContext = LoginContextUtil.getLoginContext(request
				.getSession());
		String storeCode = loginContext.getLoginUser().getStoreCode();
		String application_name = loginContext.getLoginUser().getUserName();
		return iCustomerService.jieSuanForCustomerByPhone3Service(customer_phoneNumber,money,applicant_id,application_name,storeCode);
	}*/

}
