package kr.or.connect.healthproject.login.dao;

public class UserDaoSqls {
	public final static String SELECT_ALL_BY_USER_LOGIN_ID="select *from user where email=:userLoginId";
	
	public static final String INSERT_MEMBER =
			"INSERT INTO user(name, password, email,phone, create_date, modify_date,address) "
			+ "VALUES (:name, :password,:email,:phone, :createDate, :modifyDate,:address)";
}
