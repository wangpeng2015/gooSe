package com.spring.mybatics.serviceTest;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.haier.result.ServiceResult;
import com.spring.mybatics.domain.Hair_employee;
import com.spring.mybatics.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)////表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {
		"classpath:/spring/spring-core.xml",
		"classpath:/spring/spring-mybatis.xml",
		"classpath:/spring/spring-aop.xml"
		})

@Transactional
public class TestEmpMyBatis {
	private static Logger logger = Logger.getLogger(TestEmpMyBatis.class);
	
	@Resource
	private IUserService userService;

	/*@Test
//	@Rollback(false) //不回滚
//	@Rollback(true) //回滚
	public void doLogin() {
		Hair_employee user=new Hair_employee();
		user.setUserName("wangpeng");
		user.setPassWord("123");
		logger.info(JSON.toJSONString(user));
		//登录查询
		ServiceResult res=userService.findUserService(user);
		System.out.println(res.toString());
	}*/
	
	/**
	 * 
	 * 测试保存员工
	 */
	/*@Test
	@Rollback(false) //不回滚
	public void doSave(){
		Hair_employee user=new Hair_employee();
		user.setUserCode("04");
		user.setUserName("李四");
		user.setPassWord("123");
		user.setRoles("1");//店长
		user.setEmail("18300247760@163.com");
		user.setPhoneNumber("18300247762");
		user.setStoreCode("001");//必须
		user.setUpdateTime(new Date());
//		user.setStoreCode("test");
		ServiceResult res=userService.saveUser(user);
		System.out.println(res.toString());
	}*/
	
	/**
	 * 测试删除
	 */
	@Test
	@Rollback(false) //不回滚
	public void doDelete(){
		Hair_employee user=new Hair_employee();
		user.setId(7);
		ServiceResult id=userService.deleteUserById(user);
		System.out.println(id);
	}
	
	/**
	 * 测试根据手机进行店员店长信息删除
	 */
	@Test
	@Rollback(false) //不回滚
	public void deleteUserByPhone(){
		Hair_employee user=new Hair_employee();
		user.setPhoneNumber("18300247765");
		ServiceResult res=userService.deleteUserByPhoneNService(user);
		System.out.println(res.toString());
	}
	
	/**
	 * 根据电话或者姓名查询
	 */
	@Test
	public void findUserByphoneOrName(){
		Hair_employee user=new Hair_employee();
//		user.setUserName("test1");
		user.setPhoneNumber("18300247760");
		ServiceResult list=userService.searchUserByphoneOrNameService(user);
		
		System.out.println(list.toString());
	}
	
	/**
	 * 根据id进行更新
	 */
	/*@Test
	@Rollback(false) //不回滚
	public void updateUserByid(){
		Hair_employee user=new Hair_employee();
		user.setId(9);
		user.setPhoneNumber("12345678912");
		user.setUpdateTime(new Date());
		ServiceResult id=userService.updateUserByIdService(user);
		System.out.println(id.toString());
	}*/
}
