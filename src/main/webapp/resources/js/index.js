/////헤더영역
//////

////상단 텍스트 변경
function moveText(){
	var text=document.querySelector('.header_text');
	var basicText=text.innerText;
	var count=0;
	setInterval(function(){
		if(count%2===1){
			text.innerText='WELECOME MADAGASCAR';
			text.innerText.transition='all ease 0.5s';
		}else{
			text.innerText=basicText;
			text.innerText.transition='all ease 0.5s';
		}
		count++;
		
	},2000);
}
///상단 네비영역 클릭시 색상변화
function clickName(){
	var headNav=document.querySelector('.navbar_ul');
	headNav.addEventListener('click',function(evt){
		if(evt.target.tagName==='LI'){
			addCheckClass(evt.target.firstElementChild)
			getUrlHerf(evt.target.firstElementChild);
		}else if(evt.target.tagName==='A'){
			addCheckClass(evt.target);
		}
	});
}
function addCheckClass(clickNode){
	var node=clickNode.classList;
	var checkclick=document.querySelectorAll('.nav-li');
	////클릭시 chcke 클래스 삭제
	for(var i=0;i<checkclick.length;i++){
		if(checkclick[i].classList[1]==='chcke'){
			checkclick[i].classList.remove('chcke');
		}
	}
	node.add('chcke');
}
///////li이벤트버블 설정 a태그 위에 li 눌러도 a태그 url로 변경
function getUrlHerf(clickNode){
	var atg=clickNode.href;
	location.href=`${atg}`;
}
////상단 li 클릭시 슬라이드 구현
function moveSectionImg(){
		var curIndex = 0;///이미지 카운트 세기위한 카운트 생성
		
		var ul = document.querySelector('.item ');
		var lis = document.querySelectorAll('.item_list');
		var count = lis.length;
		
		console.log(count);
		var li= ul.firstElementChild;
		var liwidth=li.offsetWidth;
		/////사진이 2장이상일 시에만 자연스런 슬라이딩을 위한 복사 이미지 생성
		/////생성후 이벤트 생성
			if(count>1){
				//////사진복사
				ul.style.width=(count+2)*liwidth+"px";
			    var lastChild = ul.lastElementChild;
				var last =lastChild.cloneNode(true);
			    var firstItemClone = li.cloneNode(true);
				
			    ul.appendChild(firstItemClone);
				ul.insertBefore(last,ul.firstElementChild);
				ul.style.transform = "translate3d(-" + (liwidth * (curIndex + 1)) + "px, 0px, 0px)";
				//////2장이상 이므로 위에보이는 폼 생성
				
				//////이벤트생성
				
				var arrow=document.querySelector('.right_click');
				var leftArrow=document.querySelector('.left_click');
				leftArrow.addEventListener("click",function(evt){
				if(curIndex===0){

				ul.style.transition = '0.2s';
				    ul.style.transform = "translate3d("+ (liwidth*(curIndex))+"px, 0px, 0px)";
					 setTimeout(function(){
				                      ul.style.transition='0s';
				                      ul.style.transform= "translate3d(-" +liwidth*count + "px, 0px, 0px)";
				                    },201)
							curIndex=count-1;
		
				}else{
					ul.style.transition = '0.2s';
				    ul.style.transform = "translate3d(-"+ (liwidth*(curIndex))+"px, 0px, 0px)";
					curIndex--;
				}
				
			});
			arrow.addEventListener("click",function(evt){				
								 if(curIndex === count-1){
								  ul.style.transition = '0.2s';
				            	  ul.style.transform = "translate3d(-"+liwidth*(curIndex+2)+"px, 0px, 0px)";

								 setTimeout(function(){
				                      ul.style.transition='0s';
				                      ul.style.transform= "translate3d(-" +liwidth + "px, 0px, 0px)";
				                    },201)
									curIndex=0;
									
								}else{
								  ul.style.transition = '0.2s';
				            	  ul.style.transform = "translate3d(-"+liwidth*(curIndex+2)+"px, 0px, 0px)";
								  
							  	  curIndex++;
								}
			});
		}
}
///////상단 프로모션 이미지 가져오기

function getPromotionImage(data){
	var html=``;
	var promotion=data['promotionList'];
	var ul=document.querySelector('.item');
	for(var i=0;i<promotion.length;i++){
		html+=` <li class="item_list">
                  <a href="">
                    <img src="${path}/resources/img/${promotion[i].saveFileName}" alt="${promotion[i].description}">
                  </a> 
				  <div class="item_title">
                    ${promotion[i].description}
                  </div>  
                </li>
		`
	}
	if(promotion.length<2){
		$('.click_clor ').remove();
	}
	$('.item').append(html);
	moveSectionImg();
	setShopNowHerf();
}
function getData(){
	$.ajax({
		url : `${path}/api/getpromotion`,
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
				getPromotionImage(data);
		}
	})
}
////shopnow href 달기
function setShopNowHerf(){
	var firstBtn=document.querySelector('.first_btn');
	var btns=document.querySelector(".button-box");
	firstBtn.addEventListener('click',function(evt){
		location.href=`${path}/shop`;
	});
	btns.addEventListener('click',function(evt){
		ocation.href=`${path}/shop`;
	});
}
////////////
/////////////////섹션 프로덕트 상품 가져오기
function getProduct(start){
	var start=start;
	$.ajax({
		url : `${path}/api/getproduct?start=`+start,
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
			var product=data['allProduct'];
			if(product.length<4){
				$('.btn_box').remove();
			}
			createProduct(data);
		}
	})
}
function createProduct(data){
	var html=``;
	var product=data['allProduct'];
	var ul=document.querySelector('.product_item_box');
	
	for(var i=0; i<product.length;i++){
		var price=product[i].price;
		if(product[i].discountRate>0){
			var discountRate=product[i].discountRate/100;
			var disPrice=price*discountRate;
			price=price-disPrice;
		}
		price=price.toLocaleString('ko-KR');
		html+=` <li class="product_item">
                  <a href="http://localhost:8080/healthproject/product?id=${product[i].productId}">
                    <img src="${path}/resources/img/${product[i].saveFileName}" alt="${product[i].description}">
                  </a>
                  <div class="product_view">
                    <a href="${path}/product?id=${product[i].productId}">
                        Qucik View
                    </a>
                  </div>
                  <div class="product_text">
                    ${product[i].description}
                  </div>
                  <div class="product_price">
                    ${price}원
                  </div>
                </li>`
	}
	$('.product_item_box').append(html);
	
}
///더보기 클릭시이벤트
function addProduct(){
	var btn=document.querySelector('.btn_box');
	btn.addEventListener("click",function(evt){
		var liLength=document.querySelectorAll('.product_item').length;
		var resultStart=liLength;
		setHeigth();
		getProduct(resultStart);
	})
}
////높이 설정
function setHeigth(){
	var productContainer=document.querySelector('.prodcut_container');
	var heigth=productContainer.offsetHeight;
	var result=heigth+430;
	
	productContainer.style.height=result+'px';
}
/////url 분석
function pasedUrl(){
	const parsedUrl = new URL(window.location.href);
	var pathName=parsedUrl.pathname;
	var path=pathName.split('/');
	return path[2];
}
function scpasedUrl(){
	const parsedUrl = new URL(window.location.href);
	var pathName=parsedUrl.pathname;
	var path=pathName.split('/');
	
	return path[3];
}
///////////////////////
////////////////////로그인폼 스크립트 작성

