package kr.or.connect.healthproject.login.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import static kr.or.connect.healthproject.login.dao.UserRoleDaoSqls.*;
import kr.or.connect.healthproject.login.dto.UserRole;

@Repository
public class UserRoleDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<UserRole> rowMapper=BeanPropertyRowMapper.newInstance(UserRole.class);
	private SimpleJdbcInsert insertAction;
	public UserRoleDao(DataSource dataSource) {
		this.jdbc=new NamedParameterJdbcTemplate(dataSource);
		this.insertAction =new SimpleJdbcInsert(dataSource)
								.withTableName("user_role")
								.usingGeneratedKeyColumns("id");
	}
	
	public List<UserRole> getUserRole(String userLoginId) {
		Map<String, Object>map=new HashMap<>();
		map.put("userLoginId", userLoginId);
		
		return jdbc.query(GET_USER_ROLE, map, rowMapper);
	}
	
	public Long addUserRole(UserRole userRole) {
		SqlParameterSource parmas=new BeanPropertySqlParameterSource(userRole);
		return insertAction.executeAndReturnKey(parmas).longValue();
	}
}
