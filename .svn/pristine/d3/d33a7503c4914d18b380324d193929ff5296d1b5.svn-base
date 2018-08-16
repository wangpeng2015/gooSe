package com.haier.kj.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.mybatics.service.IUserService;


/**
 * 注册controller，目前没有使用
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/signInController")
public class SignInController{
	
	private static Logger logger = Logger.getLogger(SignInController.class);
	
	@Resource IUserService IUserService;
	
    /**
     * http://localhost:8081/meifa/login/loginController
     * MethodName: login
     * Description: 
     * @author xudp
     */
	@RequestMapping(value="/{mapperInteger}/doSign")
	public String loginController(@PathVariable Integer mapperInteger,String userName,String password){
		logger.info("--------------doSign---------------");
		return null;
	}
}