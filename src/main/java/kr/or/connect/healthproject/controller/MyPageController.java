package kr.or.connect.healthproject.controller;

import java.security.Principal;
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

import kr.or.connect.healthproject.login.dto.ReservationUserComment;
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
			  ,Principal principal) throws Exception {
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
			  @ModelAttribute ReservationUserComment comment,
			  Model model) throws Exception{
		  
		  model.addAttribute("comment",comment);
		  
		 
		  return "mypage/basicReviewWrite.web";
	  }
	  /*
	   * 리뷰작성 등록
	   * @params ReservationUserComment
	   */
	  @PostMapping("/review_add")
	  public String reviewAdd(@ModelAttribute ReservationUserComment comment,
			  Principal principal,
			  HttpServletResponse response,
			  HttpServletRequest request) throws Exception{
		  
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
		  
		  
		 //memberService.addReservationUserComment(comment);
		  
		  
		  return "redirect:/members/mypage";
	  }
	  
}
