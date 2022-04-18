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
		var period=document.querySelector("#period");
		if(lastDay.value==""&&startDay.value==""){
			period.value=4;
			document.querySelector('#frm').submit();
			
		}else{
			var startCheck=checkDay.test(startDay.value);
			var lastCheck=checkDay.test(lastDay.value);
			var checkPeriod=getCheckPeriods();
			
			if(startCheck===true&&lastCheck===true){
				period.value=checkPeriod;
				document.querySelector('#frm').submit();
				
			}else{
				alert("날짜 형식이 잘못되었습니다.");
			}
		}
	}
	/*조회 기간 확인*/
	function getCheckPeriods(){
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
		console.log("period"+period);
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
	
	/*답변 완료된 qa 목록 클릭시 비동기로 답변 불러오기*/
	function getQaAnwser(productQuestionId,element){
		var nextElement=element.nextElementSibling;
		
		if(nextElement.className=='comment_head'){
			if(nextElement.style.display==''||nextElement.style.display=='block'){
				nextElement.style.display='none';
				nextElement.nextElementSibling.style.display='none';
			}else{
				nextElement.style.display='';
				nextElement.nextElementSibling.style.display='';
			}
		}else{
			$.ajax({
				url : `${path}/api/getQaAnwser?productQuestionId=`+productQuestionId,
		        dataType : "json",
				contentType : false,
		    	processData : false,
				headers : {
					    "Accept" : "application/json",
					    "Content-Type" : "application/json;charset=utf-8"
					},
			    type : "get",
				method:"get",
		        success : function(data) {
						addQaAnwser(data,element);
				}
			})
		}
	}
	/*qa 답변 뷰화면에 붙이기 */
	function addQaAnwser(data,element){
		var html=`<tr class="comment_head"  >
							                <td colspan="6" style="text-align: left">
							                  <div class="content_object">
							                    ${data.description}
							                  </div>
							                  <p>
							                 	 ${data.questionText}
							                  </p>
							                </td>
						              </tr>
						              <tr class="conect_feedback_comment " >
						                <td class="admin_name" style="text-align: left">
						                  담당자
						                </td>
						                <td class="admin_info" colspan="6" style="text-align: left">
						                  ${data.anwserText}
						                </td>
						              </tr>	`
		$(element).after(html);			      
	}
	
	document.addEventListener("DOMContentLoaded",function(){
		getCheckPeriod();
	})
	