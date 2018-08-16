package com.spring.mybatics.serviceTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mybatics.dao.IStoresDao;
import com.spring.mybatics.domain.Hair_stores;


@RunWith(SpringJUnit4ClassRunner.class)////表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {
		"classpath:/spring/spring-core.xml",
		"classpath:/spring/spring-mybatis.xml",
		"classpath:/spring/spring-aop.xml"
		})

@Transactional
public class TestStores {

	
	@Resource
	IStoresDao iStoresDao;
	
	@Test
	public void testFindAllStores(){
		List<Hair_stores> listStores=iStoresDao.finaAllStores();
		for (Hair_stores hair_stores : listStores) {
			System.out.println(hair_stores.toString());
		}
	}
}
