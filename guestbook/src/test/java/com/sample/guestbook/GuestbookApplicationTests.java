package com.sample.guestbook;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.guestbook.config.GuestbookSpringMvcDispatcherServletInitializer;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages="com.guestbook.demo")
@ContextConfiguration(classes = {GuestbookSpringMvcDispatcherServletInitializer.class})
@SpringBootTest
public class GuestbookApplicationTests {
	
	@Test
	public void contextLoads() {
	}
}
