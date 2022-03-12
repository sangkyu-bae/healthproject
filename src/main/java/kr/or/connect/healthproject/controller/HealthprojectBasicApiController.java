package kr.or.connect.healthproject.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.healthproject.admin.dto.ProductGetSize;
import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.dto.Product;
import kr.or.connect.healthproject.dto.ProductComment;
import kr.or.connect.healthproject.dto.ProductInfo;
import kr.or.connect.healthproject.dto.ProductList;
import kr.or.connect.healthproject.dto.SelectAllProduct;
import kr.or.connect.healthproject.dto.SelectPromotion;
import kr.or.connect.healthproject.dto.TitleCategory;
import kr.or.connect.healthproject.login.dto.ProductQuestionList;
import kr.or.connect.healthproject.service.AdminService;
import kr.or.connect.healthproject.service.HealthprojectService;


@RestController
@RequestMapping(path="/api")
public class HealthprojectBasicApiController {
	@Autowired
	HealthprojectService healthprojectService;
	@Autowired
	AdminService adminService;
	@ApiOperation(value = "카테고리 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	
	@PostMapping(path="/category")
	public Map<String, Object> addCategory(@RequestParam(name="name")String name){
		Category category=new Category();
		category.setName(name);
		
		healthprojectService.addCategory(category);
		Map<String, Object>map=new HashMap<>();
		
		map.put("category", category);
		return map;
	}
	@ApiOperation(value = "판매제품 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/addproduct")
	public Map<String, Object>addProduct(@RequestParam(name="categoryId")Long categoryId,
			@RequestParam(name="content")String content,
			@RequestParam(name="description")String description,
			@RequestParam(name="event",required = false)String event,
			@RequestParam(name="price")Long price,
			@RequestParam("file")MultipartFile file,
			HttpServletRequest request){
		Product product=new Product();
		product.setCategoryId(categoryId);
		product.setContent(content);
		product.setDescription(description);
		
		
		if(event==null) {
			product.setEvent("");
		}else {
			product.setEvent(event);
		}
	
		////파일 저장 경로 만들기
//		String root= request.getSession().getServletContext().getRealPath("/");
//		String addFileRoot=root+"/resources/img/product";
		String root="C:/Users/samsung/eclipse-workspace/healthproject/src/main/webapp/resources/img/product/";
		///파일 경로에 저장하기
		  try(
	                // 맥일 경우 
	                //FileOutputStream fos = new FileOutputStream("/tmp/" + file.getOriginalFilename());
	                // 윈도우일 경우
				  	FileOutputStream fos=new FileOutputStream(root+file.getOriginalFilename());
					InputStream is= file.getInputStream();
	        ){
	        	    int readCount = 0;
	        	    byte[] buffer = new byte[1024];
	            while((readCount = is.read(buffer)) != -1){
	                fos.write(buffer,0,readCount);
	            }
	        }catch(Exception ex){
	            throw new RuntimeException("file Save Error");
	        }
		////파일 객체 생성 값 지정
		FileInfo fileInfo=new FileInfo();
		fileInfo.setContentType(file.getContentType());
		fileInfo.setDeleteFlag(0);
		fileInfo.setFileName(file.getOriginalFilename());
		fileInfo.setSaveFileName("product/"+file.getOriginalFilename());

		healthprojectService.addProduct(product, fileInfo,price);
		
		Map<String, Object> map=new HashMap<>();
		map.put("product", product);
		map.put("fileInfo", fileInfo);
	
		return map;
	}
	
	
	@ApiOperation(value = "프로모션 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/addpromotion")
	public Map<String, Object>addPromotion(@RequestParam(name="productId")Long productId,
			@RequestParam(name="discountRate")double discountRate){
		Long id=healthprojectService.addPromotion(productId, discountRate);
		String sucess="";
		if(id>0) {
			sucess="sucess";
		}else {
			sucess="false";
		}
		Map<String, Object>map=new HashMap<>();
		map.put("sucess", sucess);
		
		return map;
	}
	
	@ApiOperation(value = "프로모션 정보 가져오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getpromotion")
	public Map<String, Object>getPromotion(){
		List<SelectPromotion>list=healthprojectService.getPromotionItem();
		
		Map<String, Object>map=new HashMap<>();
		map.put("promotionList", list);
		return map;
	}
	
	@ApiOperation(value = "상품 정보 가져오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getproduct")
	public Map<String, Object>getProductInfo(@RequestParam(name="start")Long start){
		List<SelectAllProduct>allProduct=healthprojectService.getProductInfo(start);
		int totalCount=healthprojectService.totalProductCount();
		Map<String,Object>map=new HashMap<>();
		map.put("totalCount", totalCount);
		map.put("allProduct", allProduct);

		return map;
	}
	
	@ApiOperation(value = "shop페이지 상품 정보 가져오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getallproduct")
	public Map<String, Object>getAllProduct(@RequestParam(name="id",required = false)Integer id,
			@RequestParam(name="start",required = false,defaultValue = "0")Integer start){
		List<ProductInfo>productinfo;
		int count;
		if(id==null||id==0) {
			productinfo=healthprojectService.getAllProduct(start);
			count=healthprojectService.totalProductCount();
		}else {
			productinfo=healthprojectService.getCategoryAllProduct(id, start);
			count=healthprojectService.getCount(id);
		}
		Map<String, Object>map=new HashMap<>();
		map.put("count", count);
		map.put("productinfo", productinfo);
		
		return map;
	}
	
	@ApiOperation(value = "product 상세 페이지 상품 정보 가져오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getdetalipr")
	public Map<String, Object>getDetailPr(@RequestParam(name="id")Long id){
		//ProductInfo info=healthprojectService.getDetailPr(id);
		ProductGetSize info=adminService.getProductAndSize(id);
		List<ProductList> list=healthprojectService.getList();
		Map<String, Object>map=new HashMap<>();
		map.put("productInfo", info);
		map.put("list", list);
		return map; 
	}
	
	@ApiOperation(value = "product 상세 페이지 별점및 코멘트 가져오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getcomment")
	public Map<String, Object>getComment(@RequestParam(name="productId")Long productId){
		List<ProductComment>list=healthprojectService.getProductComment(productId);
		List<ProductQuestionList>productQuestionList=healthprojectService.getProductQuestionList(productId);
		double avg=healthprojectService.getAvg(productId);
		int count=healthprojectService.getProductCount(productId);
		Map<String, Object>map=new HashMap<>();
		map.put("avg", avg);
		map.put("count", count);
		map.put("comment", list);
		map.put("productQuestionList", productQuestionList);
		return map;
	}
	
	@ApiOperation(value = "q&a 카테고리 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/addQacategory")
	public Map<String, Object>addQaCategory(@RequestParam(name="name")String name){
		String message="";
		TitleCategory category=new TitleCategory();
		category.setName(name);
		Long id=healthprojectService.addTitle(category);
		if(id>0) {
			message="ok";
		}else {
			message="fail";
		}
		Map<String, Object>map=new HashMap<>();
		map.put("message", message);
		
		return map;
	}
	
}
