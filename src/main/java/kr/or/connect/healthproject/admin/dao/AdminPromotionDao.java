package kr.or.connect.healthproject.admin.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminPromotionDao {
	@Autowired
	private SqlSessionTemplate sessionTemplate;
	
	/*
	 * 상품문의 검색
	 */
	public List<Map<String, Object>>selectQuestion(Map<String, Object>map){
		return sessionTemplate.selectList("kr.or.connect.healthproject.admin.dao.AdminPromotionDao.selectQuestion", map);
	}
}