function locationAddmemeber(){
	var ul=document.querySelector(".ul_box");
	
	ul.addEventListener('click',function(evt){
		if(evt.target.tagName==='LI'){
			var textName=evt.target.firstElementChild.innerText;
			console.log(textName);
			if(textName==='회원가입'){
				location.href=`${path}/members/joinform`;
			}
		}else if(evt.target.tagName==='SPAN'){
			console.log(evt.target.innerText);
			if(evt.target.innerText==='회원가입'){
				location.href=`${path}/members/joinform`;
			}
		}
	})
}
//////로그인폼 유효성 검사
function checkLoginSubmit(){
	var id=document.querySelector('#login_id');
	var password=document.querySelector("#login_password");
	var sub=document.querySelector(".sub_from");
	
	sub.addEventListener('click',function(evt){
		evt.preventDefault();
		var checkemail=checkEmail(id.value);
		
		if(password.value.length===0){
			alert("아이디를 입력하세요");
			id.focus();
		}else if(checkemail===0){
			alert("이메일 형식이 잘못되었습니다.");
			id.focus();
		}else if(password.value.length<8){
			alert("비밀번호 형식이 잘못되었습니다.");
			password.focus();
		}else{
			document.querySelector("#login_forms").submit();
		}
	});
}

///////////////
//////////////회원가입 폼 유효성 검사
function checkEmail(email){
		var checknumber;
		var checkresult=/^[\w+_]\w+@\w+\.\w+$/
		if(!checkresult.test(email)){
			checknumber=0;
			return checknumber;
		}else{
			checknumber=1;
			return checknumber;
		}
}
function checkPhoneNumber(number){
	var checknumber;
	var checkephone=/[0][1][0,1,2,3,9]-\d{4}-\d{4}/;
	
	if(!checkephone.test(number)){
		checknumber=0;
	}else{
		checknumber=1;
	}
	return checknumber;
}
function checkPassword(first,scond){
	var checknumber;
	if(first===scond){
		checknumber=1;
	}else{
		checknumber=0;
	}
	return checknumber;
}

function checkSubmit(){
	var email=document.querySelector('#email');
	var firstpassword=document.querySelector("#password");
	var scondpassword=document.querySelector('#password2');
	var phone=document.querySelector('#phone');
	
	var checkemail=checkEmail(email.value);
	var checkphone=checkPhoneNumber(phone.value);
	var checkpassword=checkPassword(firstpassword.value,scondpassword.value);
	
	var checkname=document.querySelector('#name'); 
	var checkaddress=document.querySelector('#address');
	
	var passwordlength=firstpassword.value.length;
	var sub=document.querySelector('#join_sub');
	if(checkemail===0){
		alert("이메일 형식이 잘못되었습니다.");
		email.focus();
	}else if(passwordlength<8){
		alert("비밀번호 크기가 작습니다.");
		password.focus();
	}else if(checkpassword===0){
		alert("비밀번호가 일치하지 않습니다.");
		password.focus();
	}else if(checkname.value.length<2){
		alert("이름이 짧습니다.");
	}else if(checkphone===0){
		alert("핸드폰 형식이 잘못되었습니다.");
		phone.focus();
	}else if(checkaddress.value.length<8){
		alert("주소가 잘못 입력되었습니다.");
	}else{
		sub.submit();
	}
}
//////////////
///////////////shop페이지 스크립트
function getProductData(categoryId,sort){
	var url=`${path}/api/getallproduct`;
	var id =categoryId;
	if(id!=undefined){
		url+="?id="+id;
	}
	$.ajax({
		url : url,
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
				if(sort!=undefined){
					sortPriceLowtoHigh(data,sort);
				}else{
					getProductImg(data);
				}
				
		}
	})
}

