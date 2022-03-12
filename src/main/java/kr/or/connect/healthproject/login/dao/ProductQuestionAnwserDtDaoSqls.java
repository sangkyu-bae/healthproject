package kr.or.connect.healthproject.login.dao;

public class ProductQuestionAnwserDtDaoSqls {
	public static final String GET_ANWSER_DT="select id,text,create_date from product_question_anwser where product_question_id=:productQuestionId";
}
