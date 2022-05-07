package kr.or.connect.healthproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.healthproject.dao","kr.or.connect.healthproject.service.impl",
		"kr.or.connect.healthproject.login.dao","kr.or.connect.healthproject.member.dao","kr.or.connect.healthproject.service.security",
		"kr.or.connect.healthproject.admin.dao","kr.or.connect.healthproject.util.dao"})
@Import({DBConfig.class
	,MybatisConfig.class})
public class ApplicationConfig {

}
