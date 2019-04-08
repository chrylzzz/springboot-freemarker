package com.lnsoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@MapperScan(basePackages = {"com.lnsoft.mapper"})
@SpringBootApplication
public class SpringbootFreemarkerApplication {

	public static void main(String[] args){
		SpringApplication.run(SpringbootFreemarkerApplication.class, args);
	}
}
