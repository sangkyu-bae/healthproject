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
    <title>캠핑모아 MADAGASCAR 상품</title>
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
              <li class="ul-list"><a href="index" class="nav-li">HOME</a></li>
              <li class="ul-list"><a href="#" class="nav-li chcke">SHOP</a></li>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/loginform" class="nav-li">LOGIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="logout" class="nav-li">Logout</a></li>
              </sec:authorize>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/joinform" class="nav-li">JOIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="members/mypage" class="nav-li">MYPAGE</a></li>
              </sec:authorize>
              
              <li class="ul-list"><a href="#" class="nav-li">CONTACT</a></li>
              <li class="ul-list"><a href="members/mycart" class="nav-li">MYCART</a></li>
             </ul>
           </div>
         </div>
       </nav>
    </header>
    <section class="product_section">
      <div class="product_containers">
        <div class="product_wrap">
          <div class="product_head">
              ShOP
          </div>
          <div class="product_img_box">
            <div class="filter_wrap float_box">
              <div class="filter_head">
                Filter by
              </div>
              <div class="filter_list_wrap">
                <ul class="filter_list">
                  <li class="filter_item" data-category="0"><a class="filter_link click_item">All</a></li>
                  <li class="filter_item" data-category="3"><a class="filter_link">Rain Coats</a></li>
                  <li class="filter_item" data-category="2"><a class="filter_link">Sleeping Bags</a></li>
                  <li class="filter_item" data-category="1"><a class="filter_link">Tent Shop</a></li>
                </ul>
              </div>
            </div>
            <div class="product_img_wrap float_box">
              <ul class="product_img_ul">
              <!-- 
                <li class="product_img_item">
                  <a href="#">
                    <img src="http://localhost:8080/healthproject/resources/img/product_1.png" alt="">
                  </a>
                  <div class="product_view">
                    <a href="#">
                        Qucik View
                    </a>
                  </div>
                  <div class="product_text">
                    I'm a product
                  </div>
                  <div class="product_price">
                    72,000원
                  </div>
                </li>
                 -->
              </ul>
            </div>
            <div class="select_box">
              <select id="select-lang" onchange="sortProduct();" name="">
                <option selected value="0">Sort By</option>
                <option value="price-low">Price(low to high)</option>
                <option value="price-high">price(high to low)</option>
                <option value="name-low">Name A-Z</option>
                <option value="name-high">Name Z-A</option>
              </select>
            </div>
          </div>
        </div>
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
  </body>
</html>