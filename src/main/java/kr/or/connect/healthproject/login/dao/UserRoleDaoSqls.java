package kr.or.connect.healthproject.login.dao;

public class UserRoleDaoSqls {
	public static final String GET_USER_ROLE="select a.id,a.user_id,a.role_name from user_role a left outer join user b on a.user_id=b.id\r\n"
			+ "where b.email =:userLoginId";
}
