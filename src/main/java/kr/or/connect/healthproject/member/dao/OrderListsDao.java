package kr.or.connect.healthproject.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.member.dto.OrderList;

@Repository
public class OrderListsDao {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	/*
	 * @parmas OrderList
	 * 로그인된 사용자가 결제완료시 결제 테이블 등록
	 */
	
	public void insertOrderList(OrderList vo) throws Exception {
		sessionTemplate.insert("kr.or.connect.healthproject.member.dao.OrderListDao.insertOrderList", vo);
	}
}