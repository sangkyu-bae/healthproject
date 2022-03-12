package kr.or.connect.healthproject.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.dto.Product;
import static kr.or.connect.healthproject.dao.ProductDaoSqls.*;
@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ProductDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
								.withTableName("product")
								.usingGeneratedKeyColumns("id");
	}
	
	public Long addProduct(Product product) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(product);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public int updateProductModify(Long productId,Date modifyDate) {
		Map<String, Object>map=new HashMap<>();
		map.put("modifyDate", modifyDate);
		map.put("productId", productId);
		
		return jdbc.update(UPDATE_PRODUCT_MODIFY, map);
	}
}
