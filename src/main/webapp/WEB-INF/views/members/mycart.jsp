<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>캠핑모아 MADAGASCAR</title>
    <link href="${path}/resources/css/reset.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/index.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  </head>
  <body>
    <header>
      <div class="head-notice">
        <span class="header_text">15% OFF ALL TENT</span>
      </div>
      <nav class="fix-head">
        <div class="container">
          <div class="headcont">
            <a href="/healthproject/index">MADAGASCAR</a>
          </div>
          <div class="navbar-nav">
            <ul class="navbar_ul">
               <li class="ul-list"><a href="/healthproject/index" class="nav-li ">HOME</a></li>
              <li class="ul-list"><a href="/healthproject/shop" class="nav-li">SHOP</a></li>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/loginform" class="nav-li">LOGIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="/healthproject/logout" class="nav-li">Logout</a></li>
              </sec:authorize>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/joinform" class="nav-li chcke">JOIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="memberinfo" class="nav-li">MYPAGE</a></li>
              </sec:authorize>
              
              <li class="ul-list"><a href="#" class="nav-li">CONTACT</a></li>
              <li class="ul-list"><a href="mycart" class="nav-li">MYCART</a></li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    <section>
      <div class="cart_container">
        <div class="cart_wrap">
          <div class="cart_head cart_left">
            <div class="cart_head_name">
                My cart
            </div>
             <ul class="my_cart_list_box">
              <li class="my_cart_list">
                <div class="cart_section">
                  <div class="cart_img_box cart_section_box">
                    <img src="" alt="">
                  </div>
                  <div class="cart_section_box cart_product_info">
                    <div class="cart_product_name">
                      I'm a product
                    </div>
                    <div class="cart_product_price cart_color">
                      12,000원
                    </div>
                    <div class="cart_color">
                      Size:L
                    </div>
                  </div>
                  <div class="cart_section_box cart_product_qut">
                    <input type="text"class="cart_input" name="" value="1">
                    <div class="qtn_boxs min_box">
                      -
                    </div>
                    <div class="qtn_boxs plus_box">
                      +
                    </div>
                  </div>
                  <div class="cart_section_box cart_product_prices">
                    12,000원
                  </div>
                  <div class="cart_section_box cart_delte_box">
                    X
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <div class="cart_order_container cart_left">
            <div class="order_head">
              Order summary
            </div>
            <div class="order_list_wrap">
              <div class="subtota sub_total_box">
                <span class="order_sub_head">subtotal</span>
                <span class="order_sub_price sub_total">12,000원</span>
              </div>
              <div class="subtota sub_shipping">
                <span class="order_sub_head">shipping</span>
                <span class="order_sub_price">Free</span>
              </div>
              <div class="subtota location_box">
                Deajon, South Korea
              </div>
            </div>
            <div class="total_box">
              <span class="order_sub_head">Total</span>
              <span class="order_sub_price total_price">12,000원</span>
            </div>
            <div class="order_check_box">
              <button type="button"class="order_check_box_btn" onclick="buy_all_product()"; name="button">Checkout</button>
            </div>
          </div>
        </div>
      </div>
      <div class="popup" style="display:none;">
      	<div class="popup_layer">
      		<div class="text_area">
      			<strong class="title">
                      기사미 태티컬 Polar Fleece Jacket
                </strong>
      			<p class="text">상품을 장바구니에서<br> 삭제하겠습니까</p>
      		</div>
      		<div class="btn_area">
      			<button type="button" name="button" class="btn yes">예</button>
      			<button type="button" name="button" class="btn no">아니오</button>
      		</div>
      	</div>
    	   <div class="popup_dimmed"></div>
       </div>
    </section>
    <footer>
      <div class="foot_section">
        <div class="foot_wrap">
          <div class="foot_text">
            <span>© 2021 product bae-Sang-Kyu</span><br>
            <span>Proudly created with uwiv29l@naver.com</span>
          </div>
        </div>
      </div>
    </footer>
    <script type="text/javascript" src ="${path }/resources/js/index.js"></script>
    <script type="text/javascript">
    function buy_all_product(){
    	window.location.href = `${path}/members/orderform`;
    }
    function qtnChange(){
    	var ul= document.querySelector('.my_cart_list_box');
    	ul.addEventListener("click",function(evt){
    		var className=evt.target.className;
    		if(className.indexOf('plus_box')>0){
    			var count =evt.target.previousElementSibling.previousElementSibling.value;
    			count=parseInt(count);
    			count=count+1;
    			var id=evt.target.parentNode.id;
    			
    			qtnChangeAjax(count,id);
    		
    		}else if(className.indexOf("min_box")>0){
    			var count =evt.target.previousElementSibling.value;
    			count=parseInt(count);
    			count=count-1;
    			var id=evt.target.parentNode.id;
    			if(count>0){
    				qtnChangeAjax(count,id);
    			}
    		}
    	})
    }

    function qtnChangeAjax(count,id){
    	var data={
    			count:count,
    			id:id	
    	}
    		$.ajax({
    		url : "http://localhost:8080/healthproject/api/updateQtn",
    		data:JSON.stringify(data),
            dataType : "json",
    		method:"POST",
    	    contentType: "application/json; charset=utf-8",
            success : function(data) {
            	console.log(data.message);
            	if(data.message==='fali'){
            		alert('로그인이 만료되었습니다. 다시 로그인하세요');
            	}
    		}
    	})
    }
    document.addEventListener("DOMContentLoaded",function(){
    	qtnChange();
    });
    </script>
  </body>
</html>
