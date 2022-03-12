package kr.or.connect.healthproject.dao;

public class ProductDaoSqls {
	public static final String UPDATE_PRODUCT_MODIFY="update product\r\n"
			+ "set modify_date=:modifyDate\r\n"
			+ "where id=:productId";
}
