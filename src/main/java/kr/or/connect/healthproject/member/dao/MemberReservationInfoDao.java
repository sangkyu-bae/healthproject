package kr.or.connect.healthproject.member.dao;

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
}
