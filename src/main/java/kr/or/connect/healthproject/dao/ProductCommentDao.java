package kr.or.connect.healthproject.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import static kr.or.connect.healthproject.dao.ProductCommentDaoSqls.*;
import kr.or.connect.healthproject.dto.ProductComment;

@Repository
public class ProductCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductComment> rowMapper=BeanPropertyRowMapper.newInstance(ProductComment.class);
	public ProductCommentDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductComment>getProductComment(Long productId){
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		
		return jdbc.query(GET_PRODUCT_COMMENT, map, rowMapper);
		
	}
	
	public double getAvg(Long productId) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		
//		double avg=0;
//		double avgs=jdbc.queryForObject(GET_AVG, map, double.class);
//		System.out.println(avgs);
//		String avgStr=Double.toString(avgs);
//		if(avgStr!=null) {
//			return avgs;
//		}else {
//			return avg;
//		}
		double avg;
		String avgs=String.valueOf(jdbc.queryForObject(GET_AVG, map, double.class));

		if(avgs.equals("null")) {
			avg=0;
			return avg;
		}else {
			avg=Double.parseDouble(avgs);
			return avg;
		}
		//return jdbc.queryForObject(GET_AVG, map,double.class);
	}
	
	public int getCount(Long productId) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", productId);
		
		return jdbc.queryForObject(GET_COUNT, map, int.class);
	}
}
	
	
