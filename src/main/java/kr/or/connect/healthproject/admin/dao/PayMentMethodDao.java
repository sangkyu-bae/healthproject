package kr.or.connect.healthproject.admin.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PayMentMethodDao {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	/*
	 * 결제 방법 검색
	 * @throws Excption
	 */
	public List<Map<String, Object>> selectPayMentList() throws Exception{
		return sessionTemplate.selectList("kr.or.connect.healthproject.admin.dao.PayMentMethodDao.selectPayMentList");
	}
}