function getProductImg(data){
	if(data['productinfo']!=undefined){
		var data=data['productinfo'];
	}else{
		var data=data;
	}
	var html='';
	
	for(var i=0;i<data.length;i++){
		var discountRate=data[i].discountRate;
		
		var price=data[i].price;
		//price=price.toLocaleString('ko-KR');
		if(discountRate>0){
			var changeprice=price-price*(discountRate/100);
			console.log(changeprice);
			changeprice=changeprice.toLocaleString('ko-KR');
			price=price.toLocaleString('ko-KR');
			html+=`
			<li class="product_img_item" data-prid="${data[i].id}">
                 <a href="product?id=${data[i].id}">
                   <img src="${path}/resources/img/${data[i].saveFileName}" alt="${data[i].description}">
                 </a>
                 <div class="product_view">
                   <a href="product?id=${data[i].id}">
                       Qucik View
                   </a>
                 </div>
               	 <div class="product_text">
                  ${data[i].description}
                 </div>
                 <div class="product_price">
					<span class="sale">${price}원</span>
                    <span>${changeprice}원</span>        
                 </div>
            </li>
			`;
		}else{
			price=price.toLocaleString('ko-KR');
			html+=`<li class="product_img_item" data-prid="${data[i].id}">
                  <a href="product?id=${data[i].id}">
                    <img src="${path}/resources/img/${data[i].saveFileName}" alt="${data[i].description}">
                  </a>
                  <div class="product_view">
                    <a href="product?id=${data[i].id}">
                        Qucik View
                    </a>
                  </div>
                  <div class="product_text">
                    ${data[i].description}
                  </div>
                  <div class="product_price">
                   	${price}원
                  </div>
                </li>`;
		}
		
	}
	$('.product_img_ul').append(html);
	
}
////요소 삭제
function removeProductImg(){
	var ul= document.querySelector('.product_img_ul');
	var length=ul.children.length;
	
	for(var i=0;i<length;i++){
		ul.removeChild(ul.firstElementChild);
	}
}
////필터 설정 누를시 상품 페이지 비동기 변환

function clickCategory(){
	var ul=document.querySelector('.filter_list');
	ul.addEventListener('click',function(evt){
		console.log(evt.target.tagName);
		if(evt.target.tagName==='LI'){
			removeProductImg();
			changeClass(evt.target.firstElementChild);
			getProductData(evt.target.dataset.category);
			changSelectBox();
		}else if(evt.target.tagName==='A'){
			removeProductImg();
			changeClass(evt.target);
			getProductData(evt.target.parentNode.dataset.category);
			changSelectBox();
		}
	});
}
///////////////클릭시 글씨 색상변경 
function changeClass(data){
	var li=document.querySelectorAll('.filter_link');
	
	for (var i=0;i<li.length;i++){
   	 	li[i].classList.remove('click_item');
	}
	
	data.classList.add('click_item');
}
//////상품 카테고리 변경시 셀렉박스 기본값으로 변경
function changSelectBox(){
	var selectBox=document.querySelector('#select-lang');
	selectBox.value=0;
}
//////상품 정렬
function sortProduct(){
	var select =document.querySelector('#select-lang');
	
	var selectValue=select.options[select.selectedIndex].value;
	if(selectValue==='price-low'){
		getSort(1);
	}else if(selectValue==='price-high'){
		getSort(2);
	}else if(selectValue==='name-low'){
		getSort(3);
	}else if(selectValue==='name-high'){
		getSort(4);
	}
}
///// 현재 데이터셋값 찾기
function getDataset(){
	var dataset=document.querySelector('.click_item');
	dataset=dataset.parentElement.dataset.category;
	
	return dataset;
}
///상품 소트
function getSort(sort){
	var catgoryId=getDataset();
	getProductData(catgoryId,sort);
}
function sortPriceLowtoHigh(data,sort){
	var data=data['productinfo'];
	if(sort===1){
		var sortingFieId='price'
		var low=data.sort(function(a,b){
			return a[sortingFieId]-b[sortingFieId];
		})
		removeProductImg();
		getProductImg(low);
	}else if(sort===2){
		var sortingFieId='price'
		var low=data.sort(function(a,b){
			return b[sortingFieId]-a[sortingFieId];
		})
		removeProductImg();
		getProductImg(low);
	}else if(sort===3){
		var low=data.sort(function(a,b){
			return a.description<b.description?-1 : a.description > b.description ? 1 : 0;
		})
		removeProductImg();
		getProductImg(low);
	}else if(sort===4){
		var low=data.sort(function(a,b){
			return a.description>b.description?-1 : a.description < b.description ? 1 : 0;
		})
		removeProductImg();
		getProductImg(low);
	}
}

//////////////////////
//////////////////////product 페이지 개발
function getproductId(){
	const parsedUrl = new URL(window.location.href);
	var id=parsedUrl.searchParams;
	id=id.get('id');
	return id;
}

function getDetailProductData(){
	id=getproductId();
	$.ajax({
		url : `${path}/api/getdetalipr?id=`+id,
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
				getDetailPr(data);
		}
	})
}
///가격단 설정
function setPrice(data){
	var discount=data.discountRate;
	var discounthtml=``;
	var price=data.price;
	if(discount!=0){
		var discountRate=data.discountRate/100;
		var disPrice=price*discountRate;
		var resultPrice=price-disPrice;
	
		price=price.toLocaleString('ko-KR');
		resultPrice=resultPrice.toLocaleString('ko-KR');
		discounthtml=`
		<div class="pr_price discount">
           ${price}원
        </div>
        <div class="pr_price real_price">
           ${resultPrice}원
        </div>
		`;
	}else{
		price=price.toLocaleString('ko-KR');
		discounthtml=`
		<div class="pr_price">
           ${price}원
        </div>`;
	}
	return discounthtml;
}
////사이즈 option 설정
function setSizeValue(data){
	var size=data;
	var sizehtml=``;
	if(size.length>0){
		for(var i=0;i<size.length;i++){
			sizehtml+=`<option value="${size[i].id}">${size[i].size}</option>`
		}
	}else{
		sizehtml+=`<option value="0">free</option>`
	}
	return sizehtml;
}

function setMovePage(data,idList,id){
	var data=data;
	var id=id;
	var idList=idList;
	if(data===null){
		location =`${path}/product?id=7`;
	}
	var first=idList[0].id;
	var last=idList.length-1;
	last=idList[last].id;
	var htmls=``;
	
	
	if(id==first){
		htmls+=`
		   <div class="pass_box_pn next_box">
              Next >
            </div>`
	}else if(id==last){
		htmls+=` 
		  <div class="pass_box_pn Prev_box">
              < Prev
            </div>`
	}else{
		htmls+=` <div class="pass_box_pn Prev_box">
              < Prev
            </div>
            <div class="pass_box_pn next_box">
              Next >
            </div>`
	}
	return htmls;
}

