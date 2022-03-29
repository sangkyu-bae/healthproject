<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script>
	var path=`${path}`;
</script>
<div class="mypage_container">
	<div class="mypage_head_box">MYPAGE</div>
	<div class="mypage_filter_wrap mypage_float_box">
		<div class="mypage_fiter_head">나의 쇼핑 활동</div>
		<div class="mypage_fiter_lst_box">
			<ul class="mypage_fiter_ul">
				<li class="mypage_fiter_list"><a href="${path}/members/order_list_opt">주문 내역 조회</a></li>
				<li class="mypage_fiter_list"><a href="#">구매후기</a></li>
				<li class="mypage_fiter_list"><a href="#">상품문의</a></li>
			</ul>
		</div>
	</div>
	<div class="mypage_section_box  mypage_float_box">
		<div class="">
			<div class="mypage_section_head">
				<h2>주문내역 조회</h2>
			</div>
			
			 
			<nav class="my_order_list_box">
				<ul class="my_order_ul">
					<li class="my_order_list" data-category="1">입금/결제 <span>0</span></li>
					<li class="my_order_list" data-category="2">배송중/픽업대기 <span>0</span></li>
					<li class="my_order_list" data-category="3">배송완료/픽업완료 <span>0</span></li>
					<li class="my_order_list" data-category="4">구매확정 <span>0</span></li>
					<li class="my_order_list" data-category="5">취소 <span>0</span></li>
				</ul>
			</nav>
		
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
						<tr>
							<td>에버캠프 시그니처 스카이뷰</td>
							<td>2022.01.21</td>
							<td>1</td>
							<td>1,312,0000</td>
							<td>결제대기</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="table_box">
			<div class="mypage_section_head">
				<h2>상품문의</h2>
			</div>
			<div class="my_product_qna_table_box">
				<table class="my_product_qna_table">
					<colgroup>
						<col style="width: 26%">
						<col style="width: *">
						<col style="width: 12.6%">
						<col style="width: 12.6%">
						<col style="width: 12.6%">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">상품정보</th>
							<th scope="col">내용</th>
							<th scope="col">문의 유형</th>
							<th scope="col">작성일</th>
							<th scope="col">처리 상태</th>
						</tr>
					</thead>
					<tbody class="my_product_qna_table_tbody">
						<tr>
							<td>에버캠프 시그니처 스카이</td>
							<td>배송문의요~</td>
							<td>배송</td>
							<td>2022.01.21</td>
							<td>답변완료</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="table_box">
			<div class="mypage_section_head">
				<h2>구매후기</h2>
			</div>
			<div class="my_buy_product">
				<table class="my_buy_product_table">
					<colgroup>
						<col style="width: 35%">
						<col style="width: 35%">
						<col style="width: *">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">상품정보</th>
							<th scope="col">내용</th>
							<th scope="col">평점</th>
						</tr>
					</thead>
					<tbody class="my_buy_product_table_tbody">
						<tr>
							<td>무인양품 발수 후드 재킷</td>
							<td>아주 좋은 상품입니다.</td>
							<td>3</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
