package kr.or.connect.healthproject.dao;

import java.util.List;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.dto.Category;

@Repository
public class CategoryDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	
	public CategoryDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction= new SimpleJdbcInsert(dataSource)
								.withTableName("category")
								.usingGeneratedKeyColumns("id");
	}
	
	public Long addCategory(Category category) {
		SqlParameterSource params= new BeanPropertySqlParameterSource(category);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	/*
	 * 카테고리 가져오기
	 */
	public List<Category> selectCategory()throws Exception {
		return sessionTemplate.selectList("kr.or.connect.healthproject.dao.selectCategory");
	}

}
