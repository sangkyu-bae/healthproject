package kr.or.connect.healthproject.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.login.dto.BuyCount;
import kr.or.connect.healthproject.login.dto.BuyInfo;
import kr.or.connect.healthproject.login.dto.MyCart;
import kr.or.connect.healthproject.login.dto.MyInfoComment;
import kr.or.connect.healthproject.login.dto.MyInfoProductQuestion;
import kr.or.connect.healthproject.login.dto.OrderList;
import kr.or.connect.healthproject.login.dto.ProductQuestion;
import kr.or.connect.healthproject.login.dto.ProductQuestionList;
import kr.or.connect.healthproject.login.dto.ReservationInfo;
import kr.or.connect.healthproject.login.dto.ReservationInfoPrice;
import kr.or.connect.healthproject.login.dto.ReservationUserComment;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.member.dto.MemberReservationInfo;
import kr.or.connect.healthproject.service.HealthprojectService;
import kr.or.connect.healthproject.service.MemberService;

@RestController
@RequestMapping(path="/api")
public class MemberApiController {
	@Autowired
	MemberService memberService;
	@Autowired
	HealthprojectService healthprojectService;
	
	
	
	@ApiOperation(value = "상품 예약 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/addmemberproducts")
	public Map<String, Object>addmemberproduct(@RequestParam (name="productId")Long productId,
			@RequestParam(name="productSizeId")Long productSizeId,
			@RequestBody ReservationInfoPrice infoPrice,
			Principal principal){
		
		ReservationInfo info=new ReservationInfo();
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
		String messege="";
		
		
		info.setCancleFlag(0);
		info.setCreateDate(new Date());
		info.setModifyDate(new Date());
		info.setProductId(productId);
		info.setReservationDate(new Date());
		info.setUserId(user.getId());
		info.setProductSizeId(productSizeId);
		
		Long infoid=memberService.addReservationInfo(info,infoPrice);
		
		if(infoid>0) {
			messege="sucess";
		}else {
			messege="fail";
		}
		Map<String, Object>map=new HashMap<>();
		map.put("messege", messege);
		return map;
	}
	
	
	@ApiOperation(value = "코멘트 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping("/addcomment")
	public Map<String, Object>addcomment(//@RequestBody ReservationUserComment comment,
			@RequestParam(name="comment")String comment,
			@RequestParam(name="productId")Long productId,
			@RequestParam(name="reservationInfoId")Long reservationInfoId,
			@RequestParam(name="score")double score,
			@RequestParam(name="file", required=false)MultipartFile file,
			Principal principal){
		
		String messesge="";
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);

		ReservationUserComment reservationUserComment=new ReservationUserComment();
		reservationUserComment.setComment(comment);
		reservationUserComment.setCreateDate(new Date());
		reservationUserComment.setModifyDate(new Date());
		reservationUserComment.setProductId(productId);
		reservationUserComment.setReservationInfoId(reservationInfoId);
		reservationUserComment.setScore(score);
		System.out.println(user.getId());
		reservationUserComment.setUserId(user.getId());
		if(file!=null) {
			String root="C:/Users/samsung/eclipse-workspace/healthproject/src/main/webapp/resources/img/commnet/";
			///파일 경로에 저장하기
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
			fileInfo.setSaveFileName("commnet/"+file.getOriginalFilename());
			memberService.addReservationUserComment(reservationUserComment, fileInfo);
			messesge="comment와image등록 완료";
		}else {
			memberService.addReservationUserComment(reservationUserComment);
			messesge="comment만 등록";
		}
		Map<String, Object>map=new HashMap<>();
		map.put("messesge", messesge);
		return map;
	}
	
