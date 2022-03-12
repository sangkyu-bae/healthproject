package kr.or.connect.healthproject.login.dao;

public class ProductQuestionDaoSqls {
	public static final String ADD_PRODUCT_QUESTION="insert into product_question(product_id,user_id,title_category_id,text,question_answer,create_date,modify_date)\r\n"
			+ "values(:productId,:userId,:titleCategoryId,:text,:questionAnswer,:createDate,:modifyDate)";
	
	public static final String UPDATE_PRODUCT_ANSWER="update product_question set question_answer=1 where id=:id";
}
