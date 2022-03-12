package kr.or.connect.healthproject.dao;

public class ProductInfoDaoSqls {
	public static final String GET_ALL_PRODUCT="select b.id,b.description,b.content,d.price,d.discount_rate,e.save_file_name\r\n"
			+ "from category a left outer join product b on a.id=b.category_id\r\n"
			+ "left outer join product_image c on b.id=c.product_id\r\n"
			+ "left outer join file_info e on c.file_id=e.id\r\n"
			+ "left outer join product_price d on b.id =d.product_id\r\n"
			+ "limit :start,6";
	
	public static final String GET_CATEGORY_ALL_PRODUCT="select b.id, b.description,b.content,d.price,d.discount_rate,e.save_file_name\r\n"
			+ "from category a left outer join product b on a.id=b.category_id\r\n"
			+ "left outer join product_image c on b.id=c.product_id\r\n"
			+ "left outer join file_info e on c.file_id=e.id\r\n"
			+ "left outer join product_price d on b.id =d.product_id\r\n"
			+ "where a.id=:id\r\n"
			+ "limit :start,6";
	
	public static final String GET_COUNT="select count(*)as totalcount\r\n"
			+ "from category a left outer join product b on a.id=b.category_id\r\n"
			+ "where a.id=:id\r\n"
			+ "group by a.id";
	public static final String GET_DETAIL_PR="select b.id,b.description,b.content,d.price,d.discount_rate,e.save_file_name\r\n"
			+ "from category a left outer join product b on a.id=b.category_id\r\n"
			+ "left outer join product_image c on b.id=c.product_id\r\n"
			+ "left outer join file_info e on c.file_id=e.id\r\n"
			+ "left outer join product_price d on b.id =d.product_id\r\n"
			+ "where b.id=:id";
	public static final String GET_ID_LIST="select id from product order by id asc";
}
