package com.haier.kj.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.Hair_employee;
import com.spring.mybatics.domain.goo.Employee;
import com.spring.mybatics.service.IUserService;
import com.spring.mybatics.util.CaptchaUtil;
import com.spring.mybatics.util.LoginContext;
import com.spring.mybatics.util.LoginContextUtil;
import com.spring.mybatics.util.SessionConstants;


/**
 * 员工操作controller
 * @author wp
 *
 */
@Controller
@RequestMapping(value="/employeeController")
public class EmployeeController{
	
	private static Logger logger = Logger.getLogger(EmployeeController.class);

    /**
     * 注入userService
     */
    @Autowired
    private IUserService userService;
    
    
    /**
     * 展示员工列表
     * @return
     */
    @RequestMapping(value="/showEmployeeView")
    @ResponseBody
    public ModelAndView showEmployeeView(){
    	ModelAndView modelAndView=new ModelAndView("employees");
    	logger.info("--------------showEmployeeView---------------");
		return modelAndView;
    }

    /**
     * 登录功能
     * 访问方式：localhost:8081/meifa/employeeController/doLogin?userName=王鹏&passWord=123
     * @param user
     * @return
     * 对应数据库表为 meifa.hair_employee
     */
    @RequestMapping(value="/doLogin")
    @ResponseBody
    public Object doLoginMember(Employee user,HttpServletRequest request,String randomString){
    	  request.getSession().removeAttribute(SessionConstants.KEY_USER_ID);
    	  logger.info("--------------login-start---------------");
    	  logger.info("userName="+user.getPhoneNumber()+",PassWord="+user.getPassWord());
    	  //String str=(String) request.getSession().getAttribute("randomString");
    	  logger.info("--------------login-end---------------");
    	  //统一声明返回类型
    	  ServiceResult serviceResult=userService.findUserService(user);
    	  Employee resUser=(Employee) serviceResult.getResult();
    	  if(resUser!=null){
    		//保存用户信息到session
    		  LoginContextUtil.writeToSession(request, resUser,resUser.getUserName(),resUser.getUserCode());
    	  }
    	  return serviceResult;
     }
    
    /**
     * 验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        CaptchaUtil.outputCaptcha(request, response);
    }
    
    /**
     * 注销功能
     */
    @RequestMapping(value="/doWriteOff")
    @ResponseBody
    public void doWriteOff(HttpServletRequest request){
    	  logger.info("--------------doWriteOff---------------");
    	  request.getSession().invalidate();
     }
    
    /**
     * 添加人员功能,添加的人员是店员或者店长而不是顾客，顾客的人员信息修改是在顾客表中，hair_customers表
     * 访问方式：localhost:8081/meifa/employeeController/doSave
     * @param user
     * @return
     */
    /*@RequestMapping(value="/doSave")
    @ResponseBody
    public Object doSaveMember(Hair_employee user){
    	ServiceResult serviceResult=userService.saveUser(user);
    	return serviceResult;
    }*/
    
    /**
     * 根据员工id删除员工
     * 访问方式：localhost:8081/meifa/employeeController/doDelete?id=4
     * @param user
     * @return
     */
    @RequestMapping(value="doDelete")
    @ResponseBody
    public Object doDelete(Hair_employee user,HttpServletRequest request){
    	ServiceResult serviceResult = new ServiceResult();
    	//统一声明返回类型
    	LoginContext loginContext=LoginContextUtil.getLoginContext(request.getSession());
    	String roles=loginContext.getSoles();
    	if(roles.equals("2")){
    		serviceResult=userService.deleteUserById(user);
    	}else{
    		serviceResult.setMessage("抱歉，只有店长有权限删除");
    	}
		return serviceResult;
    }
    
    /**
     * 根据员工phoneNumber删除员工信息
     * @param user
     * @return
     */
    @RequestMapping(value="/doDeleteByphoneNumber")
    @ResponseBody
    public Object doDeleteByphoneNumber(Hair_employee user){
  	    ServiceResult deleteId=userService.deleteUserByPhoneNService(user);
		return deleteId;
    }
    
    
    /**
     * 查询会员根据电话或者姓名 hair_employee表有些过时，我们单独建立一个表示放客户信息的那就是  hair_customers
     * localhost:8081/meifa/employeeController/doSearchUserByphoneOrName?userName=wangpeng
     */
    @RequestMapping(value="/doSearchUserByphoneOrName")
    @ResponseBody
    public Object doSearchUserByphoneOrName(Hair_employee user){
    	ServiceResult listUser=userService.searchUserByphoneOrNameService(user);
		return listUser;
    }
    
    /**
     * 更新用户根据id
     * 
     * localhost:8081/meifa/employeeController/doUpdateUserById?id=4
     */
    /*@RequestMapping(value="/doUpdateUserById")
    @ResponseBody
    public Object doUpdateUserById(Hair_employee user){
  	    ServiceResult res=userService.updateUserByIdService(user);
  	    return res;
    }*/
    
    /**
     * 查询员工列表
     */
    @RequestMapping(value="/doFindCustomersList")
    @ResponseBody
    public Object doFindCustomersList(Employee employee,HttpServletRequest request,int currentPage,int pageSize){
    	ServiceResult res=userService.findCustomerList(employee,currentPage,pageSize);
		return res;
    }
    
    /**
     * 修改客户时间
     */
    
    @RequestMapping(value="/updateCustomersDateEnd")
    @ResponseBody
    public Object updateCustomersDateEnd(String userName,String endDate){
    	ServiceResult res=userService.updateCustomerService(userName,endDate);
		return res;
    }
    
    /**
     * 删除用户根据手机号
     */
    
    @RequestMapping(value="/deleteCustomersByphone")
    @ResponseBody
    public Object deleteCustomersByphone(String userName){
    	ServiceResult res=userService.deleteCustomersByphoneService(userName);
		return res;
    }
    
    /**
     * 重置手机信息
    * @Description: 重置手机信息
    * @param userName
    * @return
    *
    * @author wp
    * @date 2018年2月8日 上午11:04:13
    * @throws
     */
    @RequestMapping(value="/resertPhoneCustomersByphone")
    @ResponseBody
    public Object resertPhoneCustomersByphone(String userName){
    	ServiceResult res = userService.resertPhoneCustomersByphoneService(userName);
    	return res;
    }
    
    /**
     * 重置密码123456
    * @Description: 重置密码123456
    * @param userName
    * @return
    *
    * @author wp
    * @date 2018年2月8日 上午11:04:28
    * @throws
     */
    @RequestMapping(value="/resertPwdCustomersByphone")
    @ResponseBody
    public Object resertPwdCustomersByphone(String userName){
    	ServiceResult res = userService.resertPwdCustomersByphoneService(userName);
    	return res;
    }
    
}