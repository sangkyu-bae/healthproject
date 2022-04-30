package kr.or.connect.healthproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
	@GetMapping("/main")
	public String ex() {
		return "admin/main.web";
	}
}
