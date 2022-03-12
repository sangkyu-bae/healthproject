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
            <!--
            	<li class="ul-list"><a href="/healthproject/index" class="nav-li">HOME</a></li>
              <li class="ul-list"><a href="#" class="nav-li">SHOP</a></li>
              <li class="ul-list"><a href="#" class="nav-li chcke">LOGIN</a></li>
              <li class="ul-list"><a href="members/joinform" class="nav-li">JOIN</a></li>
              <li class="ul-list"><a href="#" class="nav-li">CONTACT</a></li>
              
              -->
              
              <li class="ul-list"><a href="/healthproject/index" class="nav-li ">HOME</a></li>
              <li class="ul-list"><a href="/healthproject/shop" class="nav-li">SHOP</a></li>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/loginform" class="nav-li chcke">LOGIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="logout" class="nav-li">Logout</a></li>
              </sec:authorize>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="http://localhost:8080/healthproject/members/joinform" class="nav-li">JOIN</a></li>
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
      <div class="login_wrap">
       <div class="login_container">
         <div class="login_form">
           <form method="post" id="login_forms" action="/healthproject/authenticate">
             <div class="login_box id_box">
               <input type="text" class="form_box" id="login_id" placeholder="아이디" name="userId">
             </div>
             <div class="login_box">
               <input type="password" class="form_box" id="login_password" placeholder="비밀번호" name="password">
             </div>
             <div class="submit_box">
               <input type="submit"class="form_box sub_from" value="로그인">
             </div>
           </form>
         </div>
       </div>
       <div class="login_info">
         <div class="login_info_wrap">
           <ul class="ul_box">
             <li class="info_list"><span>비밀번호 찾기</span></li>
             <li class="info_list"><span>아이디 찾기</span></li>
             <li class="info_list"><span>회원가입</span></li>
           </ul>
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