function getDetailPr(data){
	var idList=data['list'];
	var data=data['productInfo'];
	var htmls=setMovePage(data,idList,id);
	var id=getproductId();
	var discounthtml=setPrice(data);
	var size=data.productSize;
	var sizehtml=setSizeValue(size);

	var html=`   <div class="product_info_box">
          <div class="pr_img_box">
            <div class="pr_nav">
              <a href="index" class="nav_link">Home</a>
              <a href="shop" class="nav_link">Shop</a>
              <span class="nav_pr">I'm a product</span>
            </div>
            <img src="${path}/resources/img/${data.saveFileName}" alt="${data.description}">
          </div>
          <div class="pr_info">
             ${data.content}
          </div>
        </div>
       `;

	 html+=`
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
              ${data.description}
            </div>
            <div class="pr_num">
              SKU:00${data.id}
            </div>
  			<div class="pr_price_box">
              <div class="pr_price discount">
                12,000원
              </div>
              <div class="pr_price real_price">
                11,000원
              </div>
            </div>
            <div class="pr_size">
              <label class="size_in"for="size">Size</label>
              <select class="selects" name="size">
              </select>
            </div>
            <div class="pr_quantity">
              <label class="qut_in"for="qut">Quantity</label>
              <div class="qt_input_boxs">
                  <input type="text" class="qut_input"name="" value="1">
                  <div class="qt_btn qt_plus">
                    +
                  </div>
                  <div class="qt_btn qt_min">
                    -
                  </div>
              </div>
            </div>
            <div class="add_cart_box">
              <button type="button" class="cart_buy_btn add_cart_btn"name="button">Add to Cart</button>
            </div>
            <div class="buy_box">
              <button type="button" class="cart_buy_btn buy_box_btn" onclick="BuyProduct();"name="button">Buy Now</button>
            </div>
          </div>
          <div class="pr_info_wrap">
           <ul class="pr_info_ul">
              <li>
                <div class="pr_info_box">
                  <div class="info_head">
                    PRODUCT INFO
                  </div>
                  <div class="Info_section">
                    ${data.content}
                  </div>
                </div>
              </li>
              <li>
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
              </li>
              <li>
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
              </li>
            </ul>
            </div>
          </div>
        </div>`;
	$('.product_info_containers').append(html);
	var passBox=document.querySelector(".pass_box");
	var priceBox=document.querySelector('.pr_price_box');
	var selectBox=document.querySelector(".selects");
	
	priceBox.innerHTML=discounthtml;
	passBox.innerHTML=htmls;
	selectBox.innerHTML=sizehtml;
	
	
	changeDisplay();
	changeProduct();
	prodcutQtn();
	showPopup();
	setTimeout(clcikAddresve, 100);
}
//////add to cart 버튼 누를시 팝업창 실행
function showPopup(){
	var btn=document.querySelector('.add_cart_btn');
	btn.addEventListener('click',function(evt){
		var popup=document.querySelector('.popup');
		var login=document.querySelector(".text_area");
		if(login.firstElementChild.classList[0]==='login'){
			var title=document.querySelector('.title');
			var prname=document.querySelector(".pr_name").innerText;
			title.innerText=prname;
			var productobj={
				id:getproductId(),
				productSizeId:document.querySelector('.selects').value,
				count:document.querySelector('.qut_input').value
			}
			clickDelteBtn(productobj);
		}else{
			clickDelteBtn();
		}
		
		popup.style.display='block';
	
	})
}
//구매페이지 
function BuyProduct(){
	 		console.log("실행");
    		var productSizeId=document.querySelector('.selects').value;
    		var count=document.querySelector('.qut_input').value;
    		var productId=getproductId();

    		var html=` <input type="hidden" name="count" value="${count}">
    				   <input type="hidden" name="productSizeId" value="${productSizeId}">
    					<input type="hidden" name="productId" value="${productId}">
    		`;
    		
    		var form=document.querySelector('#member_form');
    	
    		$('#member_form').append(html);
    		form.submit();
 }

