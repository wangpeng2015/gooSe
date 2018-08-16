package com.haier.kj.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.Hair_danju;
import com.spring.mybatics.service.IOrderService;
import com.spring.mybatics.util.LoginContext;
import com.spring.mybatics.util.LoginContextUtil;

/**
 * 
 * 订单操作controller
 * @author wp
 *
 */
@Controller
@RequestMapping(value="/orderController")
public class OrderController {
	
	private static Logger logger = Logger.getLogger(OrderController.class);
	
	@Autowired
	private IOrderService orderService;
	
	
	 /**
     * 展示订单列表
     * @return
     */
    @RequestMapping(value="/showOrderView")
    @ResponseBody
    public ModelAndView showOrderView(){
    	ModelAndView modelAndView=new ModelAndView("orders");
    	logger.info("--------------showOrderView---------------");
		return modelAndView;
    }
	/**
	 * 备用
	 * localhost:8081/meifa/orderController/doDealOrder?danju_id=1
	 * 店长审核订单
	 * @return
	 */
	@RequestMapping(value="/doDealOrder")
	@ResponseBody
	public Object doDealorder(@RequestParam(value="danju_id")int danju_id,HttpServletRequest request){
		logger.info("--------------doDealorder---------------");
		LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
		//判断是否是店长
		ServiceResult serviceResult = new ServiceResult();
		if(loginContext!=null){
			if("2".equals(loginContext.getSoles().trim())){
				serviceResult=orderService.dealOrderService(danju_id);
			}else{
				serviceResult.setMessage("抱歉，只有店长有权限审核");
			}
		}
		return serviceResult;
	}
	
	/**
	 * 店长作废订单
	 */
	@RequestMapping(value="/doInvalidOrder")
    @ResponseBody
    public Object doInvalidOrder(@RequestParam(value="danju_id")int danju_id,HttpServletRequest request){
		logger.info("--------------doInvalidOrder---------------");
		LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
		//判断是否是店长
		ServiceResult serviceResult = new ServiceResult();
		if(loginContext!=null){
			if("2".equals(loginContext.getSoles().trim())){
				serviceResult=orderService.updateInvalidOrderService(danju_id);
			}else{
				serviceResult.setMessage("抱歉，只有店长有权限作废订单");
			}
		}
		return serviceResult;
	}
	
	/**
	 * 提交保存订单
	 * @param danju
	 * @return
	 */
	@RequestMapping(value="/doRequestSaveOrder",method=RequestMethod.POST)
    @ResponseBody
    public Object doRequestSaveOrder(Hair_danju danju,String customer_pass){
		logger.info("--------------doRequestSaveOrder---------------");
		ServiceResult res=orderService.saveDanjuService(danju,customer_pass);
		return res;
	}
	
	
	/**
	 * 
	 * 查询出门店的所有的订单,根据店面的id查询出所在点的订单列表
	 * @return
	 */
	/*@RequestMapping(value="/doGetOrderList")
    @ResponseBody
    public Object doGetOrderList(HttpServletRequest request,@RequestParam(value="storeCode",required = false)String storeCode){
		logger.info("--------------doGetOrderList---------------");
		if(storeCode==null || "".equals(storeCode)){
			LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
	    	storeCode=loginContext.getLoginUser().getStoreCode();
		}
		ServiceResult list_order =orderService.getAllOrder(storeCode);
		return list_order;
	}*/
	
	
	 /**
	  * 店长获取的店的订单数量和订单金额和店的金额
      */
	/*@RequestMapping(value="/dogetManagerOrderInfo")
    @ResponseBody
    public Object dogetManagerOrderInfo(HttpServletRequest request,@RequestParam(value="storeCode",required = false)String storeCode){
		logger.info("--------------dogetManagerOrderInfo---------------");
		if(storeCode==null || "".equals(storeCode)){
			LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
	    	storeCode=loginContext.getLoginUser().getStoreCode();
		}
		ServiceResult map=orderService.getStoreOrderInfoService(storeCode);
		return map;
	}*/
	
	
	/**
	 * 店员获取自己的订单数量和自己订单金额
	 */
	/*@RequestMapping(value="/dogetEmpOrderInfo")
    @ResponseBody
    public Object dogetEmpOrderInfo(HttpServletRequest request,@RequestParam(value="storeCode",required = false)String storeCode,
    		@RequestParam(value="userId")String userId){
		logger.info("--------------dogetEmpOrderInfo---------------");
		if(storeCode==null || "".equals(storeCode)){
			LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
	    	storeCode=loginContext.getLoginUser().getStoreCode();
		}
		ServiceResult map=orderService.getEmpOrderInfoService(storeCode,userId);
		return map;
	}*/
	
	/**
	 * 店员获取自己的订单列表
	 */
	/*@RequestMapping(value="/dogetEmpOrderList")
    @ResponseBody
    public Object dogetEmpOrderList(HttpServletRequest request,@RequestParam(value="storeCode",required = false)String storeCode,
    		@RequestParam(value="userId")String userId){
		logger.info("--------------dogetEmpOrderList---------------");
		if(storeCode==null || "".equals(storeCode)){
			LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
	    	storeCode=loginContext.getLoginUser().getStoreCode();
		}
		ServiceResult map=orderService.getEmpOrderListService(storeCode,userId);
		return map;
	}*/
	
	
	/**
	 * 根据条件查询订单
	 */
	@RequestMapping(value="/doGetOrderListByanyParam")
    @ResponseBody
    public Object dogetOrderListByanyParam(HttpServletRequest request,Hair_danju danju){
		logger.info("--------------dogetOrderListByanyParam---------------");
		ServiceResult listDanju=orderService.getOrderLisyBuanyParamService(danju);
		return listDanju;
	}
	
	/**
	 * 
	 */
	/*@RequestMapping(value="/get25DaysCustomerOrders")
    @ResponseBody
    public Object get25DaysCustomerOrders(HttpServletRequest request,@RequestParam(value="application_id")int application_id,@RequestParam(value="storeCode",required = false)String storeCode){
		logger.info("--------------dogetOrderListByanyParam---------------");
		if(storeCode==null || "".equals(storeCode)){
			LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
	    	storeCode=loginContext.getLoginUser().getStoreCode();
		}
		ServiceResult listDanju=orderService.get25DaysCustomerOrdersService(application_id,storeCode);
		return listDanju;
	}*/
	
}
