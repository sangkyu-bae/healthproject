package kr.or.connect.healthproject.login.dao;

public class ReservationUserCommentDaoSqls {
	public static final String INSERT_RESERVATION_USER_COMMENT="insert into reservation_user_comment(product_id,reservation_info_id,user_id,score,comment,create_date,modify_date)\r\n"
			+ "values(:productId,:reservationInfoId,:userId,:score,:comment,now(),now())";
	public static final String GET_RESERVATION_USER_COMMENT_ID="select max(id) from reservation_user_comment";
}
