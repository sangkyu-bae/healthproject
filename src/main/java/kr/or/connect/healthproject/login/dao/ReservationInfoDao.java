package kr.or.connect.healthproject.login.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.login.dto.ReservationInfo;
import static kr.or.connect.healthproject.login.dao.ReservationInfoDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("reservation_info")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long insertReservationInfo(ReservationInfo info) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(info);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	public int addReservation(ReservationInfo info) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", info.getProductId());
		map.put("userId", info.getUserId());
		map.put("cancleFlag",info.getCancleFlag());
		map.put("productSizeId", info.getProductSizeId());
		
		return jdbc.update(ADD_RESERVATION_INFO, map);
	}
	
	public Long getReservationInfoId() {
		return jdbc.queryForObject(GET_RESERVATION_INFO_ID,  Collections.emptyMap(), long.class);
	}
}
