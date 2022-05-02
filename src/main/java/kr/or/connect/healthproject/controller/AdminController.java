package kr.or.connect.healthproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.healthproject.dto.Category;
import kr.or.connect.healthproject.service.AdminService;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
	@Autowired
	AdminService adminService;
	
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
	
	
	@GetMapping("/productWrite")
	public String productManageMent(Model model) throws Exception {
		List<Category>categoryList=adminService.selectCategory(); 
		model.addAttribute("categoryList",categoryList);
		
		return "admin/productWrite.web";
	}
}
