package kr.or.connect.healthproject.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.member.dto.MemberReservationInfo;

@Repository
public class MemberReservationInfoDao {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	/*
	 * @params MemberReservationInfo
	 * 예약정보 시간, 예약 수량 변경하는 메소드
	 */	
	public int updateReservationInfo(MemberReservationInfo info) throws Exception{
		//return sessionTemplate.update("kr.or.connect.healthproject.dao.SelectPromotionsDao.updateReservationInfo",info);
		return sessionTemplate.update("kr.or.connect.healthproject.member.dao.MemberReservationInfoDao.updateReservationInfo",info);
	}
	/*
	 * @params Map
	 * 기간에 따른 주문 내역 조회
	 */
	public List<Map<String, Object>> selectMemeberOrder(Map<String, Object> params) throws Exception{
		return sessionTemplate.selectList("kr.or.connect.healthproject.member.dao.MemberReservationInfoDao.selectMemeberOrder",params);
	}
}
