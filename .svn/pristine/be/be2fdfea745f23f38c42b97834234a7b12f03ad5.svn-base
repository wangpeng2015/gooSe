package com.haier.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class InterceptorDemo01 extends HandlerInterceptorAdapter{
	
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
	        throws Exception {  
			System.out.println("preHandle");
	        return true;  
	    }  
		//也就是Controller 方法调用之后执
	    public void postHandle(  
	            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)  
	            throws Exception { 
	    	super.postHandle(request, response, handler, modelAndView);
	    }  
	    public void afterCompletion(  
	            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
	            throws Exception {  
	    	System.out.println("afterCompletion");
	    } 

}
