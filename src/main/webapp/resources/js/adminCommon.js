/*행사 상품 등록 폼검사*/
const setNumberBox=()=>{

	const productId=[];
	let productIdBox = ``;
	
	productChooes.forEach(function(item){
		productId.push({"productId":item.id});
		var id=item.id
		console.log(id);
		productIdBox+=`<input type="hidden" class="prid" name="productId" value="${id}">`
	})
	/*
	for(var i=0; i<productChooes.length;i++){
		console.log(productChooes[i].id);
		productIdBox+=`<input type="hidden" name="productId" value="5">`;
	}*/
	
	const frm=document.querySelector('#frm');
	
	
	$("#frm").append(productIdBox);
	console.log(document.querySelector('.prid'));
	frm.submit();
}

const displayPopup =()=>{
	const discountRate=document.querySelector('#discountRate');
	const productChooes=document.querySelectorAll('.chooes');
	
	if(discountRate.value<0||discountRate.value===''){
		alert("할인율은 0% 이상입니다.");
		discountRate.focus();
		return false;
	}else if(productChooes.length<1){
		alert("선택된 상품이 없습니다.");
		return false;
	}
	
	const popup=document.querySelector(".popup");
	
	popup.style.display='block';
}

const displayNone=()=>{
	const popup=document.querySelector(".popup");
	
	popup.style.display='none';
}

