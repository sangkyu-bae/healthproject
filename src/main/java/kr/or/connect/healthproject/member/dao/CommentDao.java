package kr.or.connect.healthproject.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao {
	@Autowired
	SqlSessionTemplate sessionTemplate;
	/*
	 *현재 로그인된 사용자의 리뷰 가져오기
	 *@params Map
	 *
	 **/
	public List<Map<String, Object>> selectComment(Map<String, Object>params)throws Exception {
		return sessionTemplate.selectList("kr.or.connect.healthproject.member.dao.CommentDao.selectComment",params);
	}
}
