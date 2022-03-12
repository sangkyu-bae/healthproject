package kr.or.connect.healthproject.login.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.login.dto.OrderList;
import static kr.or.connect.healthproject.login.dao.OrderListDaoSqls.*;

import java.util.Collections;
@Repository
public class OrderListDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public OrderListDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("order_list")
							.usingGeneratedKeyColumns("id");
	}
	
	public Long insertOrderList(OrderList orderList) {
		SqlParameterSource parmas=new BeanPropertySqlParameterSource(orderList);
		return insertAction.executeAndReturnKey(parmas).longValue();
	}
	
	public int updateReservationInfo(Long reservationId) {
		return jdbc.update(UPDATE_RESERVATION_INFO_CANCLE_FLAG, Collections.singletonMap("reservationId", reservationId));
	}
}
