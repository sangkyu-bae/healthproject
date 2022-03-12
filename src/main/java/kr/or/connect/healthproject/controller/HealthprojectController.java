package kr.or.connect.healthproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HealthprojectController {
	@GetMapping(path="/index")
	public String index() {
		return "index";
	}
	@GetMapping(path="/securepage")
	public String securitpage() {
		return "secure page";
	}
	@GetMapping(path="/shop")
	public String shop() {
		return "shop";
	}
	@GetMapping(path="/product")
	public String product() {
		return "product";
	}
	@GetMapping(path="/test")
	public String test() {
		return "test";
	}
}
