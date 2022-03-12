package kr.or.connect.healthproject.login.dao;

public class ReservationInfoDaoSqls {
	public static final String ADD_RESERVATION_INFO="insert into reservation_info(product_id,user_id,reservation_date,cancle_flag,create_date,modify_date,product_size_id)\r\n"
			+ "values(:productId,:userId,now(),:cancleFlag,now(),now(),:productSizeId)";
	
	public static final String GET_RESERVATION_INFO_ID="select max(id) from reservation_info";
}
