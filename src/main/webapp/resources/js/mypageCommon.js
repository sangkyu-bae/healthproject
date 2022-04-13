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
			var checkPeriod=getCheckPeriod();
			
			if(startCheck===true&&lastCheck===true){
				var period=document.querySelector("#period");
				period.value=checkPeriod;
				document.querySelector('#frm').submit();
				
			}else{
				alert("날짜 형식이 잘못되었습니다.");
			}
		}
	}
	/*조회 기간 확인*/
	function getCheckPeriod(){
		var label= document.querySelectorAll('label');
		var check;
		for(var i=0;i<label.length;i++){
		  	if(label[i].className=='clicks'){
		        check=i;
		        break;
			}	
		}
		return check;
	}
	/*검색 기간 보더 주기 위한 확인 */
	function getCheckPeriod(){
		let params = (new URL(document.location)).searchParams;
		let period = params.get('period'); 
		var label= document.querySelectorAll('label');
		
		if(period==0){
			label[0].classList.add('clicks');
		}else if(period==1){
			label[1].classList.add('clicks');
		}else if(period==2){
			label[2].classList.add('clicks');
		}else{
			label[3].classList.add('clicks');
			
		}
	
	}
	document.addEventListener("DOMContentLoaded",function(){
		getCheckPeriod();
	})
	