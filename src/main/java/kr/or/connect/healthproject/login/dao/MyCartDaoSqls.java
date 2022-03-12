package kr.or.connect.healthproject.login.dao;

public class MyCartDaoSqls {
	public static final String GET_CART_PRODUCT="select \r\n"
			+ "a.id as reservation_info_id ,m.id as user_id,f.save_file_name,c.description,g.price,g.discount_rate,e.size,b.count\r\n"
			+ "from reservation_info a left outer join reservation_info_price b on a.id=b.reservation_info_id\r\n"
			+ "left outer join product c on a.product_id=c.id\r\n"
			+ "left outer join product_image d on c.id=d.product_id\r\n"
			+ "left outer join file_info f on d.file_id=f.id\r\n"
			+ "left outer join product_size e on a.product_size_id=e.id\r\n"
			+ "left outer join product_price g on c.id=g.product_id\r\n"
			+ "left outer join user m on m.id=a.user_id\r\n"
			+ "where m.id=:id and a.cancle_flag=0";
	
	public static final String UPDATE_RESERVATION_INFO="update reservation_info set cancle_flag=5 where id=:id";
	
	public static final String GET_MAX_RESERVATION_INFO="select max(id) from reservation_info where user_id=:userId and cancle_flag=0";
	
	public static final String GET_CART_MAX_PRODUCT="select \r\n"
			+ "a.id as reservation_info_id ,m.id as user_id,f.save_file_name,c.description,g.price,g.discount_rate,e.size,b.count\r\n"
			+ "from reservation_info a left outer join reservation_info_price b on a.id=b.reservation_info_id\r\n"
			+ "left outer join product c on a.product_id=c.id\r\n"
			+ "left outer join product_image d on c.id=d.product_id\r\n"
			+ "left outer join file_info f on d.file_id=f.id\r\n"
			+ "left outer join product_size e on a.product_size_id=e.id\r\n"
			+ "left outer join product_price g on c.id=g.product_id\r\n"
			+ "left outer join user m on m.id=a.user_id\r\n"
			+ "where m.id=16 and a.cancle_flag=0 and a.id=:reservationId";
}
