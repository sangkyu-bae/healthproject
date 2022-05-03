<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
				<li class="mypage_fiter_list"style="font-weight: bold;" ><a href="${path }/admin/main">주문 관리</a></li>
				<li class="mypage_fiter_list" ><a href="${path }/admin/productWrite">상품 관리</a></li>
				<li class="mypage_fiter_list"><a href="#">상품 문의 관리</a></li>
			</ul>
		</div>
	</div>
	<div class="mypage_section_box  mypage_float_box" style="width: 1120px;">
		<div class="">
			<div class="mypage_section_head sksk">
				<h2>주문 관리</h2>
				<div class="tab-group">
					<div class="tab-btn" style="padding: 3px; ">
						<a href="#" style="color:black;">현재 상품 주문내역</a> 
					</div>
					<div class="tab-btn bs">
						<a href="${path }/admin/pastOrderList">이전 주문내역</a>
					</div>
				</div>
			</div>
				
			<div class="my_order_table_box" style="margin-top: 65px;">
				<table class="my_order_table">
					<colgroup>
						<col style="width: 17%">
						<col style="width:23%">
						<col style="">
						<col style="">
						<col style="">
						<col style="">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">주문상품정보</th>
							<th scope="col">주문자주소</th>
							<th scope="col">주문자이름</th>
							<th scope="col">주문수량</th>
							<th scope="col">주문일자</th>
							<th scope="col">주문금액</th>
						</tr>
					</thead>
					<tbody class="my_order_table_tbody">
					<c:choose>
						<c:when test="${empty orderList }">
							</tbody>
								</table>
								<p class="n-table-none">
									<sapn>주문 내역이 없습니다 </sapn>
								</p>
						</c:when>
						<c:otherwise>
							<c:forEach var ="list" items="${orderList}" >
								<tr>
									<td  style="text-overflow: ellipsis; overflow: hidden;white-space: nowrap;">${list.description}</td>
									<td style="text-overflow: ellipsis; overflow: hidden;white-space: nowrap;">${list.orderAddress }</td>
									<td>${list.recipient }</td>
									<td>${list.count }</td>
									<td>${list.createDate }</td>
									<td>${list.updatetotalprice }</td>		
								</tr>
									
							</c:forEach>
							
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
						
						
					
						
				
			</div>
		</div>
	</div>
	<div>
		<div>
		
		</div>
	</div>
</div>