////예약등록
function addProductReserve(data){
	var id=data.id;
	var productSize=data.productSizeId;
	var count=data.count;
	console.log(id);
	console.log(count);
	/*
	var data = new FormData();
	data.append("count",$('.qut_input').val());
	*/
	var data={
		"count":count
	}
	$.ajax({
		url : `${path}/api/addmemberproducts?productId="+id+"&productSizeId=`+productSize,
        dataType : "json",
		contentType : false,
    	processData : false,
		headers : {
			    "Accept" : "application/json",
			    "Content-Type" : "application/json;charset=utf-8"
			},
	    type : "post",
		data:JSON.stringify(data),
		method:"post",
        success : function(data) {
				window.location.href = `${path}/members/mycart`;
		}
	})
	
}
///next,prev 누를시 주소 변경
function changeProduct(){
	var changeBox=document.querySelector('.pass_box');
	changeBox.addEventListener("click",function(evt){
		console.log(evt.target.classList[1]);
		if(evt.target.classList[1]==='Prev_box'){
			nextOrprev(1);
		}else if(evt.target.classList[1]==='next_box'){
			nextOrprev(2);
		}
	});
}
function nextOrprev(changedata){///changedata1이면 뒤로 2면 앞으로
	var id=getproductId();
	console.log(id);
	if(changedata===1){
		location =`${path}/product?id=${--id}`
	}else if(changedata===2){
		location=`${path}/product?id=${++id}`
	}
}
//////상품 수량 증감
function prodcutQtn(){
	var btn=document.querySelector('.qt_input_boxs');
	btn.addEventListener('click',function(evt){
		if(evt.target.classList[1]==='qt_plus'){
			minOrPlus(1,evt.target.previousElementSibling);
		}else if(evt.target.classList[1]==='qt_min'){
			minOrPlus(0,evt.target.previousElementSibling.previousElementSibling);
		}
	})
}
////제품소개,환불규정 디스플레이 설정
function changeDisplay(){
	var ul=document.querySelector('.pr_info_ul');
	ul.addEventListener('click',function(evt){
		if(evt.target.className==='info_head'){
			var li=document.querySelectorAll('.Info_section');
			var info=evt.target;
			if(info.nextElementSibling.style.display===''){
				for(var i=0;i<li.length;i++){
					li[i].style.display='';
				}
				info.nextElementSibling.style.display='block';
			}else if(info.nextElementSibling.style.display==='block'){
				info.nextElementSibling.style.display='';
			}
		}
	})
}
//////////////코멘트단 설정
function getDetailProductComment(){
	var id=getproductId();
	$.ajax({
		url : `${path}/api/getcomment?productId=`+id,
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
				getComment(data);
				getQnA(data);
		}
	})
}
function getComment(data){
	var avg=data['avg'];
	var count=data['count'];
	var comment=data['comment'];
	var html='';
	var reviewhead= document.querySelector('.review_head');
	reviewhead.innerText=`구매후기(${count})`;
	var score=document.querySelector('.score_num');
	score.innerText=`${avg}/`;
	
	var resultScore=[];
	if(count===0){
		var star= document.querySelector('.star_box');
		star.innerHTML=`<span>등록된 후기가 없습니다</span>`;
		html+=`<div class="review_box_wrap">
            <div class="review_named">
              등록된 코멘트가 없습니다!
            </div>
            <div class="review_detail_box">
              <div class="star_box">
              </div>
              <div class="review_text">
                코멘트를 등록해 주세요!
              </div>
            </div>
          </div>`;
	}else{
		var starwidth=avg*10*2;
		var star=document.querySelector('.star').firstElementChild;
		star.style.width=`${starwidth}%`;		
		for(var i=0;i<comment.length;i++){
			var score=comment[i].score;
			resultScore.push(score*10*2);
			var email=comment[i].email;
			email=email.substr(0,4);
			email+="****";
			
			html+=`<div class="review_box_wrap">
            <div class="review_named">
              ${email}님
            </div>
            <div class="review_detail_box">
              <div class="star_box">
                <span class="star starss">
                  ★★★★★
                  <span>★★★★★</span>
                </span>
              </div>
              <div class="review_text">
                ${comment[i].comment}
              </div>
            </div>
          </div>`;
		}
	}
	$('.reviwe_box').append(html);
	var htmlstar=document.querySelectorAll('.starss');
	for(var i=0;i<htmlstar.length;i++){
		console.log(resultScore[i]);
		htmlstar[i].firstElementChild.style.width=`${resultScore[i]}%`;
	}
}
///q&a단 구현
function getQnA(data){
	var html='';
	var comment=data['productQuestionList'];
	var qrName=document.querySelector(".pr_name").innerText;
	for(var i=0;i<comment.length;i++){
		var email=comment[i].email;
		var date=comment[i].createDate;
		var qnser=comment[i].questionAnswer;		
		if(qnser===0){
			qnser='미답변';
		}else{
			qnser='답변완료';
		}
		date=Unix_timestamp(date);
	
		email=email.substr(0,2);
		email+="***";
		if(comment[i].productQuestionAnwserDt!=null){
				html+=` <tr>
                <td class="qa_anser">${i+1}</td>
                <td class="qa_anser">${qnser}</td>
                <td class="qa_anser">${comment[i].name}</td>
                <td class="qa_ok">${comment[i].text}</a>
                <td class="qa_anser">${email}</td>
                <td class="qa_anser">${date}</td>
			<tr class="comment_head" id=${comment[i].productQuestionAnwserDt.id} style="display:none;">
                <td colspan="6">
                  <div class="content_object">
                    ${qrName}
                  </div>
                  <p>
                 	 ${comment[i].text}
                  </p>
                </td>
              </tr>
              <tr class="conect_feedback_comment " style="display:none;">
                <td class="admin_name">
                  담당자
                </td>
                <td class="admin_info" colspan="6">
                  ${comment[i].productQuestionAnwserDt.text}
                </td>
              </tr>
			`;
		}else{
			html+=` <tr>
                <td class="qa_anser">${i+1}</td>
                <td class="qa_anser">${qnser}</td>
                <td class="qa_anser">${comment[i].name}</td>
                <td>${comment[i].text}</td>
                <td class="qa_anser">${email}</td>
                <td class="qa_anser">${date}</td>
              </tr>
			`;
		}
	
	}
	var tbody=document.querySelector(".qna_body");
	tbody.innerHTML=html;
	showAnswer();
}

