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

<link href="${path}/resources/css/orderlistopt.css" rel="stylesheet" type="text/css">

<style>
	.review_wrap{
		margin-bottom:30px; 
	}
	.question_btn_wrap{
		margin-bottom: 30px;
	}	
	.reviwe_box {
		width: 100%;
	}
	 .star {
    position: relative;
    font-size: 2rem;
    color: #ddd;
  }
    .star span {
    width: 0;
    position: absolute; 
    left: 0;
    color: red;
    overflow: hidden;
    pointer-events: none;
  }
	.star input {
	    width: 100%;
	    height: 100%;
	    position: absolute;
	    left: 0;
	    opacity: 0;
	    cursor: pointer;
  }

</style>
<form method="post" action="${path }/mypage/review_add" id="review_frm">
	<input type="hidden" id="productId" name="productId">
	<input type="hidden" id="reservationInfoId" name="reservationInfoId">
	<input type="hidden" id="score" name="score">
	<input type="hidden" id="comment" name="comment">
</form>
<div class="qna_add_form">
            <div class="review_wrap">
              <div class="qna_categor category_title">
                일반후기등록
              </div>
            </div>
            <div class="qna_box_wrap" style="height: 55%;">
              <div class="category_title qna_categor">
               내용
              </div>
              <div class="qna_categor">
                <textarea name="name" rows="10" cols="68" placeholder="내용 입력"></textarea>
              </div>
            </div>
            <div class="">
            	<span style="font-weight: bold;">평점</span>
		         <div class="star_box">
					<span class="star"> ★★★★★ 
						<span>★★★★★</span>
						 <input type="range" oninput="drawStar(this)" value="1" step="1" min="0" max="10">
					</span>
					<div class="review_score">
						<span class="score_num">3.0/</span><span class="score_standard">5</span>
					</div>
				</div>
            </div>
          </div>
          <div class="qna_box_container">
            <div class="question_head">
              <h2>상품문의 안내</h2>
            </div>
            <div class="question_section">
              <div class="question_section_box">
                <ul class="question_section_ul">
                  <li class="question_section_list">
                    <p>
                      - 상품문의는 재입고, 사이즈, 배송 등 상품에 대하여 브랜드 담당자에게 문의하는 게시판입니다.
                    </p>
                  </li>
                  <li class="question_section_list actent">
                    <p>
                      - 욕설, 비방, 거래 글, 분쟁 유발, 명예훼손, 허위사실 유포, 타 쇼핑몰 언급,광고성 등의 부적절한 게시글은 금지합니다. 더불어 상품 문의 시 비밀글만 작성되도록 제한됩니다.
                    </p>
                  </li>
                  <li class="question_section_list">
                    <p>
                      - 주문번호, 연락처, 계좌번호 등의 개인 정보 관련 내용은 공개되지 않도록 비밀글로 문의해 주시기 바랍니다.
                      공개된 글은 비밀글로 전환될 수 있으며, 개인 정보 노출로 인한 피해는 MADAGASCAR가 책임지지 않습니다.
                    </p>
                  </li>
                </ul>
              </div>
            </div>
            <div class="question_btn_wrap">
              <button type="button" class="question_cancle"name="button">취소</button>
              <button type="button" class="question_add"name="button">등록하기</button>
            </div>
          </div>
          
        <script>
const drawStar = (target) => {
    document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
  }
</script>