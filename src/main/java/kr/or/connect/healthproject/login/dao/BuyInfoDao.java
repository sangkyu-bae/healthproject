package kr.or.connect.healthproject.login.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.login.dto.BuyCount;
import kr.or.connect.healthproject.login.dto.BuyInfo;
import kr.or.connect.healthproject.login.dto.MyInfoComment;
import kr.or.connect.healthproject.login.dto.MyInfoProductQuestion;

import static kr.or.connect.healthproject.login.dao.BuyInfoDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
@Repository
public class BuyInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<BuyInfo>rowMapper=BeanPropertyRowMapper.newInstance(BuyInfo.class);
	private RowMapper<BuyCount>rowMapper2=BeanPropertyRowMapper.newInstance(BuyCount.class);
	private RowMapper<MyInfoProductQuestion>rowMapper3=BeanPropertyRowMapper.newInstance(MyInfoProductQuestion.class);
	private RowMapper<MyInfoComment>rowMapper4=BeanPropertyRowMapper.newInstance(MyInfoComment.class);
	public BuyInfoDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<BuyInfo>getBuyInfo(Long userId){
		Map<String, Object>map=new HashMap<>();
		map.put("userId", userId);
		return jdbc.query(GET_BUY_INFO, map, rowMapper);
	}
	
	public List<BuyCount>getButCount(Long userId){
		Map<String, Object>map=new HashMap<>();
		map.put("userId", userId);
		return jdbc.query(GET_COUNT, map,rowMapper2);
	}
	
	public List<MyInfoProductQuestion>getMyQuestion(Long userId){
		Map<String, Object>map=new HashMap<>();
		map.put("userId", userId);
		return jdbc.query(GET_MY_PR_QUESTION, map,rowMapper3);
	}
	public List<MyInfoComment>getMyComment(Long userId){
		Map<String, Object>map=new HashMap<>();
		map.put("userId", userId);
		return jdbc.query(GET_MY_COMMENT, map,rowMapper4);
	}
}
