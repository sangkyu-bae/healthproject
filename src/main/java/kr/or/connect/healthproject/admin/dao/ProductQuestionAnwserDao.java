package kr.or.connect.healthproject.admin.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;

@Repository
public class ProductQuestionAnwserDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ProductQuestionAnwserDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("product_question_anwser")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long addproductQuestionAnwser(ProductQuestionAnwser anwser) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(anwser);
		return insertAction.executeAndReturnKey(params).longValue();
	}
}
