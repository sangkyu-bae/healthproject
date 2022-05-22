<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src ="${path }/resources/js/adminCommon.js"></script>
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
.wrapper{
	
}

.paging-btn{
	box-sizing: border-box;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    width: 33px;
    height: 41px;
    line-height: 40px;
    border: 1px solid #ddd;
    border-left: none;
    float: left;
    text-align: center;
    color: #b2b2b2;
}
.first:before {
	content: "<<";
	text-align: center;
}
.prev:before{
	content:"<";
}
.next:before {
	content: ">";
}
.last:before {
	content:">>";
}
.box{
	height: 42px;
	width: 100%;
}
.wrapper{
	width:40%;
	height:42px;
	margin: 0 auto;
}
.nav-left{
	float: left;
}
.nav_box:after {
	display: block;
	content:'';
	clear: both;
}
.product-box{
	margin-top:6px;
    min-width: 65px;
    border: 1px solid #f1f1f1;
    color: #777777;
    font-size: 14px;
    line-height: 30px;
    float: left;
    margin-right: -1px;
    text-align: center;
    vertical-align: top;
 }
 .chooes{
 	background-color: rgba(0,0,0,0.3);
 }
 .nav-left{
 	margin-right: 25px;
 }
 #discountRate{
    width: 74px;
    height: 39px;
    font-size: 16px;
    text-align: center;
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
				<li class="mypage_fiter_list"><a href="${path }/admin/selectQuestion">상품 문의 관리</a></li>
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
			<div class="qna_add_form" style="height: 1350px; width: 1000px;">
			<div class="review_wrap">
				<div class="qna_categor category_title">프로모션등록하기</div>
			</div>
			<form id="frm" method="post" action="${path }/admin/addPromotion" >
				<div class="qna_box_wrap" style="/*height: 45%;*/ margin-top:60px;">
					<div class="nav_box">
						<div class="nav-left">
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
						<div class="nav-left">
							<label for="categoryId" class="join_label">선택된 상품 개수</label><br> 
							<div  class = "product-box">
								<span class="total">${paging.total}/</span><span class="total-count">0</span>
							</div>
						</div>
						<div class="nav-left">
							<label for="discountRate" class="join_label">할인율</label><br> 
							<div  class = "product-box">
								<input type="number" min="0" max="100"name="discountRate" id="discountRate" class="promtion_box" placeholder="%">
							</div>
						</div>
					</div>
					<ul class="product_img_ul">
						<c:forEach items="${ viewAll}" var="list">
							<li class="product_img_item">
			                  <a href="#">
			                    <img src="${path }/resources/img/${list.saveFileName}" alt="${list.description }">
			                  </a>
			                  <div class="product_view" id="${list.productId }" onclick="setSelectProduct(this);" style="display:block; width:270px;">
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
				</div>
				<div class="box">
					<div class="wrapper">
						<c:if test="${paging.endPage != 1 && paging.nowPage != 1}">
							<a href="${path }/admin/administerPromotion?nowPage=${paging.nowPage-1 }&cntPerPage=${paging.cntPerPage}" class="paging-btn prev"></a> 
						</c:if>
						<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
							<c:choose>
								<c:when test="${p == paging.nowPage }">
									<b class="paging-btn">${p }</b>
								</c:when>
								<c:when test="${p != paging.nowPage }">
									<a class="paging-btn" href="${path }/admin/administerPromotion?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p }</a>
								</c:when>
							</c:choose>
							
						</c:forEach>
						<c:if test="${paging.endPage != 1 && paging.nowPage != paging.lastPage}">
								<a href="${path }/admin/administerPromotion?nowPage=${paging.nowPage+1 }&cntPerPage=${paging.cntPerPage}" class="paging-btn next"></a> 
							</c:if>
						<!--
						<a class="paging-btn">1</a>
						  
						<a class="paging-btn first"></a>
						<a class="paging-btn prev"></a> 
						<a class="paging-btn">1</a>
						<a class="paging-btn">1</a>
						<a class="paging-btn">1</a>
						<a class="paging-btn">1</a>
						<a class="paging-btn">1</a>
						<a class="paging-btn">1</a>
						<a class="paging-btn">1</a>
						<a class="paging-btn">1</a>
						<a class="paging-btn next"></a>
						<a class="paging-btn last"></a>
						-->
					</div>
				</div>
				
				
				<div class="order_submit_btn_box" style="margin-top: 100px;/*padding-right: 284px;*/">
					<button type="button" onclick="displayPopup()" class="order_btn" name="button">프로모션 등록</button>
				</div>
			</form>	
			</div>
	</div>
</div>
	<div class="popup" style="display: none;">
		<div class="popup_layer">
			<div class="text_area">
				<strong class="login title">팝업 타이틀</strong>
				<p class="text">
					상품을 장바구니에 <br>등록 하시겠습니까?
				</p>
			</div>
			<div class="btn_area">
				<button type="button" name="button"  onclick="setNumberBox();"class="btn yes">예</button>
				<button type="button" name="button" onclick="displayNone();" class="btn no">아니오</button>
			</div>
		</div>
		<div class="popup_dimmed"></div>
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
	        	setUlHegith();
			}
		})
}

const setUlHegith=()=>{
	const li = document.querySelectorAll('.product_img_item');
	const ul = document.querySelector('.product_img_ul');
	if(li.length<4){
		ul.style.height='482px';
	}else{
		ul.style.height='882px';
	}
}

const setNavWidth=()=>{
	const wrapper=document.querySelector('.wrapper');
	const wrapperChildCount=wrapper.childElementCount;
	
	var elementWidth=wrapper.firstElementChild.offsetWidth;
	
	wrapper.style.width=wrapperChildCount*elementWidth+'px';
}

/*프로모션 상품선택시 상품개수증가*/
const setSelectProduct=(ele)=>{
	let classList=ele.classList;
	
	classList.forEach(eless=>{
		if(eless=='chooes'){
			ele.classList.remove('chooes');
		}else{
			ele.classList.add('chooes');
		}
	})

	let chooes= document.querySelectorAll('.chooes').length;
	let totalCount=document.querySelector('.total-count');
	totalCount.innerText=chooes;
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
	setUlHegith();
	setNavWidth();
})
</script>