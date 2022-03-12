package kr.or.connect.healthproject.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.admin.dto.ProductSize;
import static kr.or.connect.healthproject.admin.dao.ProductSizeDaoSqls.*;
@Repository
public class ProductSizeDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ProductSize>rowMapper=BeanPropertyRowMapper.newInstance(ProductSize.class);
	public ProductSizeDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("product_size")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long addProductSize(ProductSize productSize) {
		SqlParameterSource paramse=new BeanPropertySqlParameterSource(productSize);
		return insertAction.executeAndReturnKey(paramse).longValue();
	}
	public List<ProductSize>getSize(Long productId){
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		
		return jdbc.query(GET_PR_SIZE, map,rowMapper);
	}
}
