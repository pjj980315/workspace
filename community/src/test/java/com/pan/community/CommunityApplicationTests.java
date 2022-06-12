package com.pan.community;

import com.pan.community.dao.AlphaDao;
import com.pan.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)//删除spring-boot-starter-test 中的 <scope>test</scope>-->
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//以它为配置类
class CommunityApplicationTests implements ApplicationContextAware {//Spring 检测到调用此接口
																	//会利用set方法把此类传进去

	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext= applicationContext;
	}
	@Test
	public void testApplication(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		System.out.println(alphaDao.select());

		alphaDao = applicationContext.getBean("AlphaHiber",AlphaDao.class);
		System.out.println(alphaDao.select());
	}
	@Test
	public void TestBeanMannagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void TestBeanConfig(){
		SimpleDateFormat simpleDateFormat =
				applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}
	@Autowired
	@Qualifier("AlphaHiber")
	private AlphaDao alphaDao;
	@Test
	public void testDI(){
		System.out.println(alphaDao.select());
	}
//	@Autowired
//	@Autowired+@Qualifier
}
