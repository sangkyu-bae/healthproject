package kr.or.connect.healthproject.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.dto.TitleCategory;

@Repository
public class TitleCategoryDao {
	private SimpleJdbcInsert insertAction;
	
	public TitleCategoryDao(DataSource dataSource) {
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("title_category")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long addTitle(TitleCategory category) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(category);
		return insertAction.executeAndReturnKey(params).longValue();
	}
}
