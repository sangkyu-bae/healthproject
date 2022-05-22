<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<c:set var="path" value="${pageContext.request.contextPath}" />
<form id="frm" method="post" action="${path }/admin/addQustionAnwser">
<input type="hidden" value="${selectOneQuestion.qusetionId }" name="productQuestionId">
<div class="order_form_container">
	<div class="order_wraps">상품문의등록</div>
	<div class="order_form_head_box">
		<h2>상품문의등록</h2>
	</div>
	<div class="order_section_box section_one">
		<div class="order_section_head">
			<h3>question Info</h3>
			<span>문의 정보</span>
		</div>
		<div class="order_form_wrap">
			<div class="order_ul_box">
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">문의상품정보</li>
					<li class="cell_discount_detail">${selectOneQuestion.description }</li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">문의자이름</li>
					<li class="cell_discount_detail">${selectOneQuestion.name}</li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">문의카테고리</li>
					<li class="cell_discount_detail">${selectOneQuestion.titleCategory }</li>
				</ul>
					<ul class="box_receiver_info">
					<li class="cell_discount_tit">문의내용</li>
					<li class="cell_discount_detail">${selectOneQuestion.text }</li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">문의일자</li>
					<li class="cell_discount_detail">${selectOneQuestion.createDate }</li>
				</ul>
				
			</div>
		</div>
	</div>
	<div class="order_section_box">
		<div class="order_section_head">
			<h3>question Anwser</h3>
			<span class="pr_info_s">문의 답변</span>
		</div>
		<div class="order_pr_table_wrap">
			<ul class="box_receiver_info">
					<li class="cell_discount_tit">답변내용</li>
					<li class="cell_discount_detail">
						<div class="add_box">
						<textarea id="text" name="text" placeholder="5자이상 등록하세요" rows="10" cols="70"></textarea>
						</div>
					</li>
					</form>
				</ul>
		</div>
	</div>
	<div class="order_submit_btn_box">
		<button type="button" class="order_btn" name="button" onclick="checkFormToSubmit()">답변 등록</button>
	</div>
</div>
<div class="popup" style="display: none;">
		<div class="popup_layer">
			<div class="text_area">
				<strong class="login title">상품 문의 답변</strong>
				<p class="text">
					문의에 대한 답변을 <br>등록 하시겠습니까?
				</p>
			</div>
			<div class="btn_area">
				<button type="button" name="button" onclick="addQuestionAnwser();"class="btn yes">예</button>
				<button type="button" name="button" onclick="displayNone();" class="btn no">아니오</button>
			</div>
		</div>
		<div class="popup_dimmed"></div>
	</div>
<script>
	 const checkFormToSubmit=()=>{
		 const text=document.querySelector("#text");

		 if(text.value.length<6){
			 alert("답변 내용을 등록해 주세요");
			 text.focus();
			 return false;
		 }
		 
		 const popup=document.querySelector(".popup");
		 popup.style.display='block';

	 }
	 const addQuestionAnwser=()=>{
		 const frm=document.querySelector("#frm");
		 frm.submit();
	 }
</script>

<script type="text/javascript" src ="${path }/resources/js/adminCommon.js"></script>