package kr.or.connect.healthproject.util.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.connect.healthproject.util.dto.PagingVO;

@Repository
public class UtilDao {

	@Autowired
	SqlSessionTemplate sessionTemplate;
	
	public int countBorad() {
		return sessionTemplate.selectOne("kr.or.connect.healthproject.util.dao.countBorad");
	}
	
	public List<Map<String, Object>> selectBoard(PagingVO vo){
		return sessionTemplate.selectList("kr.or.connect.healthproject.util.dao.selectBoard",vo);
	}
}
