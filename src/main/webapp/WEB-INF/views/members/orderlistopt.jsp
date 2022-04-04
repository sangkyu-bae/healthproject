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
<link href="${path}/resources/css/orderlistopt.css" rel="stylesheet" type="text/css">

<div class="mypage_container">
	<div class="mypage_head_box">MYPAGE</div>
	<div class="mypage_filter_wrap mypage_float_box">
		<div class="mypage_fiter_head ">나의 쇼핑 활동</div>
		<div class="mypage_fiter_lst_box">
			<ul class="mypage_fiter_ul">
				<li class="mypage_fiter_list" style="font-weight: bold;"><a href="${path}/members/order_list_opt">주문 내역 조회</a></li>
				<li class="mypage_fiter_list"><a href="${path}/mypage/write_review">구매후기</a></li>
				<li class="mypage_fiter_list"><a href="#">상품문의</a></li>
			</ul>
		</div>
	</div>
	<div class="mypage_section_box  mypage_float_box">
		<div class="">
			<div class="mypage_section_head sksk">
				<h2>주문내역 조회</h2>
			</div>
			<div>
				<div class="n-radio-tab">
					<input type="radio" class="period_btn" name="radioTabGuide">
					<label> 1주일</label>
					<input type="radio" class="period_btn" name="radioTabGuide">
					<label> 1개월</label>
					<input type="radio" class="period_btn" name="radioTabGuide">
					<label> 3개월</label>
					<input type="radio" class="period_btn" name="radioTabGuide">
					<label> 전체시기</label>
				</div>
				<div class="n-radio-tab">
					<div class="n-datepicker sb">
						<input type="text" class="n-input" name="dt_fr_input" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" alt="날짜 선택" src="${path}/resources/img/common/ico_calendar.png">
					</div>
					<div class="n-datepicker sg">
						~
					</div>
					<div class="n-datepicker ">
						<input type="text" class="n-input" name="dt_to_input" value="" placeholder="-" onblur="checkDateFormat(this);">
						<img class="ui-datepicker-trigger" alt="날짜 선택" src="${path}/resources/img/common/ico_calendar.png">
					</div>
				</div>
			
			</div>
			
			
			<div class="my_order_table_box">
				<table class="my_order_table">
					<colgroup>
						<col style="width: *">
						<col style="">
						<col style="">
						<col style="">
						<col style="">
						<col style="">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">상품정보</th>
							<th scope="col">주문일자</th>
							<th scope="col">주문번호</th>
							<th scope="col">주문금액</th>
							<th scope="col" colspan="2">주문 상태</th>
						</tr>
					</thead>
					<tbody class="my_order_table_tbody">
						<c:forEach var="list" items="${orderList}"  >
							<tr>
								<td>${list.description }</td>
								<td>${list.reservationDate }</td>
								<td>${list.price }</td>
								<td>${list.price }</td>
								<td>결제대기</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
