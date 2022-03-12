package kr.or.connect.healthproject.login.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import static kr.or.connect.healthproject.login.dao.MyCartDaoSqls.*;
import kr.or.connect.healthproject.login.dto.MyCart;

@Repository
public class MyCartDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<MyCart>rowMapper=BeanPropertyRowMapper.newInstance(MyCart.class);
	
	public MyCartDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<MyCart>getCartProduct(Long id){
		Map<String, Object>map=new HashMap<>();
		map.put("id",id);
		
		return jdbc.query(GET_CART_PRODUCT, map, rowMapper);
	}
	
	public int deleteReservation(Long id) {
		Map<String, ?>map=Collections.singletonMap("id", id);
		return jdbc.update(UPDATE_RESERVATION_INFO, map);
	}
	
	public int getMaxReservationId(Long userId) {
		Map<String, ?>map=Collections.singletonMap("userId", userId);
		return jdbc.queryForObject(GET_MAX_RESERVATION_INFO, map, int.class);
	}
	
	public MyCart getMaxProduct(int reservationId) {
		Map<String, ?>map=Collections.singletonMap("reservationId", reservationId);
		return jdbc.queryForObject(GET_CART_MAX_PRODUCT, map, rowMapper);
	}
}
