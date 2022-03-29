/*
   function requestPay(tel,recipt,realPrice,count,name) {
	IMP.request_pay({
	    pg : 'html5_inicis',
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : '주문명:결제테스트',
	    amount : 10,
	    buyer_email : 'iamport@siot.do',
	    buyer_name : '구매자이름',
	    buyer_tel : '010-1234-5678',
	    buyer_addr : '서울특별시 강남구 삼성동',
	    buyer_postcode : '123-456'
	}, function(rsp) {
	    if ( rsp.success ) {
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	    }
	
	    alert(msg);
	});
}
*/
 function requestPay(tel,recipt,name,buyName,homeNumber,deliveryNote,path,cartDate) {
	IMP.request_pay({
	    pg : 'html5_inicis',
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : name,
	    amount : 10,
	    buyer_email : '',
	    buyer_name :buyName,
	    buyer_tel : tel,
	    buyer_addr : recipt,
	    buyer_postcode : '123-456'
	}, function(rsp) {
	    if ( rsp.success ) {
			var cart=cartDate['cart'];
			var list=[];
			for(var i=0;i<cart.length;i++){
				var data={
					reservationInfoId:cart[i].reservationInfoId,
					orderAddress:recipt,
					recipient:buyName,
					deliveryNote:deliveryNote,
					homePhone:homeNumber,
					cellPhone:tel,
					impUid:rsp.imp_uid,
					merchantUid:rsp.merchant_uid
				}
				list.push(data);
			}
			
			var url =path+"/api/insertOrderList"
			console.log(path);
			$.ajax({
				dataType : "json",
				contentType : false,
		    	processData : false,
				headers : {
					    "Accept" : "application/json",
					    "Content-Type" : "application/json;charset=utf-8"
					},
			    type : "post",
				data:JSON.stringify(list),
				url : url,
			    contentType: "application/json; charset=utf-8",
		        success : function(data) {
						alert("결제가 완료되었습니다.");
						window.location.href =path+'/members/mypage';
					
				}
			})
			/*
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	        */
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	        alert(msg);
	    }
	
	    
	});
}