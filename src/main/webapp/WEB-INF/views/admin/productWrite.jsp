<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<style>
.rmobile1{
    width: 150px;
    height: 30px;
    font-size: 15px
}
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
				<li class="mypage_fiter_list" style="font-weight: bold;" ><a href="${path}/admin/productWrite">상품 관리</a></li>
				<li class="mypage_fiter_list"><a href="${path }/admin/selectQuestion">상품 문의 관리</a></li>
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
						<a href="${path }/admin/administerPromotion">행사 상품 관리</a>
					</div>
				</div>
			</div>
			<div class="qna_add_form" style="height: 850px; width: 1000px;">
			<div class="review_wrap">
				<div class="qna_categor category_title">상품등록하기</div>
			</div>
			<form id="frm" method="post" enctype="multipart/form-data" action="${path }/admin/addProduct" >
				<div class="qna_box_wrap" style="height: 45%; margin-top:60px;">
					<div>
						<label for="categoryId" class="join_label">카테고리</label><br> 
						<div style="margin-top:22px;">
							<select class="rmobile1" id="numbers_box" name="categoryId">
								<option value>카테고리선택</option>
								<c:forEach var="list" items="${categoryList}">
									<option value="${list.id}">${list.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="join_box">
						<label for="description" class="join_label">상품이름</label><br>
						 <input name="description" id="description" class="join_input" type="text">
					</div>
					<div class="join_box">
						<label for="content" class="join_label">상품설명</label><br> 
						<input name="content"id="content" class="join_input" type="text">
					</div>
					<div class="join_box">
						<label for="price" class="join_label">가격</label><br> 
						<input name="price"id="price" class="join_input" min="99" type="number" >
					</div>
					<div class="image_box" style="margin-top: 35px;">
						<img class="wthumb_img" alt="" src="" width="130px"style="display: none">
						<input type="file" id="image" accept="image/*" name ="file"onchange="showThumbnail(this)">
					</div>
				</div>
				<div class="order_submit_btn_box" style="margin-top: 150px;">
					<button type="button" onclick="frmCheck()" class="order_btn" name="button">상품등록</button>
				</div>
			</form>	
			</div>
		</div>
	</div>
</div>


<script>

function noclick(){
	var label=document.querySelectorAll('label');
	
	label.forEach(ele=>{
			ele.classList.remove("clicks");
	})
}

function frmCheck(){
	var frm=document.querySelector("#frm");
	
	var category=document.querySelector("#numbers_box");
	var description=document.querySelector("#description");
	var content=document.querySelector("#content");
	var price=document.querySelector("#price");
	var file=document.querySelector("#image");
	
	
	if(category.value==''){
		alert("카테고리를 입력하세요");
		category.focus();
		return false;
		
	}
	if(description.value.length<5){
		description.focus();
		alert("상품 이름을 등록하세요");
		return false
	}
	if(content.value.length<5){
		content.focus();
		alert("상품 설명을 5자이상 등록하세요");
		return false;
	}
	if(price.value.length<2){
		price.focus();
		alert("상품 가격을 설정하세요")
		return false;
	}
	if(file.value==''){
		alert("상품 사진을 등록하세요");
		file.focus();
		return false;
	}
	
	frm.submit();
}

document.addEventListener("DOMContentLoaded",function(){
	noclick();
})

	
</script>
