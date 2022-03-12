package kr.or.connect.healthproject.login.dao;

public class ProductQuestionListDaoSqls {
	public static final String GET_PRODUCT_QUESTION="select \r\n"
			+ "a.id,b.name,a.question_answer,a.text,c.email,a.create_date\r\n"
			+ "from product_question a left outer join title_category b on a.title_category_id=b.id\r\n"
			+ "left outer join user c on a.user_id=c.id\r\n"
			+ "where a.product_id=:productId";
}
