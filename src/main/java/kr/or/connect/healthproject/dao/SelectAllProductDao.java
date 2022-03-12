package kr.or.connect.healthproject.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.dto.SelectAllProduct;

import static kr.or.connect.healthproject.dao.SelectAllProductDaoSqls.*;import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
@Repository
public class SelectAllProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<SelectAllProduct>rowMapper=BeanPropertyRowMapper.newInstance(SelectAllProduct.class);
	
	public SelectAllProductDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<SelectAllProduct>getProductInfo(Long start){
		Map<String,Object>map=new HashMap<>();
		map.put("start", start);
		return jdbc.query(ALL_PRODUCT,map,rowMapper);
	}
	
	public int totalProductCount() {
		return jdbc.queryForObject(TOTAL_COUNT, Collections.emptyMap(), int.class);
	}
}
