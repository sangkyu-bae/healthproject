package kr.or.connect.healthproject.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;
import kr.or.connect.healthproject.admin.dto.ProductSize;
import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.service.AdminService;
import kr.or.connect.healthproject.service.HealthprojectService;
import kr.or.connect.healthproject.service.MemberService;
import kr.or.connect.healthproject.service.UtilService;
import kr.or.connect.healthproject.util.dto.PagingVO;

@RestController
@RequestMapping(path="/api")
public class AdminApiController {
	@Autowired
	AdminService adminService;
	@Autowired
	HealthprojectService healthprojectService;
	@Autowired
	MemberService memberService;
	@Autowired
	UtilService utilService;
	
	@ApiOperation(value = "qna 답변 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/addqnaanswer")
	public Map<String, Object>addQnaAnswer(@RequestParam(name="text")String text,
			@RequestParam(name="productQuestionId")Long productQuestionId,
			Principal principal){
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
		ProductQuestionAnwser anwser=new ProductQuestionAnwser();
		anwser.setCreateDate(new Date());
		anwser.setModifyDate(new Date());
		anwser.setProductQuestionId(productQuestionId);
		anwser.setText(text);
		anwser.setUserId(user.getId());
		
		Long id=adminService.insertproductQuestionAnwser(anwser);
		String messege="";
		
		if(id>0) {
			messege="sucess";
		}else {
			messege="fail";
		}
		Map<String, Object>map=new HashMap<>();
		map.put("messege", messege);
		
		return map;
	}
	
	@ApiOperation(value = "productsize 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/addproductsize")
	public Map<String, Object>addProductsize(@RequestParam(name="size")String size,
			@RequestParam(name="productId")Long productId){
		String messeage="";
		ProductSize productSize=new ProductSize();
		productSize.setProductId(productId);
		productSize.setSize(size);
		
		Long id=adminService.addProductSize(productSize);
		if(id>0) {
			messeage="succes";
		}else {
			messeage="fail";
		}
		Map<String, Object>map=new HashMap<>();
		map.put("messeage", messeage);
		return map;
	}
	
	@ApiOperation(value = "카테고리별 상품 가져오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getCategoryProduct")
	public Map<String, Object>selectCategoryProduct(@RequestParam(name="categoryId")int categoryId) throws Exception{
//		Category category=new Category();
//		category.setId(categoryId);
//		
		int total= utilService.countBoard(categoryId);
		int nowPage=1;
		int cntPerPage=6;
		PagingVO pagingVO=new PagingVO(total,nowPage,cntPerPage);
		
		
		//List<Map<String,Object>>categoryProduct=adminService.selectCategoryProduct(category);
		List<Map<String, Object>>categoryProduct=utilService.selectBoard(pagingVO, categoryId);
		
		Map<String, Object>map=new HashMap<>();
		map.put("categoryProduct", categoryProduct);
		map.put("paging",pagingVO);
		
		
		return map;
	}

	
}