	@ApiOperation(value = "상품 질문 등록 하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping("/addquestion")
	public Map<String, Object>addQuestion(@RequestParam (name="productId")Long productId,
			@RequestParam(name="titleCategory")Long titleCategory,
			@RequestParam(name="text")String text,
			Principal principal){
		String massege="";
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
		ProductQuestion productQuestion=new ProductQuestion();
		productQuestion.setCreateDate(new Date());
		productQuestion.setModifyDate(new Date());
		productQuestion.setProductId(productId);
		productQuestion.setText(text);
		productQuestion.setTitleCategoryId(titleCategory);
		productQuestion.setQuestionAnswer(0);
		productQuestion.setUserId(user.getId());
		
		int id=memberService.insertPrQuestion(productQuestion);
		if(id>0) {
			massege="sucess";
		}else {
			massege="fail";
		}
		Map<String, Object>map=new HashMap<>();
		map.put("massege", massege);
		
		return map;
	}
	@ApiOperation(value = "예약 상품 가져 오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="getcartproduct")
	public Map<String, Object>getCartProduct(Principal principal){
		String longinId=principal.getName();
		User user=memberService.getUse(longinId);
		List<MyCart>list=memberService.getCartProduct(user.getId());
		
		Map<String, Object>map=new HashMap<>();
		map.put("cartList", list);
		
		return map;
	}
	@ApiOperation(value = "상품구매 누른 상품가져 오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getbuyPr")
	public Map<String, Object>getBuyReserveProduct(Principal principal){
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
		
		MyCart cart=memberService.getMaxCartPr(user.getId());
		Map<String, Object>map=new HashMap<>();
		map.put("mycart", cart);
		
		return map;
	}
	
	@ApiOperation(value = "예약 상품 삭제하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PutMapping("deleteReserve")
	public Map<String, Object>deleteReservation(@RequestParam(name="id")Long id){
		int deleteCount=memberService.deleteReservation(id);
		return Collections.singletonMap("result", deleteCount>0?"success":"fail");
	}
	
	@ApiOperation(value = "로그인 사용자 마이리스트")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getmylist")
	public Map<String, Object>getMyList(Principal principal){
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
		
		
		List<BuyInfo>myBuyInfo=memberService.getBuyInfo(user.getId());
		List<BuyCount>getCount=memberService.getCount(user.getId());
		List<MyInfoProductQuestion>getMyPrQuestion=memberService.getMyPrQuestion(user.getId());
		List<MyInfoComment>myComment=memberService.getMyComment(user.getId());
		Map<String, Object>map=new HashMap<>();
		map.put("myBuyInfo", myBuyInfo);
		map.put("getCount", getCount);
		map.put("getMyPrQuestion", getMyPrQuestion);
		map.put("myComment", myComment);
		return map;
	}
	
	@ApiOperation(value = "사용자 주문 등록하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/insertorder")
	public Map<String, Object>insertOrderList(@RequestBody OrderList list ,
			@RequestParam (name="productId",required = false)Long productId,
			@RequestParam(name="productSizeId",required = false)Long productSizeId,
			@RequestParam(name="count", required =false)Long count,
			Principal principal){
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
		Long reservationInfoId=list.getReservationInfoId();
		int reservationCancle=0;
		Long orderId = null;
		if(reservationInfoId==null) {
			ReservationInfoPrice infoPrice=new ReservationInfoPrice();
			infoPrice.setCount(count);
			
			ReservationInfo info=new ReservationInfo();
			info.setCancleFlag(1);
			info.setCreateDate(new Date());
			info.setModifyDate(new Date());
			info.setProductId(productId);
			info.setReservationDate(new Date());
			info.setUserId(user.getId());
			info.setProductSizeId(productSizeId);
			
			Long infoid=memberService.addReservationInfo(info,infoPrice);
			list.setReservationInfoId(infoid);
			list.setUserId(user.getId());
			orderId=memberService.insertOrderList(list);
		}else if(reservationInfoId!=null) {
			list.setUserId(user.getId());
			reservationCancle=memberService.updateReservationCancleFlag(reservationInfoId);
			orderId=memberService.insertOrderList(list);
		}
		return Collections.singletonMap("message", orderId>0? "sucess":"fail");
		
	}
	@ApiOperation(value = "예약 수량 수정하기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@PostMapping(path="/updateQtn")
	public Map<String, Object>updateReservationInfo(@RequestBody MemberReservationInfo info,
			Principal principal)throws Exception{
		String message="";
		Map< String, Object>map=new HashMap<>();
		if(principal==null) {
			message="fali";
			map.put("message", message);
		}
		info.setModifyDate(new Date());
		int sucess=memberService.updateReservationInfo(info);
		if(sucess!=0) {
			message="ok";
		}
		map.put("message", message);
		
		return map;
	}
	
}
