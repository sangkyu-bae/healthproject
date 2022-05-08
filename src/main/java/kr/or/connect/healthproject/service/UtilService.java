package kr.or.connect.healthproject.service;

import java.util.List;
import java.util.Map;

import kr.or.connect.healthproject.util.dto.PagingVO;

public interface UtilService {
	// 게시물 총 갯수
	public int countBoard(int categoryId);

	// 페이징 처리 게시글 조회
	public List<Map<String, Object>> selectBoard(PagingVO vo,int categoryId);
}
