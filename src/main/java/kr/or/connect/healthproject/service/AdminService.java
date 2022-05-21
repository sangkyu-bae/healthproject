package kr.or.connect.healthproject.service;

import java.util.List;
import java.util.Map;

import kr.or.connect.healthproject.admin.dto.ProductGetSize;
import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;
import kr.or.connect.healthproject.admin.dto.ProductSize;
import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.dto.Promotion;

public interface AdminService {
	public Long insertproductQuestionAnwser(ProductQuestionAnwser anwser);
	public Long addProductSize(ProductSize productSize);
	
	public ProductGetSize getProductAndSize(Long productId);
	
	public List<Map<String, Object>>selectPayMentList()throws Exception;
	
	/*
	 * 관리자 결제완료된 고객들의 주문 조회
	 * @retrun List
	 */
	public List<Map<String,Object>>selectComplteOrderList(Map<String, Object>map)throws Exception;
	/*
	 * 카테고리 가져오기
	 */
	public List<Category> selectCategory()throws Exception;
	/*
	 * 카테고리별 상품 가져오기
	 * @ params Category 
	 */
	public List<Map<String, Object>>selectCategoryProduct(Category vo)throws Exception;
	/*
	 * 행사상품 등록하기
	 * @params 
	 */
	public Long insertPromotion(List<Promotion> list,double discountRate)throws Exception;
	
}
