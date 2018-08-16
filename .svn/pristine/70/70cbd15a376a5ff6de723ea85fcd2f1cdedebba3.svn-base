package com.haier.kj.controller.goo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.haier.kj.controller.EmployeeController;
import com.haier.result.ServiceResult;
import com.spring.mybatics.service.goo.GooService;
import com.spring.mybatics.util.SmsDemo;


@Controller
@RequestMapping(value="/gooController")
public class GooController {
	
	private static Logger logger = Logger.getLogger(EmployeeController.class);
	
	@Resource
	private GooService gooService;
	
	
	/**
	 * 页面的登录
	 * @param name
	 * @param passwd
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doLogin")
	@ResponseBody
	public Object loginIn(String name,String passwd,String uid,HttpServletRequest request){
		ServiceResult serviceResult = new ServiceResult();
		if(StringUtils.isBlank(name)){
			serviceResult.setMessage("用户名为空");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		if(StringUtils.isBlank(passwd)){
			serviceResult.setMessage("密码为空");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		serviceResult=gooService.findUserMsg(name,passwd,uid);
		return serviceResult;
	}
	
	/**
	 * 
	* @Description:android的登录  
	* @param phone
	* @param passwd
	* @param confirmPass
	* @param code
	* @param tuijianCode
	* @param request
	* @param response1
	* @return
	*
	* @author wp
	* @date 2018年1月20日 上午11:35:33
	* @throws
	 */
	@RequestMapping(value="/doSign")
	@ResponseBody
	public Object doSign(String phone,String passwd,String confirmPass,String code,String tuijianCode,HttpServletRequest request,HttpServletResponse response1){
		ServiceResult serviceResult = new ServiceResult();
		if(StringUtils.isBlank(phone)){
			serviceResult.setMessage("用户名为空");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		if(StringUtils.isBlank(code)){
			serviceResult.setMessage("验证码为空");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		HttpSession session=request.getSession();
		System.out.println(session.getId());
		Object yanzhengma=session.getAttribute(phone);
		yanzhengma=yanzhengma.toString();
		if(yanzhengma==null || !yanzhengma.equals(code) || yanzhengma.equals("")){
			serviceResult.setMessage("验证码有误");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		if(StringUtils.isBlank(passwd)){
			serviceResult.setMessage("请输入密码");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		if(StringUtils.isBlank(confirmPass)){
			serviceResult.setMessage("请输入确认码");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		phone=phone.trim();
		passwd=passwd.trim();
		confirmPass=confirmPass.trim();
		
		if(!passwd.equals(confirmPass)){
			serviceResult.setMessage("密码和确认码不相符");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		
		serviceResult=gooService.addSignUserMsg(phone,passwd,confirmPass,tuijianCode);
		
		return serviceResult;
	}
	
	/**
	 * 
	* @Description:获取android的验证码  
	* @param phoneNumber
	* @param request
	* @return
	*
	* @author wp
	* @date 2018年1月20日 上午11:35:10
	* @throws
	 */
	@RequestMapping(value="/getYanZhengMa")
	@ResponseBody
	public Object getYanZhengMa(String phoneNumber,HttpServletRequest request){
		logger.info("--------------获取验证码---------------");
		ServiceResult serviceResult = new ServiceResult();
		if(StringUtils.isBlank(phoneNumber)  || phoneNumber.length()!=11){
			serviceResult.setMessage("电话信息为空或者格式有问题");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		//发短信
        SendSmsResponse response;
        HttpSession session=request.getSession();
		try {
			 //存session
        	int ran=(int)((Math.random()*9+1)*10000);
        	logger.info("验证码--"+phoneNumber+"--"+ran);
			response = SmsDemo.sendSms(phoneNumber,ran);
			logger.info("短信接口返回的数据----------------");
			logger.info("Code=" + response.getCode());
			logger.info("Message=" + response.getMessage());
			logger.info("RequestId=" + response.getRequestId());
			logger.info("BizId=" + response.getBizId());
	        //查明细
	        if(response.getCode() != null && response.getCode().equals("OK")) {
	            session.setAttribute(phoneNumber, ran);
	            logger.info(session.getId());
	        }
		} catch (Exception e) {
			logger.info("短信发送失败");
			logger.info(e.toString());
			serviceResult.setMessage("短信发送失败");
			serviceResult.setResultCode("400");
			return serviceResult;
		}
		return phoneNumber;
	}
	
	
  /**
	* 
	* @Description:获取狼播信息  
	* @param phoneNumber
	* @param request
	* @return
	*
	* @author wp
	* @date 2018年1月1日 下午8:44:51
	* @throws
	 */
	@RequestMapping(value="/getLBMessage")
	@ResponseBody
	public Object getLBMessage(String phoneNumber){
		logger.info("--------------getLBMessage---------------");
		ServiceResult serviceResult = new ServiceResult();
		serviceResult=gooService.getLBMessageService();
		return  serviceResult;
	}
	
	public static void main(String[] args) throws Exception {
		//发短信
		String phoneNumber="18300247760";
        SendSmsResponse response = SmsDemo.sendSms(phoneNumber,123);
        System.out.println("短信接口返回的数据----------------");
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

        Thread.sleep(3000L);

        //查明细
        if(response.getCode() != null && response.getCode().equals("OK")) {
            QuerySendDetailsResponse querySendDetailsResponse = SmsDemo.querySendDetails(response.getBizId());
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for(QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO["+i+"]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }
	}
	
	/**
	 * 
	* @Description:ios的登录  
	* @param phone
	* @param passwd
	* @param confirmPass
	* @param code
	* @param tuijianCode
	* @param request
	* @param response1
	* @param uuid
	* @return
	*
	* @author wp
	* @date 2018年1月20日 上午11:34:54
	* @throws
	 */
	@RequestMapping(value="/doIosSign")
	@ResponseBody
	public Object doIosSign(String phone,String passwd,String confirmPass,String code,String tuijianCode,HttpServletRequest request,HttpServletResponse response1,String uuid){
		ServiceResult serviceResult = new ServiceResult();
		
		if(StringUtils.isBlank(phone)){
			serviceResult.setMessage("用户名为空");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		
		if(StringUtils.isBlank(passwd)){
			serviceResult.setMessage("请输入密码");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		if(StringUtils.isBlank(confirmPass)){
			serviceResult.setMessage("请输入确认码");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		phone=phone.trim();
		passwd=passwd.trim();
		confirmPass=confirmPass.trim();
		
		if(!passwd.equals(confirmPass)){
			serviceResult.setMessage("密码和确认码不相符");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		
		serviceResult=gooService.addIosSignUserMsg(phone,passwd,confirmPass,tuijianCode,uuid);
		
		return serviceResult;
	}
	
	/**
	 * 
	* @Description:获取ios的验证码  
	* @param phoneNumber
	* @param request
	* @param response1
	* @return
	*
	* @author wp
	* @date 2018年1月20日 上午11:34:37
	* @throws
	 */
	@RequestMapping(value="/getIosYanZhengMa")
	@ResponseBody
	public Object getIosYanZhengMa(String phoneNumber,HttpServletRequest request,HttpServletResponse response1){
		logger.info("--------------获取验证码---------------");
		ServiceResult serviceResult = new ServiceResult();
		if(StringUtils.isBlank(phoneNumber)  || phoneNumber.length()!=11){
			serviceResult.setMessage("电话信息为空或者格式有问题");
			serviceResult.setResultCode("300");
			return serviceResult;
		}
		//发短信
        SendSmsResponse response;
		try {
			 //存session
        	int ran=(int)((Math.random()*9+1)*10000);
        	logger.info("验证码--"+phoneNumber+"--"+ran);
			response = SmsDemo.sendSms(phoneNumber,ran);
			logger.info("短信接口返回的数据----------------");
			logger.info("Code=" + response.getCode());
			logger.info("Message=" + response.getMessage());
			logger.info("RequestId=" + response.getRequestId());
			logger.info("BizId=" + response.getBizId());
	        //查明细
	        if(response.getCode() != null && response.getCode().equals("OK")) {
	        	serviceResult.setMessage("获取验证码成功");
	        	serviceResult.setResult(ran);
	        	serviceResult.setResultCode("200");
	        	return serviceResult;
	        }
		} catch (Exception e) {
			logger.info("短信发送失败");
			logger.info(e.toString());
			serviceResult.setMessage("短信发送失败");
			serviceResult.setResultCode("400");
			return serviceResult;
		}
		return serviceResult;
	}
	
	
	/**
	 * 
	* @Description: 判断手机号是否在数据库中
	* @param phone
	* @return
	*
	* @author wp
	* @date 2018年1月20日 上午11:37:54
	* @throws
	 */
	@RequestMapping(value="/isPhoneInLangBo")
	@ResponseBody
	public Object isPhoneInLangBo(String phone) {
		ServiceResult serviceResult = new ServiceResult();
		//如果电话不为空
		if(!StringUtils.isBlank(phone)) {
			serviceResult=gooService.isPhoneInLangBoService(phone.trim());
		}else {
			serviceResult.setResultCode("200");
			serviceResult.setMessage("电话为空");
			serviceResult.setResult(null);
		}
		return serviceResult;
	}
	
	
	/**
	 * 
	* @Description: 版本升级
	* @param request
	* @param response
	* @return
	*
	* @author wp
	* @date 2018年3月4日 下午7:34:38
	* @throws
	 */
	@RequestMapping(value="/isLatestAppVersion")
	@ResponseBody
	public Object isLatestAppVersion(String version) {  
		//查询最新版本
		ServiceResult serviceResult=gooService.findLatestVersion(version);
		
		return serviceResult;
		
	}
		

}
