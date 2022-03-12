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
             <li class="ul-list"><a href="members/loginform" class="nav-li">LOGIN</a></li>
             <li class="ul-list"><a href="#" class="nav-li chcke">JOIN</a></li>
             <li class="ul-list"><a href="#" class="nav-li">CONTACT</a></li>
               -->
           
             
              <li class="ul-list"><a href="/healthproject/index" class="nav-li ">HOME</a></li>
              <li class="ul-list"><a href="/healthproject/shop" class="nav-li">SHOP</a></li>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/loginform" class="nav-li">LOGIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="logout" class="nav-li">Logout</a></li>
              </sec:authorize>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/joinform" class="nav-li chcke">JOIN</a></li>
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
      <div class="join_form_box">
        <div class="join_form_container">
          <form method="post" action="/healthproject/members/join" id="join_sub">
            <div class="join_box">
              <label for="email" class="join_label">이메일</label><br>
              <input id="email" class="join_input" type="text" name="email">
            </div>
            <div class="join_box">
              <label for="password"class="join_label">비밀번호</label><br>
              <input id="password"class="join_input" type="password" name="password">
            </div>
            <div class="join_box">
              <label for="password2"class="join_label">비밀번호 재확인</label><br>
              <input id="password2"class="join_input" type="password">
            </div>
            <div class="join_box">
              <label for="name"class="join_label">이름</label><br>
              <input id="name"class="join_input" type="text" name="name">
            </div>
            <div class="join_box">
              <label for="phone"class="join_label">핸드폰 번호</label><br>
              <input id="phone"class="join_input" type="text" name ="phone">
            </div>
            <div class="join_box">
              <label for="address" class="join_label">주소</label><br>
              <input id="address" class="join_input" type="text" name ="address">
            </div>
            <div>
              <label></label>
              <button type="button" onclick="checkSubmit();" class="join_submit">회원가입</button>
            </div>
          </form>
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
