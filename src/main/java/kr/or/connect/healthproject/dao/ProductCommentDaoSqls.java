package kr.or.connect.healthproject.dao;

public class ProductCommentDaoSqls {
	public static final String GET_PRODUCT_COMMENT="select a.score,a.comment,b.email\r\n"
			+ "from reservation_user_comment a left outer join user b on a.user_id=b.id\r\n"
			+ "where a.product_id=:productId";
	public static final String GET_AVG="select avg(score)\r\n"
			+ "from reservation_user_comment\r\n"
			+ "where product_id=:productId";
	public static final String GET_COUNT="select count(*)\r\n"
			+ "from reservation_user_comment\r\n"
			+ "where product_id=:productId";
}
