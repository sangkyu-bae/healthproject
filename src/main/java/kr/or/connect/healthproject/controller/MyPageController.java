package kr.or.connect.healthproject.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.runtime.resource.loader.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.healthproject.dto.FileInfo;
import kr.or.connect.healthproject.login.dto.ReservationUserComment;
import kr.or.connect.healthproject.login.dto.ReservationUserCommentImage;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.service.AdminService;
import kr.or.connect.healthproject.service.HealthprojectService;
import kr.or.connect.healthproject.service.MemberService;

@Controller
@RequestMapping(path="/mypage")
public class MyPageController {
	  @Autowired
	  HealthprojectService healthprojectService;
	  @Autowired
	  AdminService adminService;
	  @Autowired
	  MemberService memberService;
	  
	  @GetMapping("/write_review")
	  public String writeReview(Model model,
			  HttpServletRequest request,
			  @RequestParam(name="startDate", required = false, defaultValue = "0")String startDate
			  ,@RequestParam(name="lastDate",required = false,defaultValue = "0")String lastDate
			  ,@RequestParam(name="period",required = false, defaultValue = "4")int period
			  ,Principal principal
			 ) throws Exception {
		  String loginId=principal.getName();
		  User user=memberService.getUse(loginId);
		  
		  Map<String, Object>params=new HashMap<>();
		  params.put("userId", user.getId());
		  
		  if(startDate.equals("0")){
			  
		  }else {
			  params.put("startDate", startDate);
			  params.put("lastDate", lastDate);
		  }
		  
		  params.put("buyComplate", 1);
		  List<Map<String, Object>> orderList=memberService.selectMemeberOrder(params);
		  
		  model.addAttribute("orderList", orderList);
		  
		  
		  return "mypage/writereview.web"; 
	  }
	  @GetMapping("/review")
	  public String review(Model model
			  ,Principal principal) throws Exception {
		  String loginId=principal.getName();
		  User user =memberService.getUse(loginId);
		  
		  Map<String, Object>map=new HashMap<>();
		  map.put("userId", user.getId());
		  
		  List<Map<String, Object>> commentList=memberService.selectComment(map);
		  
		  model.addAttribute("commentList",commentList);
		  
		  
		  return "mypage/review.web";
		  
	  }
	  
	  @GetMapping("/qa")
	  public String qa(Model model
			  ,Principal principal
			  ,@RequestParam(name="startDate", required = false, defaultValue = "0")String startDate
			  ,@RequestParam(name="lastDate",required = false,defaultValue = "0")String lastDate
			  ,@RequestParam(name="period",required = false, defaultValue = "4")int period
			  )throws Exception {
		  String loginId=principal.getName();
		  User user=memberService.getUse(loginId);
		  Map<String, Object>params=new HashMap<>();
		  
		  params.put("userId", user.getId());
		  if(startDate.equals("0")){
			  
		  }else {
			  params.put("startDate", startDate);
			  params.put("lastDate", lastDate);
		  }
		  List<Map<String, Object>> questionList=memberService.selectProductQuestion(params);
		  
		  model.addAttribute("questionList",questionList);
		  
		  
		  return "mypage/qa.web";
	  }
	  /*
	   *리뷰작성 페이지 
	   */
	  @PostMapping("/basic_review_write")
	  public String basicReviewWrite(Principal principal,
			  @ModelAttribute("comment") ReservationUserComment comment,
			  @RequestParam(name="type")String type,
			  Model model) throws Exception{
		  
		  if(type.equals("basic")) {
			  model.addAttribute("type","basic");
		  }else {
			  model.addAttribute("type","image");
		  }
		  
		  return "mypage/basicReviewWrite.web";
	  }
	  /*
	   * 리뷰작성 등록
	   * @params ReservationUserComment
	   */
	  @PostMapping("/review_add")
	  public String reviewAdd(@ModelAttribute ReservationUserComment comment,
			  @RequestParam(name="file", required = false)MultipartFile file,
			  Principal principal,
			  HttpServletResponse response,
			  HttpServletRequest request,
			  Model model) throws Exception{
		  
		  String loginId=principal.getName();
		  
		 
		  if(comment.getComment()==null||
			 (comment.getProductId()==null||comment.getProductId()==0)||
			 comment.getScore()==0.0||
			 comment.getReservationInfoId()==null|| comment.getReservationInfoId()==0) {
			  healthprojectService.alert(response, "리뷰 작성요소가 미설정되었습니다.");
		      String referer=request.getHeader("Referer");
		      return "redirect:"+referer+".web";
		  }
		  
		  
		  User user =memberService.getUse(loginId);
		  comment.setUserId(user.getId());
		  
		  if(file!=null) {
			  String root=request.getServletContext().getRealPath("/resources")+"/img/commnet/";
			  
			  System.out.println(root);
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
			memberService.addReservationUserComment(comment, fileInfo);
		System.out.println("사진 있음");
		  
		  }else {
			memberService.addReservationUserComment(comment);
			System.out.println("사진없음");
		  }
		  
	
		  
		  
		  model.addAttribute("msg", "insert Review.");
		  model.addAttribute("url", "/mypage/review");
	
		  
		  
		  return "redirect";
	  }
	  
}
