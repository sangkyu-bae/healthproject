package kr.or.connect.healthproject.login.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import static kr.or.connect.healthproject.login.dao.ProductQuestionAnwserDtDaoSqls.*;
import kr.or.connect.healthproject.login.dto.ProductQuestionAnwserDt;

@Repository
public class ProductQuestionAnwserDtDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductQuestionAnwserDt> rowMapper=BeanPropertyRowMapper.newInstance(ProductQuestionAnwserDt.class);
	
	public ProductQuestionAnwserDtDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ProductQuestionAnwserDt getAnwserDt(Long productQuestionId) {
		Map<String, Object>map=new HashMap<>();
		map.put("productQuestionId", productQuestionId);
//		ProductQuestionAnwserDt anwserDt=jdbc.queryForObject(GET_ANWSER_DT, map, rowMapper);
//		System.out.println(anwserDt);
//		return jdbc.queryForObject(GET_ANWSER_DT, map, rowMapper);
		try {
			return jdbc.queryForObject(GET_ANWSER_DT, map, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
