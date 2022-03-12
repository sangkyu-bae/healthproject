package kr.or.connect.healthproject.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.dto.ProductImage;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ProductImageDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
								.withTableName("product_image")
								.usingGeneratedKeyColumns("id");
	}
	
	public Long addproductImage(ProductImage productimage) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(productimage);
		return insertAction.executeAndReturnKey(params).longValue();
	}
}
