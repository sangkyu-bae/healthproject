package kr.or.connect.healthproject.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProQuestionDao {
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	/*
	 * 로그인된 사용자 상품문의 목록 가져오기
	 * @parasm Map
	 */
	public List<Map<String, Object>> selectProductQuestion(Map<String, Object>params)throws Exception{
		return sessionTemplate.selectList("kr.or.connect.healthproject.member.dao.ProQuestionDao.selectProductQuestion",params);
	}
}
	
