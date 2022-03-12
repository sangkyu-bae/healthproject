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
            <a href="#">MADAGASCAR</a>
          </div>
          <div class="navbar-nav">
            <ul class="navbar_ul">
              <li class="ul-list"><a href="#" class="nav-li chcke">HOME</a></li>
              <li class="ul-list"><a href="shop" class="nav-li">SHOP</a></li>
              
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
    <section>
      <div class="section-container">
        <div class="event_lst_box">
          <div class="notice_file box_item">
            <div class="main_text_box">
              <span>GET READY FOR</span><br>
              <span> THE BIG CHILL</span>
            </div>
            <div class="sub_text_box">
              <span>큰추위에 대비하여 보세요</span>
            </div>
            <button type="button" name="button"class="first_btn" >
              <div class="btn-box">
                Shop Now
              </div>
            </button>
          </div>
          <div class="warpper box_item">
            <div class="slider-wrap">
              <ul class="item">
              <!--  
                <li class="item_list">
                  <a href="">
                    <img src="http://localhost:8080/healthproject/resources/img/tent.png" alt="">
                  </a>   
                </li>
                <li class="item_list">
                  <a href="">
                    <img src="http://localhost:8080/healthproject/resources/img/tent5.jpg" alt="">
                  </a>
                </li>
                -->
              </ul>
               <div class="promotion_box">
                Promotion-Product
              </div>
              <div class="click_clor left_click">
                 <
              </div>
              <div class="click_clor right_click">
                 >
              </div>
            </div>
          </div>
        </div>
        <div class="top-section">
          <div class="text-title-box">
            <span class="text-span">자연과</span><br>
            <span class="text-span">하나가 되다</span>
          </div>
          <div class="text-sub-box">
            BECOME ONE WITH NATURE
          </div>
          <button type="button" name="button"class="button-box" >
            <div class="btn-box">
              Shop Our Range
            </div>
          </button>
        </div>
      </div>
      <div class="prodcut_container">
        <div class="product_section">
          <div class="product_introduce">
            OUR BEST-SELLING EQUIPMENT
          </div>
          <div class="prduct_img_container">
            <ul class="product_list">
              <div class="product_item_box">
              <!--  
                <li class="product_item">
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
                <li class="product_item">
                  <a href="#">
                    <img src="http://localhost:8080/healthproject/resources/img/product_2.png" alt="">
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
                    9,000원
                  </div>
                </li>
                <li class="product_item">
                  <a href="#">
                    <img src="http://localhost:8080/healthproject/resources/img/product_3.png" alt="">
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
                    280,000원
                  </div>
                </li>
                <li class="product_item">
                  <a href="#">
                    <img src="http://localhost:8080/healthproject/resources/img/product_4.png" alt="">
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
                    28,000원
                  </div>
                </li>
                -->
               </div>    
            </ul>
            <div class="btn_box">
              add view
              <div class="dimed"></div>
            </div>
          </div>
        </div>
        
      	</div>
         <div class="follow_wrap">
          <div class="follow_text">
            FOLLOW US #MADAGASCAR
          </div>
          <div class="follow_list_wrap">
            <ul class="follow_list">
              <li class="follow_item">
                <a href="#">
                  <img src="http://localhost:8080/healthproject/resources/img/follow/01.jpg" alt="">
                </a>
                <div class="dimed_text">
                  skjafkdj
                </div>
              </li>
              <li class="follow_item">
                <a href="#">
                  <img src="http://localhost:8080/healthproject/resources/img/follow/02.jpg" alt="">
                </a>
                <div class="dimed_text">
                  skjafkdj
                </div>
              </li>
              <li class="follow_item">
                <a href="#">
                  <img src="http://localhost:8080/healthproject/resources/img/follow/03.jpg" alt="">
                </a>
                <div class="dimed_text">
                  skjafkdj
                </div>
              </li>
              <li class="follow_item">
                <a href="#">
                  <img src="http://localhost:8080/healthproject/resources/img/follow/04.jpg" alt="">
                </a>
                <div class="dimed_text">
                  skjafkdj
                </div>
              </li>
            </ul>
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
