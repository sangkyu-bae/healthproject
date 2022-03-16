<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:set var="path" value="${pageContext.request.contextPath}" />
<div class="order_form_container">
	<div class="order_wraps">Order</div>
	<div class="order_form_head_box">
		<h2>Order/Payment</h2>
		<div class="order_form_nav">
			<span>장바구니</span> <span class="i_tag"><i
				class="fas fa-chevron-right"></i></span> <span class="chcke">주문서</span> <span
				class="i_tag"><i class="fas fa-chevron-right"></i></span> <span>주문
				완료</span>
		</div>
	</div>
	<div class="order_section_box section_one">
		<div class="order_section_head">
			<h3>Recipient Info</h3>
			<span>수령자 정보</span>
		</div>
		<div class="order_form_wrap">
			<div class="order_ul_box">
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">배송지 선택</li>
					<li class="cell_discount_detail">신규 배송지</li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">수령인</li>
					<li class="cell_discount_detail"><input class="recipient_info"
						type="text" name="" value=""></li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">휴대전화</li>
					<li class="cell_discount_detail">
						<div class="">
							<select class="rmobile1" name="">
								<option value>선택하세요</option>
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
							</select> <span>-</span> <input type="text" maxlength='4'
								class="recipient_info phons" name="" value=""> <span>-</span>
							<input type="text" maxlength='4' class="recipient_info phons"
								name="" value="">
						</div>
					</li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">전화번호</li>
					<li class="cell_discount_detail">
						<div class="select_boxs">
							<select class="rmobile1" id="numbers_box" name="">
								<option value>선택하세요</option>
								<option value="02">02</option>
								<option value="031">031</option>
								<option value="032">032</option>
								<option value="033">033</option>
								<option value="041">041</option>
								<option value="042">042</option>
								<option value="043">043</option>
								<option value="044">044</option>
								<option value="051">051</option>
								<option value="052">052</option>
								<option value="053">053</option>
								<option value="054">054</option>
								<option value="055">055</option>
								<option value="061">061</option>
								<option value="062">062</option>
								<option value="063">063</option>
								<option value="064">064</option>
							</select> <span>-</span> <input type="text" maxlength='4'
								class="recipient_info phons home" name="" value=""> <span>-</span>
							<input type="text" maxlength='4'
								class="recipient_info phons home" name="" value="">
							<div class="check_box" style="display: inline;">
								<input type="radio" id="h" name="h" value=""> <label
									class="checknum" for="h">없음</label>
							</div>
						</div>
					</li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">배송지 ㅇㄹㅇ주소</li>
					<li class="cell_discount_detail">
						<div class="add_box">
							<input type="text" class="recipient_info nonclick fix_adrr_ti"
								name="" value="" disabled> <a class="plain_btn"
								onclick="execPostCode()">주소찾기</a><br> <input type="text"
								class="recipient_info nonclick fix_adrr_mi" name="" value=""
								disabled><br> <input type="text"
								placeholder="상세 주소를 입력해주세요" class="recipient_info fix_adrr_mi"
								name="" value=""><br>
						</div>
					</li>
				</ul>
				<ul class="box_receiver_info">
					<li class="cell_discount_tit">배송 메모</li>
					<li class="cell_discount_detail">
						<div class="ask_box">
							<select class="dlv_selectbox" name="">
								<option value>배송 시 요청사항을 선택해주세요</option>
								<option value="부재 시 경비실에 맡겨주세요">부재 시 경비실에 맡겨주세요</option>
								<option value="부재 시 집 앞에 놔주세요">부재 시 집 앞에 놔주세요</option>
								<option value="배송 전 연락 바랍니다">배송 전 연락 바랍니다</option>
								<option value="파손의 위험이 있는 상품입니다. 배송 시 주의해주세요">파손의 위험이
									있는 상품입니다. 배송 시 주의해주세요</option>
								<option value="etc">직접 입력</option>
							</select>
							<textarea name="name" id="my-self-text" maxlength="50" rows="2"
								cols="50" placeholder="최대 50자까지 입력 가능합니다."
								style="display: none;"></textarea>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="order_section_box">
		<div class="order_section_head">
			<h3>Product Info</h3>
			<span class="pr_info_s">상품 정보</span>
		</div>
		<div class="order_pr_table_wrap">
			<table class="table_basic">
				<colgroup>
					<col>
					<col width="70px">
					<col width="100px">
					<col width="100px">
				</colgroup>
				<thead>
					<tr>
						<th scope="col">수량정보</th>
						<th scope="col">수량</th>
						<th class="table_background" scope="col">배송비</th>
						<th class="table_background" scope="col">주문금액</th>
					</tr>
				</thead>
				<tbody class="order_tnodys">
					<c:forEach var="cart" items="${cart}" varStatus="status">
						<tr>
							<td>
								<div class="order_pr_img_box order_left">
									<a><img src="${path}/resources/img/${cart.saveFileName}"
										alt=""></a>
								</div>
								<div class="order_left order_pr_info_boxs">
									<h2>${cart.description}</h2>
									<div class="order_option_box">
										<p>옵션 : ${cart.size}</p>
									</div>
								</div>
							</td>
							<td class="order_qtn"><strong>${cart.count}개</strong></td>
							<td class="order_qtn table_background"><span>무료</span> <br>
								<span class="order_cor">반송비 확인</span></td>
							<td class="order_qtn table_background"><c:if
									test="${cart.discountRate eq 0 }">
									<span class="real_price">78,000 원</span>
								</c:if> <c:if test="${cart.discountRate ne 0 }">
									<span class="sale_prices">98,000 원</span>
									<span class="real_price">78,000 원</span>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="order_list_section_box">
				<ul class="list_section_type">
					<li>- 구매 가능 수량이 1개로 제한된 상품은 주문 취소 시, 24시간 내 가상계좌 재주문이 불가합니다.</li>
					<li>- MADAGASCAR은 기본적으로 대한민국 내 제주도 및 도서 산간 지역 포함 <strong>전
							지역, 전 상품 무료배송입니다.</strong>
					</li>
					<li>- 해외 배송 상품이나 일부 업체의 경우, 교환/환불 시 반송료가 다를 수 있으며 상품 페이지에 별도
						표기되어 있습니다.</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="order_section_box">
		<div class="order_section_head">
			<h3>Payment info / Agreement</h3>
			<span class="pr_info_s">결제 정보 / 주문자 동의</span>
		</div>
		<div class="pay_info_wrap">
			<ul class="pay_info_ul">
				<li class="pay_info_list cell_discount_tit ">결제 안내</li>
				<li class="pay_info_list cell_rigth">
					<div class="payment-area-wrap">
						<ul>
							<c:forEach items="${paymentList}" var="list">
								<li><input type="radio" id="paym_0${list.id}" name="kyejae" value="">
								<label class="box_choice" for="paym_0${list.id }">${list.name}</label></li>
							</c:forEach>
						</ul>
					</div>
					<div class="box-select">
						<select name="card_code" id="card_code">
							<option value="">카드 선택</option>
							<option value="BC81|31">하나 BC</option>
							<option value="CCBC|31">비씨카드(페이북)</option>
							<option value="CCCJ|42">제주카드</option>
							<option value="CCCT|36">씨티카드</option>
							<option value="CCCU|62">신협카드</option>
							<option value="CCDI|61">현대카드</option>
							<option value="CCHN|21">하나카드</option>
							<option value="CCHS|" selected="selected">KB증권(현대증권)</option>
							<option value="CCJB|35">전북카드</option>
							<option value="CCKA|15">카카오뱅크</option>
							<option value="CCKD|30">KDB산업</option>
							<option value="CCKE|21">하나(외환)</option>
							<option value="CCKJ|46">광주카드</option>
							<option value="CCKK|31">케이뱅크</option>
							<option value="CCKM|11">KB 국민</option>
							<option value="CCLG|41">신한카드</option>
							<option value="CCLO|71">롯데카드</option>
							<option value="CCNH|91">NH채움</option>
							<option value="CCPB|37">우체국카드</option>
							<option value="CCPH|33">우리카드</option>
							<option value="CCSB|39">저축은행</option>
							<option value="CCSM|38">MG새마을</option>
							<option value="CCSS|51">삼성카드</option>
							<option value="CCSU|34">수협카드</option>
						</select> <select id="card_quota" name="card_quota">
							<option value="">일시불</option>
							<option value="2">2개월</option>
							<option value="3">3개월</option>
							<option value="4">4개월</option>
							<option value="5">5개월</option>
							<option value="6">6개월</option>
							<option value="7">7개월</option>
							<option value="8">8개월</option>
							<option value="9">9개월</option>
							<option value="10">10개월</option>
							<option value="11">11개월</option>
							<option value="12">12개월</option>
						</select>
						<button type="button" class="order-benefit-button"
							onclick="Order.showInterestBenefitInfo(); return false;">
							<span>무이자/부분무이자 할부 혜택 안내</span><i class="fas fa-chevron-right"></i>
						</button>
					</div>
					<div class="order-notice">
						<p class="order-notice__title">안전결제(ISP)? (국민카드/BC카드/우리카드)</p>
						<p class="order-notice__contents">온라인 쇼핑 시 주민등록번호, 비밀번호 등의 주요
							개인정보를 입력하지 않고 고객님이 사전에 미리 설정한 한전결제(ISP) 비밀번호만 입력, 결제하도록 하여 개인정보
							유출 및 카드 도용을 방지하는 서비스입니다.</p>
						<p class="order-notice__title">안심 클릭 결제?
							(삼성/외환/롯데/현대/신한/시티/하나/NH/수협/전북/광주/산업은행/제주은행)</p>
						<p class="order-notice__contents">온라인 쇼핑시 주민등록번호, 비밀번호 등의 주요
							개인 정보를 입력하지 않고 고객님이 사전에 미리 설정한 전자 상거래용 안심 클릭 비밀번호를 입력하여 카드 사용자 본인
							여부를 확인함으로써 온라인상에서의 카드 도용을 방지하는 서비스입니다.</p>
					</div>
				</li>
			</ul>
		</div>
		<div class="my_agree_wrap">
			<ul class="agree_ul">
				<li class="pay_info_list cell_discount_tit">주문자 동의</li>
				<li class="pay_info_list cell_rigth">
					<div class="order-agree">
						<div class="order-agree__checkbox order_all_agree">
							<input type="checkbox" name="order_check" id="all_agree" value="">
							<label for="all_agree">필수 항목 전체 동의하기</label>
						</div>
						<div class="order-agree__checkbox">
							<input type="checkbox" name="order_check" id="agree_person"
								value=""> <label for="agree_person">[필수] 개인정보 수집
								및 이용 동의</label>
						</div>
						<div class="order-agree__checkbox">
							<input type="checkbox" name="order_check" id="agree_info"
								value=""> <label for="agree_info">[필수] 개인정보 제 3자
								제공 동의</label>
						</div>
						<div class="order-agree__checkbox">
							<input type="checkbox" name="order_check" id="agree_pay" value="">
							<label for="agree_pay">[필수] 전자결제대행 이용 동의</label>
						</div>
						<div class="order-agree__checkbox">
							<input type="checkbox" name="order_check" id="agree_buy_info"
								value=""> <label for="agree_buy_info">[필수] 상품정보,
								거래조건 확인 및 구매진행 동의</label>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="order_submit_btn_box">
		<button type="button" class="order_btn" name="button">${totalPrice}원
			결제하기</button>
	</div>
