package kr.or.connect.healthproject.admin.dao;

public class ProductSizeDaoSqls {
	public static final String GET_PR_SIZE="select a.id,a.product_id ,a.size \r\n"
			+ "from product_size a left outer join product b on a.product_id=b.id\r\n"
			+ "where a.product_id=:productId";
}
