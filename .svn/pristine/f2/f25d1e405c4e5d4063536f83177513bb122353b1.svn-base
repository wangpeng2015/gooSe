/**
* @Company 青鸟软通   
* @Title: DubboResult.java 
* @Package com.haier.isales.system.dto 
* @author Cao Rui   
* @date 2014-11-6 下午4:22:33 
* @version V1.0   
*/ 
package com.haier.result;

import java.io.Serializable;


/** 
 * @ClassName: DubboResult 
 * @Description: buddo接口统一的返回值类型
 *  
 */
public class SpringResult implements Serializable {

	private static final long serialVersionUID = -4114045990410261418L;
//	private PageResult<T> pageResult;	//带分页的结果列表
	private Object resultBean;	//单条实体返回时
	private String resultCode;	//返回结果编码
	private String Message;	//返回结果信息
	private String errorStackTrack;	//返回结果的堆栈信息
	
	/** 
	* <p>Description: </p> 
	* @author Guo Yuchao   
	* @date 2014-11-10 下午7:32:58  
	*/ 
	public SpringResult() {
	}
	public SpringResult(Object resultBean,String resultCode,String message,String errorStackTrack) {
		this.resultBean = resultBean;
		this.resultCode = resultCode;
		this.Message = message;
		this.errorStackTrack = errorStackTrack;
	}
	
	/** 
	* @Description:判断Dubbo结果是否是成功
	* @author Liu Wenjie   
	* @date 2014-11-21 下午10:40:47 
	* @return  
	*/ 
	public static boolean isSuccess(SpringResult result){
		return result != null && SpringResultCode.SUCCESS.equals(result.getResultCode());
	}
	
	/**
	 * @Description: 属性 resultBean 的get方法 
	 * @return resultBean
	 */
	@SuppressWarnings("unchecked")
	public <T> T getResultBean() {
		return (T)resultBean;
	}
	/**
	 * @Description: 属性 resultBean 的set方法 
	 * @param resultBean 
	 */
	public void setResultBean(Object resultBean) {
		this.resultBean = resultBean;
	}
	/**
	 * @Description: 属性 resultCode 的get方法 
	 * @return resultCode
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * @Description: 属性 resultCode 的set方法 
	 * @param resultCode 
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public String getMessage() {
		return Message;
	}
	
	public void setMessage(String message) {
		Message = message;
	}
	/**
	 * @Description: 属性 errorStackTrack 的get方法 
	 * @return errorStackTrack
	 */
	public String getErrorStackTrack() {
		return errorStackTrack;
	}
	/**
	 * @Description: 属性 errorStackTrack 的set方法 
	 * @param errorStackTrack 
	 */
	public void setErrorStackTrack(String errorStackTrack) {
		this.errorStackTrack = errorStackTrack;
	}
	@Override
	public String toString() {
		return "SpringResult [resultBean=" + resultBean + ", resultCode="
				+ resultCode + ", Message=" + Message + ", errorStackTrack="
				+ errorStackTrack + "]";
	}
	
}
