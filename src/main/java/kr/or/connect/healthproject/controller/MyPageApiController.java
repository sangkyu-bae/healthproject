package kr.or.connect.healthproject.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.service.HealthprojectService;
import kr.or.connect.healthproject.service.MemberService;

@RestController
@RequestMapping(path="/api")
public class MyPageApiController {
	@Autowired
	MemberService memberService;
	@Autowired
	HealthprojectService healthprojectService;

	@ApiOperation(value = "상품 qa 답변 가져오기")
	@ApiResponses({  // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Exception")
    })
	@GetMapping(path="/getQaAnwser")
	public Map<String, Object>selectQaAnwser(Principal principal,
			@RequestParam(name="productQuestionId") int productQuestionId)throws Exception{
		
		String loginId=principal.getName();
		
		
		Map<String, Object>map=new HashMap<>();
		if(loginId==null) {
			map.put("error", "loginError");
			return map;
		}
		User user=memberService.getUse(loginId);
		map.put("productQuestionId", productQuestionId);
		map.put("loginId",user.getId());
		
		Map<String, Object>qaAnwser=memberService.selectQaAnwser(map);
		if(qaAnwser.size()==0) {
			Map<String, Object>maps=new HashMap<>();
			maps.put("error", "logicError");
			return maps;
		}
		
		return qaAnwser;
		
	}
}
