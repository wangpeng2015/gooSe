package com.haier.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.haier.result.ServiceResult;
import com.haier.result.SpringResultCode;
import com.haier.result.exception.BusinessException;


/** 
 * @ClassName: DubboResultInterceptor 
 * @Description: 对dubbo返回值进行统一封装的方法 
 *  
 */
public class ServiceResultInterceptor {
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiceResultInterceptor.class);
	/** 
	* @Description: 封装service接口对应的返回值
	* @param jp
	* @return
	* @throws Throwable  
	*/ 
	public Object around(ProceedingJoinPoint jp){
		ServiceResult serviceResult = new ServiceResult();
		// 执行方法
		try {
			Object  proceed= jp.proceed();
			if(proceed instanceof ServiceResult){
				serviceResult = (ServiceResult)proceed;
				serviceResult.setResult(serviceResult.getResult());
			}else{
				serviceResult.setResult(proceed);
			}
			serviceResult.setResultCode(SpringResultCode.SUCCESS);
			LOG.info("==============正常返回===============");
			return serviceResult;
		} catch (Throwable e) {
			//异常编码
			if(e instanceof BusinessException){
				BusinessException be = (BusinessException)e;
				if(be.getExceptionCode() != null){
					serviceResult.setResultCode(be.getExceptionCode().getErrorCode());
					serviceResult.setMessage(be.getMessage());
				}else{
					serviceResult.setResultCode(SpringResultCode.BUSINESS_ERROR);
					serviceResult.setMessage(be.getMessage());
				}
			}else{
				serviceResult.setResultCode(SpringResultCode.RUNTIME_ERROR);
				serviceResult.setMessage(e.getMessage());
			}
			//把错误的信息放入栈中，打印到前台
			serviceResult.setErrorStackTrack("错误");
			LOG.info("==============异常返回===============");
			return serviceResult;
		} 

	}
	
}