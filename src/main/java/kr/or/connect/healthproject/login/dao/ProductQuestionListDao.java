package kr.or.connect.healthproject.login.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.login.dto.ProductQuestionList;

import static kr.or.connect.healthproject.login.dao.ProductQuestionListDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
@Repository
public class ProductQuestionListDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductQuestionList>rowMapper=BeanPropertyRowMapper.newInstance(ProductQuestionList.class);
	
	public ProductQuestionListDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductQuestionList>getProductQuestion(Long productId){
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		
		return jdbc.query(GET_PRODUCT_QUESTION, map, rowMapper);
	}
}
