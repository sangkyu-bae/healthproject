package kr.or.connect.healthproject.dao;

public class SelectPromotionDaoSqls {
	public static final String SELECT_PROMOTION_ITEMS="select\r\n"
			+ "d.id,a.save_file_name,c.description\r\n"
			+ "from file_info a left outer join product_image b on a.id=b.file_id\r\n"
			+ "left outer join product c on b.product_id = c.id\r\n"
			+ "left outer join promotion d on d.product_id=c.id\r\n"
			+ "where d.id is not null";
}
