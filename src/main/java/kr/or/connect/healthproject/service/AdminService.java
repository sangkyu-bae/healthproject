package kr.or.connect.healthproject.service;

import kr.or.connect.healthproject.admin.dto.ProductGetSize;
import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;
import kr.or.connect.healthproject.admin.dto.ProductSize;

public interface AdminService {
	public Long insertproductQuestionAnwser(ProductQuestionAnwser anwser);
	public Long addProductSize(ProductSize productSize);
	
	public ProductGetSize getProductAndSize(Long productId);
}
