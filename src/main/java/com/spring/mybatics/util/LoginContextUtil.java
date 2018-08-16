/**
* @Company 青鸟软通   
* @Title: LoginContextUtil.java 
* @Package com.haier.hrportal.util 
* @author Liu Wenjie   
* @date 2014-3-4 下午2:30:14 
* @version V1.0   
*/ 
package com.spring.mybatics.util;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.spring.mybatics.domain.Hair_employee;
import com.spring.mybatics.domain.goo.Employee;

/** 
 * @ClassName: LoginContextUtil 
 * @Description: 辅助实例化LoginContext的工具类方法
 */
public class LoginContextUtil {
	
	public static final  String LOGIN_CONTEXT = "loginContext";
	/** 
	* @Title: writeToSession 
	* @Description: 将用户信息以LoginContext的形式保存到Session中
	* @param session
	* @param user  
	* @throws 
	*/ 
	public static void writeToSession(HttpSession session, LoginContext loginContext) {
		session.setAttribute(LOGIN_CONTEXT, loginContext);
	}
	
	
	/** 
	* @Description: 根据用户信息，填写用户缓存信息
	* @author Liu Wenjie   
	* @param user 
	 * @param password 
	* @date 2014-4-21 下午12:52:14 
	* @param session
	* @param empSn  
	 */
	public static LoginContext writeToSession(HttpServletRequest request, Employee user, String username, String userCode){
		LoginContext loginContext = new LoginContext();
		
		loginContext.setUuid(UUID.randomUUID().toString());
		loginContext.setLoginUser(user);
		loginContext.setSoles(user.getRoles());
		
		HttpSession session = request.getSession();
		writeToSession(session, loginContext);
		//店员id
		session.setAttribute(SessionConstants.KEY_USER_ID, user.getId());
		//用户名称
		session.setAttribute(SessionConstants.KEY_USER_NAME, user.getUserName());
		//用户编码
		session.setAttribute(SessionConstants.KEY_USER_CODE, user.getUserCode());
		//用户角色
		session.setAttribute(SessionConstants.KEY_USER_ROLE, user.getRoles());
		return loginContext;
	}
	
	/**
	* @Title: getLoginContext 
	* @Description: 获取登录缓存的统一方法
	* @param session  
	* @throws
	 */
	public static LoginContext getLoginContext(HttpSession session){
		return (LoginContext)session.getAttribute(LOGIN_CONTEXT);
	}

}