function clcikAddresve(){
	var btn=document.querySelector('.add_qna_btn');
	var basicHtml=document.querySelector('.qna_box').innerHTML;
	btn.addEventListener('click',function(evt){
		var html=`  <div class="qna_add_form">
            <div class="qna_category_box">
              <div class="qna_categor category_title">
                문의유형
              </div>
              <div class="qna_categor category_ex">
                <input type="radio" id="type_0" name="title_category_id" class="n-radio" value="1">
                <label for="type_0">배송</label>
                <input type="radio" id="type_1" name="title_category_id" class="n-radio" value="2">
                <label for="type_1">사이즈</label>
                <input type="radio" id="type_3" name="title_category_id" class="n-radio" value="3">
                <label for="type_3">상품상세문의</label>
              </div>
              <div class="qna_info">
                <p>-클레임(교환/환불/취소)관련 문의는 <br>마이페이지 > 1:1 문의에서 문의 바랍니다.</p>
              </div>
            </div>
            <div class="qna_category_box">
              <div class="category_title qna_categor">
               제목
              </div>
              <div class="qna_title_input qna_categor">
                <input type="text" maxlength="15" placeholder="15자 이내 입력"name="" class="title_boxs"value="">
              </div>
            </div>
            <div class="qna_box_wrap">
              <div class="category_title qna_categor">
               내용
              </div>
              <div class="qna_categor">
                <textarea name="name" rows="10" cols="68" placeholder="내용 입력"></textarea>
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
          </div>`;
		var form=document.querySelector(".qna_box");
		form.innerHTML=html;
		setbtnEvent(basicHtml)
	})
}
/////////////취소 버튼, 등록버튼 이벤트 등록
function setbtnEvent(basichtml){
	var btn=document.querySelector('.question_btn_wrap');
	btn.addEventListener('click',function(evt){
		if(evt.target.classList[0]==='question_cancle'){
			var qnaBox=document.querySelector('.qna_box');
			qnaBox.innerHTML=basichtml;
			clcikAddresve();
			showAnswer();
		}else if(evt.target.classList[0]==='question_add'){
			addQnaAjax();
		}
	})
}
////// qna등록
function addQnaAjax(){
	var productId=getproductId();
	var titleCategory=getRadioValue();
	var text=document.querySelector('textarea');
	var checkNum=validation(titleCategory,text);
	if(checkNum===1){	
			$.ajax({
			url : `${path}/api/addquestion?productId=`+productId+"&titleCategory="+titleCategory+"&text="+text.value,
	        dataType : "json",
			contentType : false,
	    	processData : false,
			headers : {
				    "Accept" : "application/json",
				    "Content-Type" : "application/json;charset=utf-8"
				},
		    type : "post",
			method:"post",
	        success : function(data) {
					window.location.href = `${path}/product?id=`+productId;
			}
		})
	}
}
function getRadioValue(){
	var radio=document.querySelectorAll('.n-radio');
	var values=0;
		for(var i=0;i<radio.length;i++){
	    if(radio[i].checked==true){
	       values=radio[i].value;
	    }
	}
	return values;
}
function validation(titleCategory,text){
	var titleBox=document.querySelector('.title_boxs');
	var checkNum=0;
	if(titleCategory===0){
		alert("문의유형을 클릭해주세요");
	}else if(text.textLength<5){
		alert("내용을 5자이상 등록해주세요");
	}else if(titleBox.value.length<5){
		alert("제목을 5자이상 등록해주세요");
	}else{
		checkNum=1;
	}
	return checkNum;
}
function showAnswer(){
	var tbod=document.querySelector('tbody');
	tbod.addEventListener('click',function(evt){
		console.log(evt.target.className);
		if(evt.target.className==='qa_ok'){
			var tr=evt.target.parentElement;
			if(tr.nextElementSibling.style.display==='none'){
				tr.nextElementSibling.style.display='';
				tr.nextElementSibling.nextElementSibling.style.display='';
			}else{
				tr.nextElementSibling.style.display='none';
				tr.nextElementSibling.nextElementSibling.style.display='none';
			}
		}
	})
}

function Unix_timestamp(t){
	t=t.toString();
	t=t.substring(0,10);
	t=parseInt(t);
	var date = new Date(t*1000);
	var year = date.getFullYear();
	var month = "0" + (date.getMonth()+1);
	var day = "0" + date.getDate();
	return year + "." + month.substr(-2) + "." + day.substr(-2) ;
}

/////////mycart 구현
//////////
/////////

////////
function getMycartProduct(){
	$.ajax({
		url : `${path}/api/getcartproduct`,
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
			getmyCartImg(data);
			///////
		}
	})
}
function getmyCartImg(data){
	var data=data['cartList'];
	var html=``;
	var totalPrice=0;
	console.log(data.length);
	if(data.length==0){
		html+=`	<p class="n-table-none">
							<sapn>장바구니 내역이 없습니다.</sapn>
						</p>`
						console.log(html);
						
		const leftHtml=document.querySelector(".cart_order_container ")
		leftHtml.remove();
		document.querySelector('.cart_head').style.width='100%';
		document.querySelector(".cart_head_name").style.width='100%';
	}else{
		for(var i=0;i<data.length;i++){
		var price=data[i].price;
		var resultprice=price.toLocaleString('ko-KR');
		var sumResultPrice=price*data[i].count;
		totalPrice+=sumResultPrice;
		sumResultPrice=sumResultPrice.toLocaleString('ko-KR');
		html+=`<li class="my_cart_list">
                <div class="cart_section">
                  <div class="cart_img_box cart_section_box">
                    <img src="${path}/resources/img/${data[i].saveFileName}" alt="${data[i].description}">
                  </div>
                  <div class="cart_section_box cart_product_info">
                    <div class="cart_product_name">
                      ${data[i].description}
                    </div>
                    <div class="cart_product_price cart_color">
                      ${resultprice}원
                    </div>
                    <div class="cart_color">
                       Size:${data[i].size}
                    </div>
                  </div>
                  <div class="cart_section_box cart_product_qut" id=${data[i].reservationInfoId}>
                    <input type="text"class="cart_input" value="${data[i].count}">
                    <div class="qtn_boxs min_box">
                      -
                    </div>
                    <div class="qtn_boxs plus_box">
                      +
                    </div>
                  </div>
                  <div class="cart_section_box cart_product_prices">
                   ${sumResultPrice}원
                  </div>
                  <div class="cart_section_box cart_delte_box" id="${data[i].reservationInfoId}">
                    X
                  </div>
                   <div class="cart_buy_btns">
                  	<input type="button" onclick="addProducts(${data[i].reservationInfoId})" value="구매하기">
                  </div> 
                </div>
              </li>`
              
	}
	changeTotalPrice(totalPrice,0);
	}
	
	console.log(html);
	
	var ul=document.querySelector('.my_cart_list_box');
	ul.innerHTML=html;
	addQtnEvent();
	
	setHeigths();
}
function addQtnEvent(){
	var ul=document.querySelector('.my_cart_list_box');
	ul.addEventListener("click",function(evt){
		var price;
		var qtn;
		console.log(evt.target);
		if(evt.target.classList[1]==='min_box'){
			qtn=minOrPlus(0,evt.target.previousElementSibling);
			price=updatePrice(evt.target.parentNode.previousElementSibling.firstElementChild.nextElementSibling);
			changePrice(price,qtn,evt.target.parentNode.nextElementSibling);
			changeTotalPrice(price,1);
		}else if(evt.target.classList[1]==='plus_box'){
			qtn=minOrPlus(1,evt.target.previousElementSibling.previousElementSibling);
			price=updatePrice(evt.target.parentNode.previousElementSibling.firstElementChild.nextElementSibling);
			changePrice(price,qtn,evt.target.parentNode.nextElementSibling);
			changeTotalPrice(price,1);
		}else if(evt.target.classList[1]==='cart_delte_box'){
			setProductName(evt.target.previousElementSibling.previousElementSibling.previousElementSibling.firstElementChild,evt.target.id);
		}
		
	})
}


