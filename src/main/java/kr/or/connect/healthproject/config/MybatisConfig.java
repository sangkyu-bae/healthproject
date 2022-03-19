package kr.or.connect.healthproject.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@ComponentScan(basePackages = {"kr.or.connect.healthproject.dao"})
public class MybatisConfig {
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	ResourceLoader resourceLoader;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(
				new PathMatchingResourcePatternResolver()
					.getResource("classpath:mybaits/mybatis-config.xml"));
		sqlSessionFactoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver()
				.getResources("classpath:mybaits/mappers/*.xml"));
		
		return sqlSessionFactoryBean.getObject();		
	}
	 
	 @Bean
	    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
	        return new SqlSessionTemplate(sqlSessionFactory);
	    }

}
