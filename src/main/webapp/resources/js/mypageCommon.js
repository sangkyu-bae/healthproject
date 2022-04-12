/*검색 기간 클릭시 날짜 세팅*/
	function setPriod(period){
		var lastDay=document.querySelector("#lasts");
		var startDay=document.querySelector('#starts');
		if(period=="all"){
			lastDay.value="";
			startDay.value="";
		}else{
			var now = new Date();
			var year=now.getFullYear();
			var month = now.getMonth()+1;
			var day=now.getDate();
			var format = year+"."+(("00"+month.toString()).slice(-2))+"."+(("00"+day.toString()).slice(-2));
			lastDay.value=format;
			
			if(period=="1week"){
				now.setDate(now.getDate()-7);
			}else if(period=="1month"){
				now.setMonth(now.getMonth()-1);
			}else if(period=="3month"){
				now.setMonth(now.getMonth()-3);
			}
			var startYear=now.getFullYear();
			var startMonth = now.getMonth()+1;
			var startDays=now.getDate();
			
			format= startYear+"."+(("00"+startMonth.toString()).slice(-2))+"."+(("00"+startDays.toString()).slice(-2));
			
			startDay.value=format;
		}
	}
	
	/*검색 기간 클릭스 보더 설정*/
	function setBorder(element){
		var label=document.querySelectorAll('label');
		
		for(var i=0;i<label.length;i++){
			if(label[i].className=='clicks'){
				label[i].classList.remove('clicks');
				break;
			}	
		}
		element.classList.add("clicks");
	}
	
	/*조회 버튼 클릭시 조회*/
	function search(){
		var lastDay=document.querySelector("#lasts");
		var startDay=document.querySelector('#starts');
		const checkDay=/\d{4}.\d{2}.\d{2}/;
		if(lastDay.value==""&&startDay.value==""){
			document.querySelector('#frm').submit();
			
		}else{
			var startCheck=checkDay.test(startDay.value);
			var lastCheck=checkDay.test(lastDay.value);
			
			if(startCheck===true&&lastCheck===true){
				document.querySelector('#frm').submit();
			}else{
				alert("날짜 형식이 잘못되었습니다.");
			}
		}
	}
	
	function getCheckPeriod(){
		var a= document.querySelectorAll('label');
		for(var i=0;i<a.length;i++){
		  	if(a[i].className=='clicks'){
		        
			}
		}
	}