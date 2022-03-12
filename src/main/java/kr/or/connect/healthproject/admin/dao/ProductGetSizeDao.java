package kr.or.connect.healthproject.admin.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.admin.dto.ProductGetSize;
import static kr.or.connect.healthproject.admin.dao.ProductGetSizeDaoSqls.*;

@Repository
public class ProductGetSizeDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductGetSize>rowMapper=BeanPropertyRowMapper.newInstance(ProductGetSize.class);
	
	public ProductGetSizeDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ProductGetSize getproductAndSize(Long productId) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		try {
			return jdbc.queryForObject(GET_DETAIL_PR, map, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
