package kr.or.connect.healthproject.admin.dao;

public class ProductGetSizeDaoSqls {
		public static final String GET_DETAIL_PR="select b.id,b.description,b.content,d.price,d.discount_rate,e.save_file_name\r\n"
			+ "from category a left outer join product b on a.id=b.category_id\r\n"
			+ "left outer join product_image c on b.id=c.product_id\r\n"
			+ "left outer join file_info e on c.file_id=e.id\r\n"
			+ "left outer join product_price d on b.id =d.product_id\r\n"
			+ "where b.id=:productId";
}
