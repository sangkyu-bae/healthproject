package kr.or.connect.healthproject.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.healthproject.service.UtilService;
import kr.or.connect.healthproject.util.dao.UtilDao;
import kr.or.connect.healthproject.util.dto.PagingVO;
@Service
public class UtilServiceImpl implements UtilService{
	@Autowired
	UtilDao utilDao;
	
	@Override
	@Transactional
	public int countBoard(int categoryId) {
		return utilDao.countBorad(categoryId);
	}

	@Override
	@Transactional
	public List<Map<String, Object>> selectBoard(PagingVO vo,int categoryId) {
		return utilDao.selectBoard(vo,categoryId);
	}
	
}
