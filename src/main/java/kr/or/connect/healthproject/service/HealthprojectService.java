package kr.or.connect.healthproject.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.dto.Product;
import kr.or.connect.healthproject.dto.ProductComment;
import kr.or.connect.healthproject.dto.ProductImage;
import kr.or.connect.healthproject.dto.ProductInfo;
import kr.or.connect.healthproject.dto.ProductList;
import kr.or.connect.healthproject.dto.SelectAllProduct;
import kr.or.connect.healthproject.dto.SelectPromotion;
import kr.or.connect.healthproject.dto.TitleCategory;
import kr.or.connect.healthproject.login.dto.BuyInfo;
import kr.or.connect.healthproject.login.dto.ProductQuestionList;

public interface HealthprojectService {
	public Long addCategory(Category category);
	public Long addProduct(Product product,FileInfo fileInfo,Long price);
	public Long addPromotion(Long productId,double discountRate);
	
	public List<SelectPromotion> getPromotionItem();
	
	public int totalProductCount();
	public List<SelectAllProduct> getProductInfo(Long start);
	
	public List<ProductInfo> getAllProduct(Integer start);
	public List<ProductInfo> getCategoryAllProduct(Integer id,Integer start);
	public int getCount(Integer id);
	public ProductInfo getDetailPr(Long id);
	public List<ProductList> getList();
	
	public List<ProductComment>getProductComment(Long productId);
	public double getAvg(Long productId);
	public int getProductCount(Long productId);
	
	public Long addTitle(TitleCategory category);
	public List<ProductQuestionList>getProductQuestionList(Long productId);
	
	public void alert(HttpServletResponse response,String msg);
}
