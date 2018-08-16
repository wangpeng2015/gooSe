/**
* @Company 青鸟软通   
* @Title: BusinessException.java 
* @Package com.jbinfo.common.exception 
* @author Liu Wenjie   
* @date 2013-11-8 下午12:26:36 
* @version V1.0   
*/ 
package com.haier.result.exception;

import com.haier.result.exception.ExceptionConstants.BusinessExceptionCode;


/** 
 * @ClassName: BusinessException 
 * @Description: 业务异常 
 *  
 */
public class BusinessException extends RuntimeException {

		
	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = -7745154374032695042L;
	
	protected BusinessExceptionCode exceptionCode;
	

	/** 
	 * <p>Description: </p> 
	 * @author Liu Wenjie   
	 * @date 2013-11-8 下午12:26:36  
	 */
	public BusinessException(BusinessExceptionCode exceptionCode) {
		super(exceptionCode.getContext());
		this.exceptionCode = exceptionCode;
	}

	/** 
	 * <p>Description: </p> 
	 * @author Liu Wenjie   
	 * @date 2013-11-8 下午12:26:36 
	 * @param message 
	 */
	public BusinessException(BusinessExceptionCode exceptionCode,String message) {
		super(message);
		this.exceptionCode = exceptionCode;
	}

	/** 
	 * <p>Description: </p> 
	 * @author Liu Wenjie   
	 * @date 2013-11-8 下午12:26:36 
	 * @param cause 
	 */
	public BusinessException(BusinessExceptionCode exceptionCode,Throwable cause) {
		super(cause);
		this.exceptionCode = exceptionCode;
	}

	/** 
	 * <p>Description: </p> 
	 * @author Liu Wenjie   
	 * @date 2013-11-8 下午12:26:36 
	 * @param message
	 * @param cause 
	 */
	public BusinessException(BusinessExceptionCode exceptionCode,String message, Throwable cause) {
		super(message, cause);
		this.exceptionCode = exceptionCode;
	}

	/**
	 * @Description: 属性 exceptionCode 的get方法 
	 * @return exceptionCode
	 */
	public BusinessExceptionCode getExceptionCode() {
		return exceptionCode;
	}
	

}
