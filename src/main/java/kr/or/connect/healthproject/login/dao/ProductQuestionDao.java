package kr.or.connect.healthproject.login.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.login.dto.ProductQuestion;
import static kr.or.connect.healthproject.login.dao.ProductQuestionDaoSqls.*;
@Repository
public class ProductQuestionDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ProductQuestionDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("product_question")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long addProductQuestion(ProductQuestion productQuestion) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(productQuestion);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public int insertProductQuestion(ProductQuestion productQuestion) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productQuestion.getProductId());
		map.put("userId", productQuestion.getUserId());
		map.put("titleCategoryId", productQuestion.getTitleCategoryId());
		map.put("text",productQuestion.getText());
		map.put("questionAnswer",productQuestion.getQuestionAnswer());
		map.put("createDate", productQuestion.getCreateDate());
		map.put("modifyDate", productQuestion.getModifyDate());
		
		return jdbc.update(ADD_PRODUCT_QUESTION, map);
	}
	
	public int updateProductAnswer(Long id) {
		Map<String, Object>map=new HashMap<>();
		map.put("id", id);
		return jdbc.update(UPDATE_PRODUCT_ANSWER, map);
	}
}
