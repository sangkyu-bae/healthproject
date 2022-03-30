package kr.or.connect.healthproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
			  HttpServletRequest request) {
		  
		  return "mypage/writereview.web";
	  }
	  
}
