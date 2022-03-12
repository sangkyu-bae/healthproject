package kr.or.connect.healthproject.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.healthproject.dao.CategoryDao;
import kr.or.connect.healthproject.dao.FileInfoDao;
import kr.or.connect.healthproject.dao.ProductCommentDao;
import kr.or.connect.healthproject.dao.ProductDao;
import kr.or.connect.healthproject.dao.ProductImageDao;
import kr.or.connect.healthproject.dao.ProductInfoDao;
import kr.or.connect.healthproject.dao.ProductPriceDao;
import kr.or.connect.healthproject.dao.PromotionDao;
import kr.or.connect.healthproject.dao.SelectAllProductDao;
import kr.or.connect.healthproject.dao.SelectPromotionDao;
import kr.or.connect.healthproject.dao.TitleCategoryDao;
import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.dto.Product;
import kr.or.connect.healthproject.dto.ProductComment;
import kr.or.connect.healthproject.dto.ProductImage;
import kr.or.connect.healthproject.dto.ProductInfo;
import kr.or.connect.healthproject.dto.ProductList;
import kr.or.connect.healthproject.dto.ProductPrice;
import kr.or.connect.healthproject.dto.Promotion;
import kr.or.connect.healthproject.dto.SelectAllProduct;
import kr.or.connect.healthproject.dto.SelectPromotion;
import kr.or.connect.healthproject.dto.TitleCategory;
import kr.or.connect.healthproject.login.dao.BuyInfoDao;
import kr.or.connect.healthproject.login.dao.ProductQuestionAnwserDtDao;
import kr.or.connect.healthproject.login.dao.ProductQuestionListDao;
import kr.or.connect.healthproject.login.dto.BuyInfo;
import kr.or.connect.healthproject.login.dto.ProductQuestionList;
import kr.or.connect.healthproject.service.HealthprojectService;

@Service
public class HealthprojectServiceImpl implements HealthprojectService{
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	FileInfoDao fileInfoDao;
	@Autowired
	ProductImageDao productImageDao;
	@Autowired
	ProductPriceDao productPriceDao;
	@Autowired
	PromotionDao promotionDao;
	@Autowired
	SelectPromotionDao selectPromotionDao;
	@Autowired
	SelectAllProductDao selectAllProductDao;
	@Autowired
	ProductInfoDao productInfoDao;
	@Autowired
	ProductCommentDao productCommentDao;
	@Autowired
	TitleCategoryDao titleCategoryDao;
	@Autowired
	ProductQuestionListDao productQuestionListDao;
	@Autowired
	ProductQuestionAnwserDtDao productQuestionAnwserDtDao;

	
	@Override
	@Transactional(readOnly = false)
	public Long addCategory(Category category) {
		Long id=categoryDao.addCategory(category);
		return id;
	}


	@Override
	@Transactional(readOnly = false)
	public Long addProduct(Product product,FileInfo fileInfo,Long price) {
		product.setCreateDate(new Date());
		product.setModifyDate(new Date());
		Long productId= productDao.addProduct(product);
		
		////가격 객체 등록
		ProductPrice productPrice=new ProductPrice();
		productPrice.setCreateDate(new Date());
		productPrice.setDiscountRate(0.00);
		productPrice.setModifyDate(new Date());
		productPrice.setPrice(price);
		productPrice.setPriceTypeName("A");
		productPrice.setProductId(productId);
		Long productPriceId=productPriceDao.addProductPrice(productPrice);
		//파일 등록
		fileInfo.setCreateDate(new Date());
		fileInfo.setModifyDate(new Date());
		Long fileInfoId=fileInfoDao.addFileInfo(fileInfo);
		
		//상품 이미지 등록
		ProductImage productImage=new ProductImage();
		productImage.setProductId(productId);
		productImage.setFileId(fileInfoId);
		productImage.setType("th");
		Long productImageId=productImageDao.addproductImage(productImage);
		
		return productImageId;
	}


	@Override
	@Transactional(readOnly = false)
	public Long addPromotion(Long productId,double discountRate) {
		Promotion promotion =new Promotion();
		Date modifyDate=new Date();
		
		promotion.setProductId(productId);
		Long promotionId=promotionDao.addPromotion(promotion);
		
		productDao.updateProductModify(productId, modifyDate);
		productPriceDao.updateProductPrice(productId, discountRate);
		return promotionId;
	}


	@Override
	@Transactional
	public List<SelectPromotion> getPromotionItem() {
		List<SelectPromotion>list=selectPromotionDao.getPromotionItem();
		return list;
	}


	@Override
	@Transactional
	public List<SelectAllProduct> getProductInfo(Long start) {
		List<SelectAllProduct>list=selectAllProductDao.getProductInfo(start);
		return list;
	}


	@Override
	@Transactional
	public int totalProductCount() {
		int count=selectAllProductDao.totalProductCount();
		return count;
	}


	@Override
	@Transactional
	public List<ProductInfo> getAllProduct(Integer start) {
		List<ProductInfo>list=productInfoDao.getAllProduct(start);
		return list;
	}


	@Override
	@Transactional
	public List<ProductInfo> getCategoryAllProduct(Integer id, Integer start) {
		List<ProductInfo>list=productInfoDao.getCategoryAllProduct(id, start);
		return list;
	}


	@Override
	@Transactional
	public int getCount(Integer id) {
		int count =productInfoDao.getCount(id);
		return count;
	}


	@Override
	@Transactional
	public ProductInfo getDetailPr(Long id) {
		ProductInfo info=productInfoDao.getDetailPr(id);
		return info;
	}


	@Override
	@Transactional
	public List<ProductComment> getProductComment(Long productId) {
		List<ProductComment>list=productCommentDao.getProductComment(productId);
		return list;
	}


	@Override
	@Transactional
	public double getAvg(Long productId) {
		double avg=productCommentDao.getAvg(productId);
		return avg;
	}


	@Override
	@Transactional
	public int getProductCount(Long productId) {
		int count=productCommentDao.getCount(productId);
		return count;
	}


	@Override
	@Transactional(readOnly = false)
	public Long addTitle(TitleCategory category) {
		Long id =titleCategoryDao.addTitle(category);
		return id;
	}

	@Override
	@Transactional
	public List<ProductQuestionList> getProductQuestionList(Long productId) {
		List<ProductQuestionList>list=productQuestionListDao.getProductQuestion(productId);
		
		for(ProductQuestionList c:list) {
			c.setProductQuestionAnwserDt(productQuestionAnwserDtDao.getAnwserDt(c.getId()));
		}
		return list;
	}


	@Override
	@Transactional
	public List<ProductList> getList() {
		List<ProductList>list=productInfoDao.getListId();
		return list;
	}


	@Override
	@Transactional
	public void alert(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter w=response.getWriter();
			w.println(" <script>alert('"+msg+"');history.go(-1);</script>");
			w.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
