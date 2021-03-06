package kr.or.connect.healthproject.controller;

import java.io.PrintWriter;
import java.security.Principal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.healthproject.login.dto.MyCart;
import kr.or.connect.healthproject.login.dto.ReservationInfo;
import kr.or.connect.healthproject.login.dto.ReservationInfoPrice;
import kr.or.connect.healthproject.login.dto.User;
import kr.or.connect.healthproject.service.AdminService;
import kr.or.connect.healthproject.service.HealthprojectService;
import kr.or.connect.healthproject.service.MemberService;
import kr.or.connect.healthproject.service.security.UserEntity;


//import kr.or.connect.healthproject.service.MemberService;

@Controller
@RequestMapping(path="/members")
public class MemberController {
//	@Autowired
//	MemberService memberService;
	  private final MemberService memberService;
	  private final PasswordEncoder passwordEncoder;
	  public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
		  this.memberService =	memberService;
		  this.passwordEncoder =passwordEncoder;
	  }
	  @Autowired
	  HealthprojectService healthprojectService;
	  @Autowired
	  AdminService adminService;
	  @GetMapping("/loginform")
	   public String loginform(Principal principal) {
	      //????????? ??????????????? ?????? ???????????? ??????
	      if(principal!=null) {
	         return "redirect:/index";
	      }
	      return "members/loginform.web";
	   }

	@RequestMapping("/loginerror")
	public String loginerror(@RequestParam("login_error")String loginError) {
		return "members/loginerror.web";
	}
	@GetMapping("/joinform")
	public String joinform() {
		return "members/joinform.web";
	}
	@PostMapping("/join")
	public String join(@ModelAttribute User user) {
		System.out.println(user.getName());
		System.out.println(user.getEmail());
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		memberService.addUser(user);
		
		return"redirect:/index";
	}
	
	@GetMapping("/memberinfo")
	public String memberInfo(Principal principal,ModelMap modelMap) {
		String loginId=principal.getName();
		User user=memberService.getUse(loginId);
	    modelMap.addAttribute("member", user);

        return "members/memberinfo.web";
	}
	@GetMapping("/mycart")
	public String mycart(Model model,
			Principal principal) {
		String longinId=principal.getName();
		User user=memberService.getUse(longinId);
		Map<String, Object>params=new HashMap<>();
		params.put("id", user.getId());
		List<MyCart>list=memberService.getCartProduct(params);
		model.addAttribute("cartList",list);
		
		System.out.println(list.size());
		
		return "members/mycart.web";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "members/mypage.web";
	}
	   /*
	    * @param productId ????????????
	    * @param productSizeId ???????????????
	    * @param count ????????????
	    * param??? ???????????? ???????????? ??? ?????? ???????????????
	    */
	   @PostMapping("/directbuy")
	   public String directBuy(@RequestParam (name="productId")Long productId,
	         @RequestParam(name="productSizeId",required = false)Long productSizeId,
	         @RequestParam(name="count")Long count,
	         Principal principal,
	         RedirectAttributes redirectAttributes,
	         HttpServletResponse response,
	         HttpServletRequest request,
	         ModelMap model)throws Exception {
	      String loginId=principal.getName();
	      User user=memberService.getUse(loginId);

	      if(productId==null||productSizeId==null||count==null) {
	         healthprojectService.alert(response, "?????? ????????? ????????? ???????????????.");
	         String referer=request.getHeader("Referer");
	         return "redirect:"+referer+".web";
	      }
	      
	      ReservationInfo info=new ReservationInfo();
	      info.setCancleFlag(1);
	      info.setCreateDate(new Date());
	      info.setModifyDate(new Date());
	      info.setProductId(productId);
	      info.setProductSizeId(productSizeId);
	      info.setReservationDate(new Date());
	      info.setUserId(user.getId());
	      
	      ReservationInfoPrice infoPrice=new ReservationInfoPrice();
	      infoPrice.setCount(count);
	   
	      //memberService.addReservationInfo(info,infoPrice);
	      MyCart cart=memberService.getMaxCartPr(user.getId());
	      redirectAttributes.addAttribute("checkPoint", "123");
	      return "redirect:/members/orderform.web";
	   }


	   @RequestMapping("/orderform")
	   public String orderform(HttpServletRequest request,
			 @RequestParam(name="reservationId",required = false,defaultValue = "0") Integer reservationId,
	         @RequestParam(name="checkPoint",required = false,defaultValue = "0") String checkPoint,
	         ModelMap modelMap,
	         Principal principal) throws Exception{
		
		   if(principal==null) {
		         return "redirect:/index";
		    }
		  String loginId=  principal.getName();
		  User user=memberService.getUse(loginId);
		
		  
		  MyCart carts;
		  List<MyCart> cart = new ArrayList<MyCart>();
		  
		  /*int totalPrice=0;*/
		  String totalPrice="";
		  
		  if(checkPoint.equals("123")) {
			  carts=memberService.getMaxCartPr(user.getId());
			  String price=NumberFormat.getInstance().format(carts.getPrice());
			  carts.setMoneyFormat(price);
		      cart.add(carts);
		      totalPrice=price;
		  }else {
			  Map<String,Object>params=new HashMap<>();
			  params.put("id", user.getId());
			  int price = 0;
			  if(reservationId==0) {
				  cart=memberService.getCartProduct(params);
			      for(MyCart m:cart) {
			    	  price+=m.getPrice()*m.getCount();
			    	  totalPrice=NumberFormat.getInstance().format(price);
			    	  String moneyFormat=NumberFormat.getInstance().format(m.getPrice()*m.getCount());
			    	  m.setMoneyFormat(moneyFormat);
			      }
			  }else {
				  params.put("reservationId", reservationId);
				  cart=memberService.getCartProduct(params);
				  for(MyCart m:cart) {
				 	  price+=m.getPrice()*m.getCount();
			    	  totalPrice=NumberFormat.getInstance().format(price);
			       	  String moneyFormat=NumberFormat.getInstance().format(m.getPrice()*m.getCount());
			    	  m.setMoneyFormat(moneyFormat);
			      }
			  }
		  }
		  
		  modelMap.addAttribute("cart",cart);
		  modelMap.addAttribute("totalPrice",totalPrice);
		  request.setAttribute("cart", cart);
		  List<Map<String, Object>> paymentList=adminService.selectPayMentList();
	      modelMap.addAttribute("paymentList",paymentList);
	    
	  
	      return "members/orderform.web";
	   }
	   
	   @GetMapping("/order_list_opt")
	   public String orderListInfo(Model model,
			@RequestParam(name="startDate", required = false , defaultValue = "0") String startDate,
			@RequestParam(name="lastDate", required = false , defaultValue = "0") String lastDate,
			@RequestParam(name="period",required = false, defaultValue = "4") int period,
			Principal principal,
			HttpServletRequest request )throws Exception {
		  String loginId=principal.getName();
		  User user= memberService.getUse(loginId);
		  NumberFormat numberFormat = NumberFormat.getInstance();
		  Map<String, Object>params=new HashMap<>();
		  params.put("userId", user.getId());
		  if(startDate.equals("0")) {
			  
		  }else {
			  params.put("startDate", startDate);
			  params.put("lastDate", lastDate);
		  }
		
		  
		  List<Map<String, Object>> orderList=memberService.selectMemeberOrder(params);
		  
		  String flag="";
		  String nameString="";
		  String price="";
		  for(Map<String, Object>m : orderList) {
			
			  int prices=Integer.parseInt(String.valueOf (m.get("price")));
			  int count =Integer.parseInt(String.valueOf(m.get("count")));
		
			  int resultPrice=prices*count;
			  price=numberFormat.format(resultPrice);
			  flag=(String) m.get("cancleFlag");
			  	if(flag.equals("1")){
					nameString="????????????";
				}else if(flag.equals("2")){
					nameString="?????????";
				}else if(flag.equals("3")){
					nameString="????????????";
				}else if(flag.equals("4")){
					nameString="????????????";
				}else if(flag.equals("5")){
					nameString="??????";
				}
			  
			  m.put("resultprice", price);
			  m.put("namestring",nameString);
		  }
		  
		  
		  
		  model.addAttribute("orderList",orderList);
		 
		   
		   return "members/orderlistopt.web";
	   }
	   
}
