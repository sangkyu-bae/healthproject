package kr.or.connect.healthproject.login.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import static kr.or.connect.healthproject.login.dao.UserDaoSqls.*;
import kr.or.connect.healthproject.login.dto.User;

@Repository
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<User> rowMapper= BeanPropertyRowMapper.newInstance(User.class);
	private SimpleJdbcInsert insertAction;
	public UserDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction=new SimpleJdbcInsert(dataSource)
								.withTableName("user")
								.usingGeneratedKeyColumns("id");
	}
	
	public User getUserByLoginId(String userLoginId) {
		Map<String, Object> map =new HashMap<>();
		map.put("userLoginId", userLoginId);
		
		return jdbc.queryForObject(SELECT_ALL_BY_USER_LOGIN_ID, map, rowMapper);
		
	}
	
//	public Long addUser(User user) {
//		System.out.println("여기는 다오"+user.getEmail());
//		SqlParameterSource pamras=new BeanPropertySqlParameterSource(user);
//		return insertAction.executeAndReturnKey(pamras).longValue();
//	}
	
	public int addUser(User user) {
		Map<String, Object>map= new HashMap<>();
		map.put("name", user.getName());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("email", user.getEmail());
		map.put("createDate", user.getCreateDate());
		map.put("modifyDate",user.getModifyDate());
		map.put("address", user.getAddress());
		
		return jdbc.update(INSERT_MEMBER, map);
		
	}
}
