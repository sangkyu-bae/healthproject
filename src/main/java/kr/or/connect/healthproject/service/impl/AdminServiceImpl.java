package kr.or.connect.healthproject.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.healthproject.admin.dao.PayMentMethodDao;
import kr.or.connect.healthproject.admin.dao.ProductGetSizeDao;
import kr.or.connect.healthproject.admin.dao.ProductQuestionAnwserDao;
import kr.or.connect.healthproject.admin.dao.ProductSizeDao;
import kr.or.connect.healthproject.admin.dto.ProductGetSize;
import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;
import kr.or.connect.healthproject.admin.dto.ProductSize;
import kr.or.connect.healthproject.dao.CategoryDao;
import kr.or.connect.healthproject.dao.ProductDao;
import kr.or.connect.healthproject.dao.ProductPriceDao;
import kr.or.connect.healthproject.dao.PromotionDao;
import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.dto.Promotion;
import kr.or.connect.healthproject.login.dao.ProductQuestionDao;
import kr.or.connect.healthproject.member.dao.OrderListsDao;
import kr.or.connect.healthproject.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	ProductQuestionAnwserDao productQuestionAnwserDao;
	@Autowired
	ProductQuestionDao productQuestionDao;
	@Autowired
	ProductSizeDao productSizeDao;
	@Autowired
	ProductGetSizeDao productGetSizeDao;
	@Autowired
	PayMentMethodDao payMentMethodDao;
	@Autowired
	OrderListsDao orderListsDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	PromotionDao promotionDao;
	@Autowired
	ProductPriceDao productPriceDao;
	
	@Override
	@Transactional(readOnly = false)
	public Long insertproductQuestionAnwser(ProductQuestionAnwser anwser) {
		Long id=productQuestionAnwserDao.addproductQuestionAnwser(anwser);
		productQuestionDao.updateProductAnswer(anwser.getProductQuestionId());
		return id;
	}
	@Override
	@Transactional(readOnly = false)
	public Long addProductSize(ProductSize productSize) {
		Long id =productSizeDao.addProductSize(productSize);
		return id;
	}
	@Override
	@Transactional()
	public ProductGetSize getProductAndSize(Long productId) {
		ProductGetSize getSize=productGetSizeDao.getproductAndSize(productId);
		if(getSize!=null) {
			List<ProductSize>list=productSizeDao.getSize(productId);
			getSize.setProductSize(list);
		}
		return getSize;
	}
	/*
	 * 결제 방법 검색
	 * @throws Excption
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> selectPayMentList() throws Exception {
		return payMentMethodDao.selectPayMentList();
	}
	
	/*
	 * 고객들 주문 조회
	 */
	@Override
	@Transactional
	public List<Map<String, Object>> selectComplteOrderList(Map<String, Object>map) throws Exception {
	
		return orderListsDao.selectComplteOrderList(map);
	}
	/*
	 * 카테고리 검색
	 */
	@Override
	public List<Category> selectCategory() throws Exception {
		return categoryDao.selectCategory();
	}
	/*
	 * 카테고리별 상품검색
	 * @params Category
	 */
	@Override
	public List<Map<String, Object>> selectCategoryProduct(Category vo) throws Exception {
		
		return categoryDao.selectCategoryProduct(vo);
	}
	/*
	 * 행사상품 등록하기
	 * @params List<Promotion>, double discount
	 */
	@Override
	@Transactional(readOnly = false)
	public Long insertPromotion(List<Promotion> list, double discountRate) throws Exception {
		Long productId=(long) 0;
		Date modifyDate=new Date();
		for(Promotion promotion: list) {
			productId=promotionDao.addPromotion(promotion);
			
			productDao.updateProductModify(promotion.getProductId(),modifyDate );
			productPriceDao.updateProductPrice(promotion.getProductId(), discountRate);
			
			if(productId==0) {
				System.out.println("오류발생");
				return productId;
			}
			
		}
		return productId;
	}
}
