<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>캠핑모아 MADAGASCAR 상품</title>
    <link href="${path}/resources/css/reset.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/index.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  </head>
  <body>
    <header>
       <div class="head-notice">
         <span class="header_text">15% OFF ALL TENT</span>
       </div>
       <nav class="fix-head">
         <div class="container">
           <div class="headcont">
             <a href="/healthproject/index">MADAGASCAR</a>
           </div>
          <div class="navbar-nav">
            <ul class="navbar_ul">
             <li class="ul-list"><a href="index" class="nav-li">HOME</a></li>
              <li class="ul-list"><a href="shop" class="nav-li chcke">SHOP</a></li>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/loginform" class="nav-li">LOGIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="logout" class="nav-li">Logout</a></li>
              </sec:authorize>
              
              <sec:authorize access="!isAuthenticated()">
              	<li class="ul-list"><a href="members/joinform" class="nav-li">JOIN</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
              	<li class="ul-list"><a href="members/mypage" class="nav-li">MYPAGE</a></li>
              </sec:authorize>
              
              <li class="ul-list"><a href="#" class="nav-li">CONTACT</a></li>
              <li class="ul-list"><a href="members/mycart" class="nav-li">MYCART</a></li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    <section class="product_info_section">
      <div class="product_info_containers">
        <!-- 
        <div class="product_info_box">
          <div class="pr_img_box">
            <div class="pr_nav">
              <a href="#" class="nav_link">Home</a>
              <a href="#" class="nav_link">Shop</a>
              <span class="nav_pr">I'm a product</span>
            </div>
            <img src="http://localhost:8080/healthproject/resources/img/product/moin_jakcket.jpg" alt="">
          </div>
          <div class="pr_info">
            무인양품은 1980년도 설립되어 [생산 과정 간소화][소재의 선택][포장의 간략화]라는 3가지 발상을 기본으로 심플하고 합리적인 가격의 상품을 만들어 내기 위해 노력해왔습니다.
          </div>
        </div>
       
        <div class="product_info_box">
          <div class="pass_box">
            <div class="pass_box_pn Prev_box">
              < Prev
            </div>
            <div class="pass_box_pn next_box">
              Next >
            </div>
          </div>
          <div class="pr_box">
            <div class="pr_name">
              I'm a Product
            </div>
            <div class="pr_num">
              SKU:001
            </div>
            <div class="pr_price">
              12,000원
            </div>
            <div class="pr_size">
              <label class="size_in"for="size">Size</label>
              <select class="selects" name="size">
                <option value="L">L</option>
                <option value="Xl">XL</option>
              </select>
            </div>
            <div class="pr_quantity">
              <label class="qut_in"for="qut">Quantity</label>
              <input type="text" class="qut_input"name="" value="1">
            </div>
            <div class="add_cart_box">
              <button type="button" class="cart_buy_btn add_cart_btn"name="button">Add to Cart</button>
            </div>
            <div class="buy_box">
              <button type="button" class="cart_buy_btn buy_box_btn"name="button">Buy Now</button>
            </div>
          </div>
          <div class="pr_info_wrap">
            <div class="pr_info_box">
              <div class="info_head">
                PRODUCT INFO
              </div>
              <div class="Info_section">
                I'm a product detail.
                I'm a great place to add more information about your product such as sizing, material, care and cleaning instructions.
                This is also a great space to write what makes this product special and how your customers can benefit from this item.
              </div>
            </div>
            <div class="pr_info_box">
              <div class="info_head">
                RETURN & REFUND POLICY
              </div>
              <div class="Info_section">
              I’m a Return and Refund policy.
              I’m a great place to let your customers know what to do in case they are dissatisfied with their purchase.
              Having a straightforward refund or exchange policy is a great way to build trust and reassure your customers that they can buy with confidence.
              </div>
            </div>
            <div class="pr_info_box">
              <div class="info_head">
                SHIPPING INFO
              </div>
              <div class="Info_section">
              I'm a shipping policy.
              I'm a great place to add more information about your shipping methods, packaging and cost.
              Providing straightforward information about your shipping policy is a great way to build trust and reassure your customers that they can buy from you with confidence.
              </div>
            </div>
          </div>
        </div>
        -->
      </div>
      <div class="product_review_container">
        <div class="review_wrap">
          REVIEW
        </div>
        <div class="review_head">
          구매후기(4)
        </div>
        <div class="star_box">
          <span class="star">
            ★★★★★
            <span>★★★★★</span>
          </span>
          <div class="review_score">
            <span class="score_num">3.0/</span><span class="score_standard">5</span>
          </div>
        </div>
        <div class="reviwe_box">
         <!-- 
          <div class="review_box_wrap">
            <div class="review_named">
              uiwv****님
            </div>
            <div class="review_detail_box">
              <div class="star_box">
                <span class="star starss">
                  ★★★★★
                  <span>★★★★★</span>
                </span>
              </div>
              <div class="review_text">
                정말 좋은 상품이에요~
              </div>
            </div>
          </div>
          <div class="review_box_wrap">
            <div class="review_named">
              uiwv****님
            </div>
            <div class="review_detail_box">
              <div class="star_box">
                <span class="star starss">
                  ★★★★★
                  <span>★★★★★</span>
                </span>
              </div>
              <div class="review_text">
                정말 좋은 상품이에요~
              </div>
            </div>
          </div>
          <div class="review_box_wrap">
            <div class="review_named">
              uiwv****님
            </div>
            <div class="review_detail_box">
              <div class="star_box">
                <span class="star starss">
                  ★★★★★
                  <span>★★★★★</span>
                </span>
              </div>
              <div class="review_text">
                정말 좋은 상품이에요~
              </div>
            </div>
          </div>
          <div class="review_box_wrap">
            <div class="review_named">
              uiwv****님
            </div>
            <div class="review_detail_box">
              <div class="star_box">
                <span class="star starss">
                  ★★★★★
                  <span>★★★★★</span>
                </span>
              </div>
              <div class="review_text">
                정말 좋은 상품이에요~
              </div>
            </div>
          </div>
          -->
        </div>
      </div>
      <div class="product_qna_container">
        <div class="review_wrap">
          Q&A
        </div>
        <div class="qna_box">
           <sec:authorize access="isAuthenticated()">
              <div class="add_qna_box">
           		 <button type="button" class="add_qna_btn"name="button">등록하기</button>
         	 </div>
          </sec:authorize>
          <table border="1">
            <thead>
              <tr>
                <th class="qa_num">번호</th>
                <th class="qa_num">답변여부</th>
                <th class="qa_num">구분</th>
                <th class="qa_info">내용</th>
                <th class="qa_num">작성자</th>
                <th class="qa_num">등록일자</th>
              </tr>
            </thead>
            <tbody class="qna_body">
              <tr>
                <td class="qa_anser">1</td>
                <td class="qa_anser">답변완료</td>
                <td class="qa_anser">배송</td>
                <td>배송</td>
                <td class="qa_anser">ui***</td>
                <td class="qa_anser">2022-01-02</td>
              </tr>
              <tr>
                <td class="qa_anser">2</td>
                <td class="qa_anser">답변완료</td>
                <td class="qa_anser">배송</td>
                <td>배송</td>
                <td class="qa_anser">ui***</td>
                <td class="qa_anser">2022-01-02</td>
              </tr>
             
            </tbody>
          </table>
        </div>
      </div>
     <sec:authorize access="isAuthenticated()">
	       <div class="popup" style="display:none;">
	        <div class="popup_layer">
	          <div class="text_area">
	            <strong class="login title">팝업 타이틀</strong>
	            <p class="text">상품을 장바구니에 <br>등록 하시겠습니까?</p>
	          </div>
	          <div class="btn_area">
	            <button type="button" name="button" class="btn yes">예</button>
	            <button type="button" name="button" class="btn no">아니오</button>
	          </div>
	        </div>
	         <div class="popup_dimmed"></div>
	       </div>
      </sec:authorize>
      <sec:authorize access="!isAuthenticated()">
	       <div class="popup" style="display:none;">
	        <div class="popup_layer">
	          <div class="text_area">
	            <strong class="nologin title">비로그인 상태</strong>
	            <p class="text">로그인 하시겠습니까?</p>
	          </div>
	          <div class="btn_area">
	            <button type="button" name="button" class="btn yes">예</button>
	            <button type="button" name="button" class="btn no">아니오</button>
	          </div>
	        </div>
	         <div class="popup_dimmed"></div>
	       </div>
      </sec:authorize>
      <form action="members/directbuy" method="post" id="member_form">
      	
      </form>
    </section>
    <footer>
      <footer>
        <div class="foot_section">
          <div class="foot_wrap">
            <div class="foot_text">
              <span>© 2021 product bae-Sang-Kyu</span><br>
              <span>Proudly created with uwiv29l@naver.com</span>
            </div>
          </div>
        </div>
      </footer>
    </footer>
        <script type="text/javascript" src ="${path }/resources/js/index.js"></script>
        <!--  
    	<script type="text/javascript">
    	/*	
    	function BuyProduct(){
    		var productSizeId=document.querySelector('.selects').value;
    		var count=document.querySelector('.qut_input').value;
    		var productId=getproductId();

    		var html=` <input type="hidden" name="count" value="${count}">
    				   <input type="hidden" name="productSizeId" value="${productSizeId}">
    					<input type="hidden" name="productId" value="${productId}">
    		`;
    		
    		var form=document.querySelector('#member_form');
    		
    		form.appendChild(html);
    		form.submit();
    	}
    	*/
    	</script>
    	-->
  </body>
</html>