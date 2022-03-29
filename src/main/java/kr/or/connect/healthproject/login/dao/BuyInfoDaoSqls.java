package kr.or.connect.healthproject.login.dao;

public class BuyInfoDaoSqls {
	public static final String GET_BUY_INFO="select \r\n"
			+ "b.description,a.cancle_flag,a.id as reservation_info_id,c.price,c.discount_rate,d.count,a.create_date\r\n"
			+ "from reservation_info a left outer join product b on a.product_id=b.id\r\n"
			+ "left outer join product_price c on b.id=c.product_id\r\n"
			+ "left outer join reservation_info_price d on a.id=d.reservation_info_id\r\n"
			+ "where a.user_id=:userId and a.cancle_flag between 1 and 5 order by a.cancle_flag\r\n"
			+ "";
	public static final String GET_COUNT="select cancle_flag,count(cancle_flag) as cnt from reservation_info\r\n"
			+ " where user_id=:userId and cancle_flag between 1 and 5\r\n"
			+ " group by cancle_flag";
	public static final String GET_MY_PR_QUESTION="select\r\n"
			+ "a.id as product_question_id,c.description, a.text,b.name,a.question_answer,a.create_date\r\n"
			+ "from product_question a left outer join title_category b on b.id=a.title_category_id\r\n"
			+ "left outer join product c on a.product_id=c.id\r\n"
			+ "where a.user_id=:userId";
	public static final String GET_MY_COMMENT="select \r\n"
			+ "a.id as reservation_user_comment,b.description,a.comment,a.score\r\n"
			+ "from reservation_user_comment a left outer join product b on a.product_id=b.id\r\n"
			+ "where a.user_id=:userId";
}
