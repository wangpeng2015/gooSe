package com.spring.mybatics.serviceTest;

import javax.annotation.Resource;

import com.spring.mybatics.domain.goo.Mp4AppVersion;
import com.spring.mybatics.service.goo.Mp4Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.haier.result.ServiceResult;
import com.spring.mybatics.service.goo.GooService;

/**
 * 
 * 顾客
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)////表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {
		"classpath:/spring/spring-core.xml",
		"classpath:/spring/spring-mybatis.xml",
		"classpath:/spring/spring-aop.xml"
		})

@Transactional
public class TestGooMybatis {
	
	@Resource
	GooService gooService;

	@Resource
    Mp4Service mp4Service;

	@Test
	public  void testgetAppVersionAndUrl(){
        ServiceResult app=mp4Service.getAppVersionAndUrl();

        System.out.println(app.getResult().toString());
	}
	
}
