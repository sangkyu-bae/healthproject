package kr.or.connect.healthproject.member.dao;

import java.util.List;
import java.util.Map;

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
		System.out.println(vo);
		sessionTemplate.insert("kr.or.connect.healthproject.member.dao.OrderListsDao.insertOrderList", vo);
	}
	
	/*
	 * 관리자 페이지에서 결제완료된 고객들의 모든 주문 조회
	 * @return List 
	 */
	
	public List<Map<String, Object>> selectComplteOrderList(Map<String, Object>map)throws Exception{
		return sessionTemplate.selectList("kr.or.connect.healthproject.member.dao.OrderListsDao.selectComplteOrderList",map);
	}
}