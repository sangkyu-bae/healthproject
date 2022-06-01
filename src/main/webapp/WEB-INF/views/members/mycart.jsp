<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.n-table-none{
    width: 500px;
    margin: 0 auto;
    line-height: 30em;
    text-align: center;
    font-size: 20px;
    font-weight: bold;
}
</style>
<c:set var="path" value="${pageContext.request.contextPath}"/>
      <div class="cart_container">
        <div class="cart_wrap">
          <div class="cart_head cart_left">
            <div class="cart_head_name">
                My cart
            </div>
             <ul class="my_cart_list_box">
             <%--  <c:choose>
              	<c:when test="${empty list}">
              			<p class="n-table-none">
							<sapn>상품 문의 내역이 없습니다 </sapn>
						</p>
              	</c:when>
              	<c:otherwise>
              	  <li class="my_cart_list">
	                <div class="cart_section">
	                  <div class="cart_img_box cart_section_box">
	                    <img src="${path }/img/${list.saveFileName}" alt="${list.description}">
	                  </div>
	                  <div class="cart_section_box cart_product_info">
	                    <div class="cart_product_name">
	                      I'm a product
	                    </div>
	                    <div class="cart_product_price cart_color">
	                      12,000원
	                    </div>
	                    <div class="cart_color">
	                      Size:L
	                    </div>
	                  </div>
	                  <div class="cart_section_box cart_product_qut">
	                    <input type="text"class="cart_input" name="" value="1">
	                    <div class="qtn_boxs min_box">
	                      -
	                    </div>
	                    <div class="qtn_boxs plus_box">
	                      +
	                    </div>
	                  </div>
	                  <div class="cart_section_box cart_product_prices">
	                    12,000원
	                  </div>
	                  <div class="cart_section_box cart_delte_box">
	                    X
	                  </div>
	                  <div class="cart_buy_btns">
	                  	<input type="button">
	                  </div>
	                </div>
             	 </li>
              	</c:otherwise>
              </c:choose>
             --%>
               
            </ul>
          </div>
       
		<div class="cart_order_container cart_left">
            <div class="order_head">
              Order summary
            </div>
            <div class="order_list_wrap">
              <div class="subtota sub_total_box">
                <span class="order_sub_head">${list.length }</span>
                <span class="order_sub_price sub_total">12,000원</span>
              </div>
              <div class="subtota sub_shipping">
                <span class="order_sub_head">shipping</span>
                <span class="order_sub_price">Free</span>
              </div>
              <div class="subtota location_box">
                Deajon, South Korea
              </div>
            </div>
            <div class="total_box">
              <span class="order_sub_head">Total</span>
              <span class="order_sub_price total_price">12,000원</span>
            </div>
            <div class="order_check_box">
              <button type="button"class="order_check_box_btn" onclick="buy_all_product()" name="button">Checkout</button>
            </div>
          </div>
       
    
    
       
        </div>
      </div>
      <div class="popup" style="display:none;">
      	<div class="popup_layer">
      		<div class="text_area">
      			<strong class="title">
                      기사미 태티컬 Polar Fleece Jacket
                </strong>
      			<p class="text">상품을 장바구니에서<br> 삭제하겠습니까</p>
      		</div>
      		<div class="btn_area">
      			<button type="button" name="button" class="btn yes">예</button>
      			<button type="button" name="button" class="btn no">아니오</button>
      		</div>
      	</div>
    	   <div class="popup_dimmed"></div>
       </div>
    <script type="text/javascript">
    function buy_all_product(){
    	window.location.href = `/healthproject/members/orderform`;
    }
    function qtnChange(){
    	var ul= document.querySelector('.my_cart_list_box');
    	ul.addEventListener("click",function(evt){
    		var className=evt.target.className;
    		if(className.indexOf('plus_box')>0){
    			var count =evt.target.previousElementSibling.previousElementSibling.value;
    			count=parseInt(count);
    			count=count+1;
    			var id=evt.target.parentNode.id;
    			
    			qtnChangeAjax(count,id);
    		
    		}else if(className.indexOf("min_box")>0){
    			var count =evt.target.previousElementSibling.value;
    			count=parseInt(count);
    			count=count-1;
    			var id=evt.target.parentNode.id;
    			if(count>0){
    				qtnChangeAjax(count,id);
    			}
    		}
    	})
    }

    function qtnChangeAjax(count,id){
    	var data={
    			count:count,
    			id:id	
    	}
    		$.ajax({
    		url : "http://localhost:8080/healthproject/api/updateQtn",
    		data:JSON.stringify(data),
            dataType : "json",
    		method:"POST",
    	    contentType: "application/json; charset=utf-8",
            success : function(data) {
            	console.log(data.message);
            	if(data.message==='fali'){
            		alert('로그인이 만료되었습니다. 다시 로그인하세요');
            	}
    		}
    	})
    }
    function addProducts(reservationId){ 
    	location.href="/healthproject/members/orderform?reservationId="+reservationId ;
    }
    document.addEventListener("DOMContentLoaded",function(){
    	qtnChange();
    });
    </script>

