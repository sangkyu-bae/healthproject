<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<div class="head-notice">
	<span class="header_text">15% OFF ALL TENT</span>
</div>
<nav class="fix-head">
	<div class="container">
		<div class="headcont">
			<a href="${path }">MADAGASCAR</a>
		</div>
		<div class="navbar-nav">
			<ul class="navbar_ul">
				<li class="ul-list"><a href="/healthproject" class="nav-li">HOME</a></li>
				<li class="ul-list"><a href="/healthproject/shop" class="nav-li">SHOP</a></li>

				<sec:authorize access="!isAuthenticated()">
					<li class="ul-list"><a href="/healthproject/members/loginform" class="nav-li">LOGIN</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="ul-list"><a href="logout" class="nav-li">Logout</a></li>
				</sec:authorize>

				<sec:authorize access="!isAuthenticated()">
					<li class="ul-list"><a href="/healthproject/members/joinform" class="nav-li">JOIN</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="ul-list"><a href="/healthproject/members/mypage" class="nav-li">MYPAGE</a></li>
				</sec:authorize>

				<li class="ul-list"><a href="/" class="nav-li">CONTACT</a></li>
				<li class="ul-list"><a href="/healthproject/members/mycart" class="nav-li">MYCART</a></li>
			</ul>

		</div>
	</div>
</nav>
<script>
	function pasedUrls(){
		const parsedUrl = new URL(window.location.href);
		var pathName=parsedUrl.pathname;
		return pathName;
	}
	function changeNavBold(){
		var path=pasedUrls();
		var navList=document.querySelectorAll('.nav-li');
		if(path.indexOf('index')>-1){
			navList[0].classList.add('chcke');
		}else if(path.indexOf('orderform')>-1){
			navList[5].classList.add('chcke');
		}else if(path.indexOf('product')>-1){
			navList[1].classList.add('chcke');
		}else{
			for(var i=0;i<navList.length;i++){
				var href=navList[i].href;
				if(href.indexOf(path)>-1){
					navList[i].classList.add('chcke');
					break;
				}
			}
		}
	}
	changeNavBold();
</script>