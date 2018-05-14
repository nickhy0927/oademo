package com.iss.user;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iss.platform.user.entity.User;
import com.iss.platform.user.service.UserService;
import com.orm.constant.SysConstant.DataStatus;
import com.orm.constant.SysConstant.Enable;
import com.orm.constant.SysConstant.Locked;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void getTest() {
		System.out.println("测试成功...");
		System.out.println(userService);
		User user = userService.get("76a37696fae84e409c332d47033e0612");
		System.out.println(user);
	}
	
	@Test
	public void insertTest() {
		System.out.println("插入操作...");
		User user = new User();
		user.setUpdateTime(new Date());
		user.setStatus(DataStatus.VALID);
		user.setEnable(Enable.YES);
		user.setEmail("h_y_12@163.com");
		user.setPassword("123456");
		user.setLoginName("zhangsan");
		user.setRealName("张三");
		user.setPosition("总经理");
		user.setLocked(Locked.NO);
		user.setLastLoginTime(new Date());
		userService.insert(user);
		System.out.println(user);
	}
}
