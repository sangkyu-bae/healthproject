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
<script type="text/javascript"  src ="${path }/resources/js/mypageCommon.js"> </script>
<link href="${path}/resources/css/orderlistopt.css" rel="stylesheet" type="text/css">

<div class="mypage_container">
	<div class="mypage_head_box">MYPAGE</div>
	<div class="mypage_filter_wrap mypage_float_box">
		<div class="mypage_fiter_head ">나의 쇼핑 활동</div>
		<div class="mypage_fiter_lst_box">
			<ul class="mypage_fiter_ul">
				<li class="mypage_fiter_list" style="font-weight: bold;"><a href="${path}/members/order_list_opt">주문 내역 조회</a></li>
				<li class="mypage_fiter_list"><a href="${path}/mypage/write_review">구매후기</a></li>
				<li class="mypage_fiter_list"><a href="${path }/mypage/qa">상품문의</a></li>
			</ul>
		</div>
	</div>
	<div class="mypage_section_box  mypage_float_box">
		<div class="">
			<div class="mypage_section_head sksk">
				<h2>주문내역 조회</h2>
			</div>
			<div class="n-table-filter">
				<div class="n-radio-tab">
					<input type="radio" onclick="setPriod('1week')" class="period_btn" id="radioTabGuide0" name="radioTabGuide">
					<label  for="radioTabGuide0" onclick="setBorder(this)"> 1주일</label>
					<input onclick="setPriod('1month')" type="radio" class="period_btn"  id="radioTabGuide1" name="radioTabGuide">
					<label  for="radioTabGuide1" onclick="setBorder(this)"> 1개월</label>
					<input type="radio" onclick="setPriod('3month')" class="period_btn" id="radioTabGuide2" name="radioTabGuide">
					<label for="radioTabGuide2" onclick="setBorder(this)"> 3개월</label>
					<input type="radio" class="period_btn" onclick="setPriod('all')" id="radioTabGuide3" name="radioTabGuide">
					<label for="radioTabGuide3" onclick="setBorder(this)" > 전체시기</label>
				</div>
				<form id="frm" action="${path }/members/order_list_opt">
					<div class="n-radio-tab n-right">
						<div class="n-datepicker sb">
							<input type="text" id="starts"class="n-input" name="startDate" value="" placeholder="-">
								<img class="ui-datepicker-trigger" alt="날짜 선택" src="${path}/resources/img/common/ico_calendar.png">
							</div>
							<div class="n-datepicker sg">
								~
							</div>
							<div class="n-datepicker ">
								<input type="text" id="lasts" class="n-input" name="lastDate" value="" placeholder="-">
								<img class="ui-datepicker-trigger" alt="날짜 선택" src="${path}/resources/img/common/ico_calendar.png">
							</div>
							<input type="hidden" id="period" name="period">
					</div>
					<button type="button" class="n-btn btn-sm btn-accent" onclick="search()">조회</button>
				</form>
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
							<th scope="col" colspan="2">주문상태</th>
						</tr>
					</thead>
					<tbody class="my_order_table_tbody">
					<c:choose>
						<c:when test="${empty orderList }">
								</tbody>
							</table>
							<p class="n-table-none">
								<sapn>등록된 주문이 없습니다. </sapn>
							</p>
						</c:when>
						<c:otherwise>
						<c:forEach var="list" items="${orderList}" varStatus="status" >
							<tr>
								<td>${list.description }</td>
								<td>${list.reservationDate }</td>
								<td>${status.count }</td>
								<td>${list.resultprice }</td>
								<td>${list.namestring}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
						</c:otherwise>
					</c:choose>
						
			</div>
		</div>
	</div>
</div>

