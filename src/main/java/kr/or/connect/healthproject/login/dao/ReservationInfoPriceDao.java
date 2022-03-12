package kr.or.connect.healthproject.login.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.login.dto.ReservationInfoPrice;

@Repository
public class ReservationInfoPriceDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public ReservationInfoPriceDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("reservation_info_price")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long addReservationInfoPrice(ReservationInfoPrice infoPrice) {
		SqlParameterSource params=new BeanPropertySqlParameterSource(infoPrice);
		return insertAction.executeAndReturnKey(params).longValue();
		
	}
}
