package kr.or.connect.healthproject.login.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.login.dto.ReservationUserComment;

import static kr.or.connect.healthproject.login.dao.ReservationUserCommentDaoSqls.*;
@Repository
public class ReservationUserCommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	
	public ReservationUserCommentDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
							.withTableName("reservation_user_comment")
							.usingGeneratedKeyColumns("id");
	}
	public Long insertReservationUserComment(ReservationUserComment comment) {
		SqlParameterSource parmas=new BeanPropertySqlParameterSource(comment);
		return insertAction.executeAndReturnKey(parmas).longValue();
	}
	/*
	public int addReservationUserComment(ReservationUserComment comment) {
		Map<String, Object>map=new HashMap<>();
		map.put("productId", comment.getProductId());
		map.put("reservationInfoId", comment.getReservationInfoId());
		map.put("userId", comment.getUserId());
		map.put("score", comment.getScore());
		map.put("comment", comment.getComment());
		
		return jdbc.update(INSERT_RESERVATION_USER_COMMENT, map);
	}
	*/
	
	/*
	 * @paramter ReservationUserComment
	 * 리뷰 등록
	 */
	public int addReservationUserComment(ReservationUserComment vo)  throws Exception{
		System.out.println("vo?"+vo);
		
		return sessionTemplate.insert("kr.or.connect.healthproject.login.dao.ReservationUserCommentDao.addReservationUserComment",vo);
	}
	public Long getId() {
		return jdbc.queryForObject(GET_RESERVATION_USER_COMMENT_ID, Collections.emptyMap(), long.class);
	}
}
