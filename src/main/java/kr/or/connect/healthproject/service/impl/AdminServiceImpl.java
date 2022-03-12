package kr.or.connect.healthproject.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.healthproject.admin.dao.ProductGetSizeDao;
import kr.or.connect.healthproject.admin.dao.ProductQuestionAnwserDao;
import kr.or.connect.healthproject.admin.dao.ProductSizeDao;
import kr.or.connect.healthproject.admin.dto.ProductGetSize;
import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;
import kr.or.connect.healthproject.admin.dto.ProductSize;
import kr.or.connect.healthproject.login.dao.ProductQuestionDao;
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
}