</div>
<script type="text/javascript" src="${path }/resources/js/addressapi.js"></script>
<script type="text/javascript">
/////전화번호 or핸드폰 번호 4자리 입력시 다음칸으로 focus
 	function nextNumber(){
 		$(".phons").keyup(function(){
 			var limitNum=$(this).attr("maxlength");
 			if(this.value.length>=limitNum){
 				$(this).next().next('.phons').focus();
 				return false;
 			}
 		})
 	}

 	/////////전화번호 없음 클릭시 disabled
 	function checkHomeNumber(){
 		
 		var checkNode=document.querySelector('#h');
 		checkNode.addEventListener("click",function(evt){
 			disabledfuc(checkNode);	
 		})
 	}
 	function disabledfuc(checkNode){
 		var numInput=document.querySelectorAll('.home');
 		var numberBox=document.querySelector('#numbers_box');
 		if(numInput[0].disabled===false){
 			checkNode.checked=true;
 			for(var i=0;i<numInput.length;i++){
 				numInput[i].disabled=true;
 				numInput[i].value='';
 			}
 			numberBox.value='';
 			numberBox.disabled=true;
 			numberBox.style.color='gray';
 			numberBox.style.fontWeight='bold';
 		}else if(numInput[0].disabled===true){
 			checkNode.checked=false
 			for(var i=0;i<numInput.length;i++){
 				numInput[i].disabled=false;
 			}
 			numberBox.disabled=false;
 			numberBox.style.color='black';
 			numberBox.style.fontWeight='';
 		}
 	}

 	//////배송메모의 직접입력으로 벨류 변경시 텍스트에리아 박스 보이게하기
 	function showTextarea(){
 		var select=document.querySelector('.dlv_selectbox');
 		var textareas=document.querySelector('#my-self-text');
 		select.addEventListener('change',function(){
 			if(select.value==='etc'){
 				textareas.style.display='block';
 			}else{
 				if(textareas.style.display==='block'){
 					textareas.style.display='none';
 				}
 			}
 		})
 	}
 	///////////주문 전화번호 유효성 검사
 	function checkPhoneNum(){
 		var numberBox=document.querySelectorAll('.phons');
 		var checkPhone=/\d{3,4}/;
 		
 		var checknum;
 		for(var i=0;i<numberBox.length;i++){
 			if(!checkPhone.test(numberBox[i].value)){
 				 if(numberBox[i].disabled===true){
 					checknum=1;
 				 }else{
 					checknum=numberBox[i];
 					break;
 				 }	
 			}else{
 				checknum=1;
 			}
 		}
 		return checknum;
 	}
 	////배송 메모 확인
 	function checkRecipient(){
 		var recipient=document.querySelector('.dlv_selectbox');////배송메모 
 		var etcTextarea=document.querySelector('#my-self-text');
 		var checknum=2;
 		if(recipient.value===''){
 			checknum=0
 		}else if(recipient.value==='etc'){
 			if(etcTextarea.value.length<5){
 				checknum=1;
 			}
 		}
 		return checknum;
 	}
 	////주문자 동의 확인하기
 	function checkBuyAgree(){
 		var checkBox=document.querySelectorAll('.order-agree__checkbox');
 		var checkNum=0;
 		for(var i=1;i<checkBox.length;i++){
 			if(checkBox[i].firstElementChild.checked===false){
 				checkNum=checkBox[i].firstElementChild;
 				break;
 			}
 		}
 		return checkNum;
 	}
 	//전체폼 검사
 	function checkOrderForm(){
 		var names=document.querySelector('.recipient_info');///수령인
 		var number=checkPhoneNum();///전화번호 유효성 검사
 		var address=document.querySelector('.fix_adrr_ti');//배송지 주소
 		var recipient=checkRecipient();////배송메모 유효성 검사
 		var agreeCheck=checkBuyAgree();
 		
 		if(names.value.length<3){
 			alert("수령인 이름이 너무작습니다.");
 			names.focus();
 		}else if(number!=1){
 			alert("번호형식이 잘못되었습니다.");
 			number.focus();
 		}else if(address.value===''){
 			alert("배송지 주소가 등록되지 않았습니다");
 			var adr=document.querySelectorAll(".fix_adrr_mi");
 			adr=adr[1];
 			adr.focus();
 		}else if(recipient.value===''){
 			alert("배송 메모를 등록해주세요");
 			recipient.focus();
 		}else if(recipient===0){
 			alert("배송 메모를 등록해주세요");
 			document.querySelector('.dlv_selectbox').focus();
 		}else if(recipient===1){
 			alert("직접입력 글자수가 적습니다. 5자 이상 등록해주세요");
 			document.querySelector('#my-self-text').focus();
 		}else if(agreeCheck!=0){
 			alert("필수 동의사항을 체크해주세요");
 			agreeCheck.focus();
 		}
 	}

 	////필수 항목 전체 동의 누를시 다 체크
 	function allCheck(){
 		var btn=document.querySelector('#all_agree');
 		var checkOrderAgree=document.querySelectorAll('.order-agree__checkbox');
 		btn.addEventListener("change",function(){
 			console.log(btn.checked);
 			if(btn.checked===true){
 				for(var i=1;i<checkOrderAgree.length;i++){
 					checkOrderAgree[i].firstElementChild.checked=true;
 				}
 			}else if(btn.checked===false){
 				for(var i=1;i<checkOrderAgree.length;i++){
 					checkOrderAgree[i].firstElementChild.checked=false;
 				}
 			}
 		})
 	}
 	function clickBtnEvent(){
 		var btn=document.querySelector('.order_btn');
 		btn.addEventListener('click',function(){
 			checkOrderForm();
 		})
 	}
	
    function ChangeTypePrice(price,discount,count){
       price=price*count;
       console.log(price);
       price=price.toLocaleString('ko-KR');
       if(discount>0){
          var sale_prices=document.querySelector('.sale_prices');
          sale_prices.innerText=price+" 원"; 
          
          var resultPrice=price;
          var disCount=disCount/100;             
          var disPrice=resultPrice*discountRate;
          resultPrice=resultPrice-disPrice;
          
          sale_prices.nextElementSibling.innertext=resultPrice+" 원";
       }else{
          var real_price=document.querySelector('.real_price');
          real_price.innerText=price+" 원"
       }
       
    }

 	document.addEventListener("DOMContentLoaded",function(){
 		nextNumber();
 		checkHomeNumber();
 		showTextarea();
 		clickBtnEvent();
 		allCheck();
 		
 	})
 	</script>