function minOrPlus(type,values){
	var type=type;///0일때는 - 1일때 +
	var values=values;
	var qtn=values.value;
	if(type===0&&qtn>1){
		qtn=--qtn;
		values.value=qtn;
	}else if(type===1){
		qtn=++qtn;
		values.value=qtn;
	}
	return qtn;
}
//////가격 형변환
function updatePrice(price){
	var price=price;
	var basicPrice=price.innerText;
	var resultPrice=basicPrice.split("원")[0];
	var toPrice=changeInt(resultPrice);
	return toPrice;
}
////////가격 변환을 위환 형변환
function changeInt(price){
	var price=price;
	price=price.split(",");
	var toPrice='';
	for(var i=0;i<price.length;i++){
		toPrice+=price[i];
		toPrice=parseInt(toPrice);
	}
	return toPrice
}

//////////가격 변환
function changePrice(price,qtn,node){
	var price=price;
	var qtn=qtn;
	var node=node;
	var resultPrice=price*qtn;
	
	
	resultPrice=resultPrice.toLocaleString('ko-KR');
	resultPrice=resultPrice+"원";
	node.innerText=resultPrice;
}
/////토탈 가격 변환
function changeTotalPrice(price,start){////start 0일때는 초기값,1일땐 수량증감
	var price=price;
	var priceBox=document.querySelector('.sub_total');
	var totalPriceBox=document.querySelector(".total_price");
	var resultPrice=''
	if(start===0){
		resultPrice=price.toLocaleString('ko-KR');
	}else if(start===1){
		resultPrice=getProductPrice();
		resultPrice=resultPrice.toLocaleString('ko-KR');
	}
	
	priceBox.innerText=resultPrice;
	totalPriceBox.innerText=resultPrice;
	
}
/////////전체가격 찾기
function getProductPrice(){
	var productPrice=document.querySelectorAll('.cart_product_prices');
	var toPrice=0
		for(var i=0;i<productPrice.length;i++){
			toPrice+=updatePrice(productPrice[i]);
		}
	return toPrice;
}
////// 장바구니 삭제 텍스트 볁경
function setProductName(nameNode,id){
	var name=nameNode;
	var id=id;
	
	var popupTitle=document.querySelector('.title');
	popupTitle.innerText=name.innerText;
	
	var popup=document.querySelector(".popup");
	popup.style.display='block';
	
	clickDelteBtn(id);
}
///////x버튼 클릭시 이벤트
function clickDelteBtn(id){
	var id=id;
	console.log(id);
	var btn=document.querySelector('.btn_area');
	btn.addEventListener('click',function(evt){
		if(evt.target.classList[1]==='no'){
			var popup=document.querySelector(".popup");
			popup.style.display='none';
		}else if(evt.target.classList[1]==='yes'){
			var path=pasedUrl();
			if(path==='product'){
				if(id===undefined){
					window.location.href = `${path}/members/login`;
				}else{
					addProductReserve(id);
				}
				
			}else{
				deleteAjax(id);
			}
		}
	})
}
function setHeigths(){
	var cartProductWrap=document.querySelector('.cart_left');
	var cartWrap=document.querySelector('.cart_wrap');
	
	console.log(cartProductWrap.offsetHeight);
	console.log(cartWrap.offsetHeight);
	if(cartProductWrap.offsetHeight>cartWrap.offsetHeight){
		cartWrap.style.height=cartProductWrap.offsetHeight+"px";
	}
}
function deleteAjax(id){
	var id=id
	$.ajax({
		url : `${path}/api/deleteReserve?id=`+id,
        dataType : "json",
		method:"PUT",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
				window.location.href = `${parh}/members/mycart`;
		}
	})
}

