package kr.or.connect.healthproject.dao;

public class ProductPriceDaoSqls {
	public static final String UPDATE_PRODUCT_PRICE="update product_price\r\n"
			+ "set product_type_name='B',discount_rate=:discountRate\r\n"
			+ "where product_id=:productId";
	public static final String GET_PRICE="select  *from product_price\r\n"
			+ "where product_id=:productId";
}
