package kr.or.connect.healthproject.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.dto.ProductPrice;
import static kr.or.connect.healthproject.dao.ProductPriceDaoSqls.*;

import java.util.HashMap;
import java.util.Map;
@Repository
public class ProductPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ProductPrice>rowMapper=BeanPropertyRowMapper.newInstance(ProductPrice.class);
	public ProductPriceDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("product_price")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long addProductPrice(ProductPrice productPrice) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(productPrice);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public int updateProductPrice(Long productId,double discountRate) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		map.put("discountRate", discountRate);
		
		return jdbc.update(UPDATE_PRODUCT_PRICE, map);
	}
	
	public ProductPrice getPrice(Long productId) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		
		return jdbc.queryForObject(GET_PRICE, map, rowMapper);
	}
}