///////////////////
//////////////////mypage
function getMyPageData(){
	id=getproductId();
	$.ajax({
		url : `${path}/api/getmylist`,
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
		 throwData(data);
		}
	})
}
function throwData(data){
	var count=data['getCount'];
	var buyInfo=data['myBuyInfo'];
	var comment=data['myComment'];
	var question=data['getMyPrQuestion'];
	getMyCount(count);
	getBuySection(buyInfo);
	getCommentSection(comment);
	getQnaSection(question);
	
}
///주문내역 조회 및에 갯수 만들기
function getMyCount(count){
	var count=count;
	var orderList=document.querySelectorAll('.my_order_list');
	for(var i=0;i<count.length;i++){
		for(var j=0;j<orderList.length;j++){
			var counts=count[i].cancleFlag;
			var order=orderList[j].dataset.category;
			if(counts==order){
				orderList[j].firstElementChild.innerText=count[i].cnt;
			}
		}
	}
}
function setPrices(price,disCount){
	var price=price;
	var disCount=disCount/100;
	
	var disPrice=price*discountRate;
	price=price-disPrice;
	///////////
	////////////
	return price;
}
function setState(flag){
	var flag=flag;
	var nameString='';
	if(flag===1){
		nameString='결제대기';
	}else if(flag===2){
		nameString='배송중';
	}else if(flag===3){
		nameString='배송완료';
	}else if(flag===4){
		nameString='구매확정';
	}else if(flag===5){
		nameString='취소';
	}
	return nameString;
}
function getBuySection(buyInfo){
	var buyInfo=buyInfo;
	var html=``;
	for(var i=0;i<buyInfo.length;i++){
		var time=Unix_timestamp(buyInfo[i].createDate);
		var price=buyInfo[i].price*buyInfo[i].count;
		if(buyInfo[i].discountRate>0){
			price=setPrices(price,buyInfo[i].disCount);
		}
		price=price.toLocaleString('ko-KR');
		
		var state=setState(buyInfo[i].cancleFlag);
		
		html+=` <tr>
                    <td>${buyInfo[i].description}</td>
                    <td>${time}</td>
                    <td id="${buyInfo[i].reservationInfoId}">${i+1}</td>
                    <td>${price}</td>
                    <td>${state}</td>
                  </tr>
				`;
	}
	var orderTable=document.querySelector('.my_order_table_tbody');
	orderTable.innerHTML=html;
}
function getQnaSection(question){
	var question=question;
	var html=``;
	for(var i=0;i<question.length;i++){
		var time=Unix_timestamp(question[i].createDate);
		var anwer="";
		if(question[i].questionAnswer===0){
			anwer="미답변";
		}else if(question[i].questionAnswer===1){
			anwer="답변완료";
		}
		html+=`    <tr>
                    <td>${question[i].description}</td>
                    <td>${question[i].text}</td>
                    <td>${question[i].name}</td>
                    <td>${time}</td>
                    <td>${anwer}</td>
                  </tr>
			`;
	}
	var qnaTable=document.querySelector('.my_product_qna_table_tbody');
	qnaTable.innerHTML=html;
}
function getCommentSection(comment){
	var comment=comment;
	var html=``;
	for(var i=0;i<comment.length;i++){
		html+=` <tr id="${comment[i].reservationUserComment}">
                    <td>${comment[i].description}</td>
                    <td>${comment[i].comment}</td>
                    <td>${comment[i].score}</td>
                  </tr>
			`;
	}
	console.log(html);
	var commnetTable=document.querySelector('.my_buy_product_table_tbody');
	commnetTable.innerHTML=html;
}
/////////////////////
/*받아온 상품 정보 화면에 뿌리기*/
const setProductImage=(data)=>{
	const product=data['categoryProduct'];
	var html=``;
	
	product.forEach(ele=>{
		html+=`		<li class="product_img_item">
            <a href="#">
            <img src="${path }/resources/img/${ele.saveFileName}" alt="${ele.description }">
          </a>
          <div class="product_view"  onClick="setSelectProduct(this);" style="display:block; width:270px;">
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
	
	$('.product_img_ul').empty();
	$('.product_img_ul').append(html);
	
	const paging =data['paging'];
	
	let subHtml=``;
	
	if(paging.endPage !=1 && paging.nowPage !=1){
		subHtml+=`<a href="${path }/admin/administerPromotion?nowPage=${paging.nowPage-1 }&cntPerPage=${paging.cntPerPage}" class="paging-btn prev"></a> `;
	}
	for(let i=paging.startPage;i<paging.endPage;i++){
		if(i==paging.nowPage){
			subHtml+=`<b class="paging-btn">${i}</b>`;
		}
		subHtml+=`<a class="paging-btn" href="${path }/admin/administerPromotion?nowPage=${i+1}&cntPerPage=${paging.cntPerPage}">${i+1}</a>`
	
	}
	if(paging.endPage != 1 && paging.nowPage != paging.lastPage){
		subHtml+=`<a href="${path }/admin/administerPromotion?nowPage=${paging.nowPage+1 }&cntPerPage=${paging.cntPerPage}" class="paging-btn next"></a> `;
	}
	$('.wrapper').empty();
	$('.wrapper').append(subHtml);
	
}
////////////////////////orderform page 스크립트 개발
/*
function getMycartData(){
		$.ajax({
		url : "http://localhost:8080/healthproject/api/getbuyPr",
        dataType : "json",
		method:"GET",
	    contentType: "application/json; charset=utf-8",
        success : function(data) {
				getOrderTempleat(data);
		}
	})
}
function getOrderTempleat(data){
	var data=data['mycart'];
	var html=`   <tr>
                  <td>
                    <div class="order_pr_img_box order_left">
                      <a><img src="http://localhost:8080/healthproject/resources/img/${data.saveFileName}" alt=""></a>
                    </div>
                    <div class="order_left order_pr_info_boxs">
                      <h2>${data.description}</h2>
                      <div class="order_option_box">
                        <p>옵션 : ${data.size}</p>
                      </div>
                    </div>
                  </td>
                  <td class="order_qtn"><strong>${data.count}개</strong></td>
                  <td class="order_qtn table_background">
                    <span>무료</span> <br>
                    <span class="order_cor">반송비 확인</span>
                  </td>
                  <td class="order_qtn table_background">
               `;

	var price= data.price;
	var count=data.count;
	price=price*count;
	
	var resultPrice=0;
	var disCount=data.discountRate;
	var toPrice=price.toLocaleString('ko-KR');
	
	if(disCount>0){
		resultPrice=setPrices(price,disCount);
		resultPrice=resultPrice.toLocaleString('ko-KR');
		html+=` <span class="sale_prices">${toPrice} 원</span>
                    <span>${resultPrice} 원</span>
                  </td>
                </tr>`
	}else{
		html+=`
                    <span>${toPrice} 원</span>
                  </td>
                </tr>`;
	}
	document.querySelector('.order_tnodys').innerHTML=html;
	nextNumber();
	checkHomeNumber();
	showTextarea();
	clickBtnEvent();
	allCheck();
}
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
*/

////////////////////
/////////////////////////
document.addEventListener("DOMContentLoaded",function(){
	var path=pasedUrl();
	if(path==='index'){
		clickName();
		moveText();
		getData();
		getProduct(0);
		addProduct();
	}else if(path==='authenticate'){
		moveText();
		clickName();
		locationAddmemeber();
		checkLoginSubmit();
	}else if(path==='shop'){
		moveText();
		clickName();
		getProductData();
		clickCategory();
	}else if(path==='product'){
		clickName();
		moveText();
		getDetailProductData();
		getDetailProductComment();
	}else{
		clickName();
		moveText();
		var scpath=scpasedUrl();
		if(scpath==='loginform'){
			locationAddmemeber();
			checkLoginSubmit();
		}else if(scpath==='joinform'){

		}else if(scpath==='mycart'){
			getMycartProduct();
			
		}else if(scpath==='mypage'){
		    getMyPageData();
		}else if(scpath==='orderform'){
			//getMycartData();
		}
		
	}

});