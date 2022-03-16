package kr.or.connect.healthproject.service;

import java.util.List;
import java.util.Map;

import kr.or.connect.healthproject.admin.dto.ProductGetSize;
import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;
import kr.or.connect.healthproject.admin.dto.ProductSize;

public interface AdminService {
	public Long insertproductQuestionAnwser(ProductQuestionAnwser anwser);
	public Long addProductSize(ProductSize productSize);
	
	public ProductGetSize getProductAndSize(Long productId);
	
	public List<Map<String, Object>>selectPayMentList()throws Exception;
}
