package com.blacklist.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
@MapperScan(basePackages = "com.blacklist.demo.mapper")
public class DemoBlacklistApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DemoBlacklistApplication.class, args);

	}



}
