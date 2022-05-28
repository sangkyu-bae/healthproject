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
				<sec:authorize access="hasRole('ADMIN')">
				  <li class="ul-list"><a href="${path }/admin/main" id='adimin' class="nav-li">ADMIN</a></li>
				</sec:authorize>
				<li class="ul-list"><a href="/healthproject" id="home" class="nav-li">HOME</a></li>
				<li class="ul-list"><a href="/healthproject/shop" id="shop" class="nav-li">SHOP</a></li>

				<sec:authorize access="!isAuthenticated()">
					<li class="ul-list"><a href="/healthproject/members/loginform" id='login' class="nav-li">LOGIN</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="ul-list"><a href="${path }/logout" id="logout"class="nav-li">Logout</a></li>
				</sec:authorize>

				<sec:authorize access="!isAuthenticated()">
					<li class="ul-list"><a href="/healthproject/members/joinform" id="join" class="nav-li">JOIN</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="ul-list"><a href="/healthproject/members/mypage" id="mypage"class="nav-li">MYPAGE</a></li>
				</sec:authorize>

				<li class="ul-list"><a href="${path }" id='contact'class="nav-li">CONTACT</a></li>
				<li class="ul-list"><a href="${path}/members/mycart" id='mycart' class="nav-li">MYCART</a></li>
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
		console.log(path);
		var navList=document.querySelectorAll('.nav-li');
		
		let checkBold;
		
		/* navList.forEach((item)=>{
			if(path.indexOf('index')>-1&&item.id=='home') {
				checkBold=item;
				return false;
			}else if(path.indexOf('admin')>-1&& item.id=='adimin') {
				checkBold=item;
				console.log(item);
				return false;
			}else if(path.indexOf('orderform')>-1 && item.id=='mycart'){
				checkBold=item;
				return false;
			}else if(path.indexOf('product')>-1 && item.id=='shop'){
				checkBold=item;
				return false;
			}else if((path.indexOf('order_list_opt')>-1||path.indexOf('write_review')>-1||path.indexOf('review')>-1||path.indexOf('qa')>-1)&& item.id=='mypage'){
				checkBold=item;
				return false;
			}
			else{
				for(var i=0;i<navList.length;i++){
					var href=navList[i].href;
					if(href.indexOf(path)>-1){
						navList[i].classList.add('chcke');
						return false;
					}
				}
			}	
		})
		 */
/* 		console.log(checkBold)
		checkBold.classList.add('chcke'); */
	}
	
	document.addEventListener("DOMContentLoaded",function(){
		changeNavBold();
	})
	
</script>