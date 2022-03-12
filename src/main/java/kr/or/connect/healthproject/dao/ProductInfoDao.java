package kr.or.connect.healthproject.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import static kr.or.connect.healthproject.dao.ProductInfoDaoSqls.*;
import static kr.or.connect.healthproject.login.dao.ProductQuestionAnwserDtDaoSqls.GET_ANWSER_DT;

import kr.or.connect.healthproject.dto.ProductInfo;
import kr.or.connect.healthproject.dto.ProductList;

@Repository
public class ProductInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductInfo>rowMapper=BeanPropertyRowMapper.newInstance(ProductInfo.class);
	private RowMapper<ProductList>rowMapper2=BeanPropertyRowMapper.newInstance(ProductList.class);
	public ProductInfoDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductInfo> getAllProduct(Integer start){
		Map<String, Object>map= new HashMap<>();
		map.put("start", start);
		return jdbc.query(GET_ALL_PRODUCT,map,rowMapper);
	}
	
	public List<ProductInfo>getCategoryAllProduct(Integer id,Integer start){
		Map<String, Object>map=new HashMap<>();
		map.put("id", id);
		map.put("start", start);
		
		return jdbc.query(GET_CATEGORY_ALL_PRODUCT, map, rowMapper);
	}
	
	public int getCount(Integer id) {
		Map<String, Object>map=new HashMap<>();
		map.put("id", id);
		
		return jdbc.queryForObject(GET_COUNT, map, int.class);
	}
	
	public ProductInfo getDetailPr(Long id) {
		Map<String, Object>map =new HashMap<>();
		map.put("id", id);
		try {
			return jdbc.queryForObject(GET_DETAIL_PR, map, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		//return jdbc.queryForObject(GET_DETAIL_PR, map, rowMapper);
	}
	public List<ProductList> getListId(){
		return jdbc.query(GET_ID_LIST,rowMapper2);
	}
}
