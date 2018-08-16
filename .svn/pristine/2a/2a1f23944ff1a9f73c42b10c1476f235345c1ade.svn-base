package com.haier.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.haier.result.ServiceResult;
import com.haier.result.SpringResult;
import com.haier.result.SpringResultCode;

public class ControllerResultInterceptor {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerResultInterceptor.class);
	
	
	/** 
	* @Description: 封装controller接口对应的返回值
	* @param jp
	* @return
	* @throws Throwable  
	*/ 
	public Object around(ProceedingJoinPoint jp){
		SpringResult springResult = new SpringResult();
		// 执行方法
		try {
			Object  proceed= jp.proceed();
			if(proceed instanceof ServiceResult){
				ServiceResult serviceResult = (ServiceResult)proceed;
//				serviceResult.set(scoreResult.getResult());
				springResult.setResultBean(serviceResult.getResult());
				springResult.setResultCode(serviceResult.getResultCode());
				springResult.setMessage(serviceResult.getMessage());
			}else{
				springResult.setResultBean(proceed);
			}
			if(proceed instanceof ModelAndView){
				return proceed;
			}
			if(springResult.getResultCode()==null || "".equals(springResult.getResultCode())){
				springResult.setResultCode(SpringResultCode.SUCCESS);
			}
			LOG.info("==============controller正常返回===============");
			return springResult;
		} catch (Throwable e) {
			springResult.setResultCode(SpringResultCode.RUNTIME_ERROR);
//			springResult.setErrorStackTrack(ThrowableUtil.getStackTrace(e));
			springResult.setMessage(e.getMessage());
			LOG.info("==============controller异常返回===============");
			return springResult;
		} 

	}
}
