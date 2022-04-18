package kr.or.connect.healthproject.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QaAnwserDao {
	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	/*
	 * @parma Map
	 * qa 답변 가져오기
	 */
	public Map<String, Object> selectQaAnwser(Map<String, Object>params)throws Exception{
		return sessionTemplate.selectOne("kr.or.connect.healthproject.member.dao.QaAnwserDao.selectQaAnwser",params);
	}
	
	
}
