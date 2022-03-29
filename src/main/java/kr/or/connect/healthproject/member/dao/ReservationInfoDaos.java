package kr.or.connect.healthproject.member.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.member.dto.ReservationInfo;

@Repository
public class ReservationInfoDaos {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	/*
	 * 결제완료시 예약정보 변경
	 * @params ReservtionInfo
	 */
	public int updateReservationInfo(ReservationInfo vo) {
		return sessionTemplate.update("kr.or.connect.healthproject.member.dao.ReservationInfoDaos.updateReservationInfo",vo);
	}
}
