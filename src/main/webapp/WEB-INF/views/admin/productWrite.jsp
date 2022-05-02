<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<style>

</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script type="text/javascript"  src ="${path }/resources/js/mypageCommon.js"> </script>
<link href="${path}/resources/css/orderlistopt.css" rel="stylesheet" type="text/css">

<div class="mypage_container" style="width: 100%">
	<div class="mypage_head_box">ADMIN</div>
	<div class="mypage_filter_wrap mypage_float_box">
		<div class="mypage_fiter_head ">페이지 관리</div>
		<div class="mypage_fiter_lst_box">
			<ul class="mypage_fiter_ul">
				<li class="mypage_fiter_list" ><a href="${path }/admin/main">주문 관리</a></li>
				<li class="mypage_fiter_list" style="font-weight: bold;" ><a href="${path }/admin/productWrite">상품 관리</a></li>
				<li class="mypage_fiter_list"><a href="#">상품 문의 관리</a></li>
			</ul>
		</div>
	</div>
	<div class="mypage_section_box  mypage_float_box" style="width: 1120px;">
		<div class="">
			<div class="mypage_section_head sksk">
				<h2>상품 관리</h2>
				<div class="tab-group">
					<div class="tab-btn" style="padding: 3px; ">
						<a href="#" style="color:black;">상품 추가 하기</a> 
					</div>
					<div class="tab-btn bs">
						<a href="#">이전 주문내역</a>
					</div>
				</div>
			</div>
			<div class="qna_add_form" style="height: 850px; width: 1000px;">
				<div>
					<select class="rmobile1" id="numbers_box" name="">
						<option value>선택하세요</option>
						<c:forEach var=list items="${categoryList }">
							<option value="${list.name}"></option>
						</c:forEach>
					</select>
				</div>
			<div class="review_wrap">
				<div class="qna_categor category_title">사진후기등록</div>
			</div>
			<div class="qna_box_wrap" style="height: 45%; margin-top:60px;">
				<div class="join_box">
					<label for="email" class="join_label">상품이름</label><br> <input
						id="email" class="join_input" type="text" name="email">
				</div>
				<div class="image_box">
					<img class="wthumb_img" alt="" src="" width="130px"style="display: none">
					 <input type="file" id="image" accept="image/*" name ="file"onchange="showThumbnail(this)">
				</div>
			</div>
			<div class="">
			
			</div>
	
		</div>
			</div>
		</div>
		
	
</div>

