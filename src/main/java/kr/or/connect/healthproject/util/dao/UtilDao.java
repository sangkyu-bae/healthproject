package kr.or.connect.healthproject.util.dao;

import java.util.HashMap;
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
	
	public int countBorad(int categoryId) {
		Map<String, Object>map=new HashMap<>();
		map.put("id", categoryId);
		return sessionTemplate.selectOne("kr.or.connect.healthproject.util.dao.countBorad",map);
	}
	
	public List<Map<String, Object>> selectBoard(PagingVO vo,int categoryId){
		Map<String, Object>map=new HashMap<>();
		
		map.put("id", categoryId);
		map.put("start", vo.getStart());
		map.put("end", vo.getEnd());
		
		return sessionTemplate.selectList("kr.or.connect.healthproject.util.dao.selectBoard",map);
	}
}
