package kr.or.connect.healthproject.dao;

public class SelectAllProductDaoSqls {
	public static final String ALL_PRODUCT="select\r\n"
			+ "a.id as product_id,a.description,b.price,b.discount_rate,d.save_file_name\r\n"
			+ "from product a left outer join product_price b on a.id=b.product_id\r\n"
			+ "left outer join product_image c on a.id = c.product_id\r\n"
			+ "left outer join file_info d on d.id=c.file_id\r\n"
			+ "limit :start ,4";
	public static final String TOTAL_COUNT="select count(*) as count from product";
}
