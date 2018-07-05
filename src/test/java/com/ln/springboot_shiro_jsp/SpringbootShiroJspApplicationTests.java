package com.ln.springboot_shiro_jsp;

import com.interlink.mng.domain.UserInfo;
import com.ln.springboot_shiro_jsp.controller.PasswordGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShiroJspApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("start...");
	}

	@Test
	public void testGenPsw(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("admin");
		userInfo.setName("admin");
		userInfo.setPassword("123456");

		PasswordGenerator generator = new PasswordGenerator();
		generator.encryptPassword(userInfo);

		System.out.println(userInfo);

	}

}
