package kr.or.connect.healthproject.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.healthproject.admin.dto.ProductQuestionAnwser;
import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.dto.Product;
import kr.or.connect.healthproject.dto.Promotion;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.service.AdminService;
import kr.or.connect.healthproject.service.HealthprojectService;
import kr.or.connect.healthproject.service.MemberService;
import kr.or.connect.healthproject.service.UtilService;
import kr.or.connect.healthproject.util.dto.PagingVO;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	HealthprojectService healthprojectService;
	@Autowired
	UtilService utilService;
	@Autowired
	MemberService memberService;
	/*
	 * 
	 */
	@GetMapping("/main")
	public String adminMain(Model model,
			HttpServletRequest request) throws Exception {
		
		
		Map<String, Object>maps=new HashMap<>();
		List<Map<String, Object>>orderList=adminService.selectComplteOrderList(maps);
		
		
		
		String totalPrice="";
		int money=0;
		
		if(orderList!=null) {
			for(Map<String, Object> map:orderList ) {
				money=Integer.parseInt(String.valueOf(map.get("totalPrice")));
				totalPrice=String.format("%,d", money);
				map.put("updateTotalPrice", totalPrice);
				
			}
		}
		
		model.addAttribute("orderList",orderList);

		return "admin/main.web";
	}
	
	/*
	 * 주문완료되고, 상품배송 완료된 과거 주문을 보여주는 페이지
	 */
	@GetMapping("/pastOrderList")
	public String pastOrderList(HttpServletRequest request,
			Model model,
			@RequestParam(name="startDate",required = false, defaultValue = "0")String startDate,
			@RequestParam(name="lastDate", required = false,defaultValue = "0")String lastDate) throws Exception {
		
		Map<String, Object>maps=new HashMap<>();
		
		maps.put("past", "true");
		
		if(!startDate.equals("0")&&!lastDate.equals("0")) {
			maps.put("startDate", startDate);
			maps.put("lastDate", lastDate);
		}
	
		
		List<Map<String, Object>>pastOrderList=adminService.selectComplteOrderList(maps);
		
		String totalPrice="";
		int money=0;
		
		if(pastOrderList!=null) {
			for(Map<String, Object> map:pastOrderList ) {
				money=Integer.parseInt(String.valueOf(map.get("totalPrice")));
				totalPrice=String.format("%,d", money);
				map.put("updateTotalPrice", totalPrice);
				
			}
		}
		
		
		
		model.addAttribute("pastOrderList",pastOrderList);
		
	
		return "admin/pastOrderList.web";
	}
	
	/*
	 *상품등록폼 페이지 
	 */
	
	@GetMapping("/productWrite")
	public String productManageMent(Model model) throws Exception {
		List<Category>categoryList=adminService.selectCategory(); 
		model.addAttribute("categoryList",categoryList);

		return "admin/productWrite.web";
	}
	/*
	 * 상품등록
	 */
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute Product product,
			@RequestParam(name="event",required = false)String event,
			@RequestParam(name="price")Long price,
			@RequestParam(name="file")MultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response,
			Model model
	)throws Exception {
		
		
		if(product==null||(price==null||price==0)||file==null) {
			healthprojectService.alert(response, "상품 등록이 필요한 데이터가 들어오지 않았습니다.");
			String referer=request.getHeader("Referer");
	         return "redirect:"+referer+".web";
		}
		
		if(event==null) {
			product.setEvent("");
		}else {
			product.setEvent(event);
		}
	
		////파일 저장 경로 만들기
		String root=request.getServletContext().getRealPath("/resources")+"/img/product/";
			
		System.out.println(product);
		///파일 경로에 저장하기
		if(file!=null) {
		  try(
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
		
		}
		
		model.addAttribute("msg", "insert Product.");
		model.addAttribute("url", "/shop");
		
		return "redirect";
	}
	
	/*
	 *행사 상품 관리 
	 */
	@GetMapping("/administerPromotion")
	public String administerPromotion(Model model,
			 @RequestParam(value="nowPage", required=false)String nowPage
			,@RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {
		List<Category>categoryList=adminService.selectCategory(); 
		model.addAttribute("categoryList",categoryList);
		
		Category category=new Category();
		
		category.setId((long) 0);//초기 시작
		//List<Map<String,Object>>categoryProduct=adminService.selectCategoryProduct(category);
		
		int total=utilService.countBoard(0);
		
		if(nowPage==null&&cntPerPage==null) {
			nowPage = "1";
			cntPerPage = "6";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "6";
		}
		
		
		PagingVO pagingVO=new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		model.addAttribute("paging", pagingVO);
		model.addAttribute("viewAll", utilService.selectBoard(pagingVO,0));
		//model.addAttribute("categoryProduct",categoryProduct);
		
		return "admin/administerPromotion.web";
	}
	/*
	 * 행사 상품 등록
	 */
	@PostMapping("/addPromotion")
	public String addPromotion(
			@RequestParam(name="discountRate")double discountRate,
			HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		String[] arrayParam = request.getParameterValues("productId");
		List<Promotion>promotions=new ArrayList<Promotion>();
		
		for (String b:arrayParam) {
			Promotion promotion=new Promotion();
			Long porductId= Long.parseLong(b);
			promotion.setProductId(porductId);
			promotions.add(promotion);
		}

		Long insertId=adminService.insertPromotion(promotions, discountRate);
		
		if(insertId==0) {
			healthprojectService.alert(response, "상품등록이 처리되지 않았습니다.");
			String referer=request.getHeader("Referer");
	         return "redirect:"+referer+".web";
		}
		
		return "redirect:/admin/administerPromotion.web";
	}
	
	/*
	 * 상품문의 관리 페이지
	 */
	@GetMapping("/selectQuestion")
	public String selectQuestion(Model model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		Map<String, Object>map=new HashMap<>();
		map.put("questionAnswer", 0);
		
		List<Map<String, Object>>selectProductQuestion=adminService.selectQuestion(map);
		
		model.addAttribute("selectProductQuestion", selectProductQuestion);
		
		return "admin/selectQuestion.web";
	}
	/*
	 * 상품 문의 답변 등록페이지
	 * @params qustionId
	 */
	@GetMapping("/writeQuestion")
	public String writeQuestion(@RequestParam(name="qustionId") int qustionId,
			Model model,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object>map=new HashMap<>();
		map.put("qustionId", qustionId);
		
		List<Map<String, Object>>selectProductQuestion=adminService.selectQuestion(map);
		Map<String, Object>selectOneQuestion=new HashMap<>();
		
		for(Map<String, Object> tests:selectProductQuestion) {
			for(Map.Entry<String, Object> entry:tests.entrySet()){
		        String key = entry.getKey();
		        Object value = entry.getValue();
		        selectOneQuestion.put(key, value);
			}
		}
		
		model.addAttribute("selectOneQuestion", selectOneQuestion);
		return "admin/writeQuestion.web";
	}

	/*
	 * 상품문의 답변 등록
	 * @params ProductQuestionAnwser vo
	 */
	@PostMapping("/addQustionAnwser")
	public String addQustionAnwser(@ModelAttribute ProductQuestionAnwser vo,
			HttpServletRequest request,
			HttpServletResponse response,
			Principal principal,
			Model model) {
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
		
		vo.setUserId(user.getId());
		vo.setCreateDate(new Date());
		vo.setModifyDate(new Date());
		if(vo.getProductQuestionId()==null||vo.getProductQuestionId()==0||vo.getText()==null) {
			  healthprojectService.alert(response, "상품 답변 요소가 미설정되었습니다.");
		      String referer=request.getHeader("Referer");
		      return "redirect:"+referer+".web";
		}
		adminService.insertproductQuestionAnwser(vo);
		model.addAttribute("msg", "insert QestionAnswer.");
		model.addAttribute("url", "/admin/selectQuestion");
	
		return "redirect";
	}
}

