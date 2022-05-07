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

.product_img_ul::after {
	display: block;
	content: '';
	clear: both;
}

</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<c:set var="path" value="${pageContext.request.contextPath}" />

<link href="${path}/resources/css/orderlistopt.css" rel="stylesheet" type="text/css">

<div class="mypage_container" style="width: 100%">
	<div class="mypage_head_box">ADMIN</div>
	<div class="mypage_filter_wrap mypage_float_box">
		<div class="mypage_fiter_head ">페이지 관리</div>
		<div class="mypage_fiter_lst_box">
			<ul class="mypage_fiter_ul">
				<li class="mypage_fiter_list" ><a href="${path }/admin/main">주문 관리</a></li>
				<li class="mypage_fiter_list" style="font-weight: bold;" ><a href="${path}/admin/productWrite">상품 관리</a></li>
				<li class="mypage_fiter_list"><a href="#">상품 문의 관리</a></li>
			</ul>
		</div>
	</div>
	<div class="mypage_section_box  mypage_float_box" style="/*width: 1120px;*/">
		
			<div class="mypage_section_head sksk">
				<h2>상품 관리</h2>
				<div class="tab-group">
					<div class="tab-btn" style="padding: 3px; ">
						<a href="${path}/admin/productWrite">상품 추가 하기</a> 
					</div>
					<div class="tab-btn bs">
						<a href="#"  style="color:black;">행사 상품 관리</a>
					</div>
				</div>
			</div>
			<div class="qna_add_form" style="height: 1200px; width: 1000px;">
			<div class="review_wrap">
				<div class="qna_categor category_title">프로모션등록하기</div>
			</div>
			<form id="frm" method="post" enctype="multipart/form-data" action="${path }/admin/addProduct" >
				<div class="qna_box_wrap" style="/*height: 45%;*/ margin-top:60px;">
					<div>
						<label for="categoryId" class="join_label">카테고리</label><br> 
						<div style="margin-top:22px;">
							<select  onchange="getCategroyProduct(this)" class="rmobile1" id="numbers_box" name="categoryId">
								<option value=0>카테고리선택</option>
								<c:forEach var="list" items="${categoryList}">
									<option value="${list.id}">${list.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<ul class="product_img_ul">
						<c:forEach items="${ categoryProduct}" var="list">
							<li class="product_img_item">
			                  <a href="#">
			                    <img src="${path }/resources/img/${list.saveFileName}" alt="${list.description }">
			                  </a>
			                  <div class="product_view" style="display:block; width:270px;">
			                    <a href="#">
			                        선택하기
			                    </a>
			                  </div>
			                  <div class="product_text">
			                   	${list.description}
			                  </div>
			                  <div class="product_price">
			                    ${list.price }원
			                  </div>
			                </li>
						</c:forEach>	

					</ul>
					<!-- 
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
					 -->
				</div>
				<div class="order_submit_btn_box" style="/*margin-top: 150px;padding-right: 284px;*/">
					<button type="button" onclick="" class="order_btn" name="button">프로모션 등록</button>
				</div>
			</form>	
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

/*select 옵션 변할때 마다 카테고리별 상품 ajax로 가져오기*/
const getCategroyProduct= (element) =>{
	console.log(element.value);

	const url =`${path}/api/getCategoryProduct?categoryId=`+element.value;
	$.ajax({
			url : url,
	        dataType : "json",
			method:"GET",
		    contentType: "application/json; charset=utf-8",
	        success : function(data) {
	        	setProductImage(data);
			}
		})
}
/*
const setProductImage=(data)=>{
	const product=data['categoryProduct'];
	var html=``;
	
	product.forEach(ele=>{
		html+=`		<li class="product_img_item">
            <a href="#">
            <img src="${path }/resources/img/${ele.saveFileName}" alt="${ele.description }">
          </a>
          <div class="product_view" style="display:block; width:270px;">
            <a href="#">
                선택하기
            </a>
          </div>
          <div class="product_text">
           	${ele.description}
          </div>
          <div class="product_price">
            ${ele.price }원
          </div>
        </li>`
	});
	console.log(html);
	
	$('.product_img_ul').empty();
	$('.product_img_ul').append(html);
}
*/

document.addEventListener("DOMContentLoaded",function(){
	noclick();
})
</script>