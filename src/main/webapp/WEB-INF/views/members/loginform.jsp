<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<c:set var="path" value="${pageContext.request.contextPath}" />
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

</script>
<style>
	.kakaoLogin{
	   border-color: #fee500;
   	   background-color: #fee500;
   	   color: black;
   	   font-weight: bold;
	}
	.submit_box{
		position: relative;
	}
	svg{
		position: absolute;
		top: 15.5px;
   		 left: 167px;
	}
	.test_box{
		position: absolute;
   	 	top: 18px;
	}
	.login_form{
		position: relative;
	}
	
</style>
<div class="login_wrap">
	
	<div class="login_container">
		<div class="login_form">
			<div class="test_box">
				<legend>테스트계정</legend>
	
				<div>
					<input onClick="setTestLogin(this)" type="radio" id="member" name="drone" value="member" checked>
					<label for="member">Member</label>
				</div>
				<div>
					<input onClick="setTestLogin(this)" type="radio" id="admin" name="drone" value="admin"> <label
						for="admin">Admin</label>
				</div>
			</div>
			<form method="post" id="login_forms"
				action="/healthproject/authenticate">
				<div class="login_box id_box">
					<input type="text" class="form_box" id="login_id" placeholder="아이디"
						name="userId">
				</div>
				<div class="login_box">
					<input type="password" class="form_box" id="login_password"
						placeholder="비밀번호" name="password">
				</div>
				<div class="submit_box">
					<input type="submit" class="form_box sub_from" value="로그인">
				</div>
				<div class="submit_box">
					<input type="button" onclick="kakaoLogin()"
						class="form_box sub_from kakaoLogin" value="카카오로그인">
					<svg width=30 height="30" xmlns="http://www.w3.org/2000/svg">
						<path file-rule="evenodd" clip-rule="evenodd"
							d="M15 7C10.029 7 6 10.129 6 13.989C6 16.389 7.559 18.505 9.932 19.764L8.933 23.431C8.845 23.754 9.213 24.013 9.497 23.826L13.874 20.921C14.243 20.958 14.618 20.978 15 20.978C19.971 20.978 24 17.849 24 13.989C24 10.129 19.971 7 15 7Z"
							file="black"></path>
					</svg>
				</div>
				<!-- <input type="button" onclick="kakaoLogout()" value="테스트로그아웃"> -->
			</form>
		</div>
	</div>
	<div class="login_info">
		<div class="login_info_wrap">
			<ul class="ul_box">
				<li class="info_list"><span>비밀번호 찾기</span></li>
				<li class="info_list"><span>아이디 찾기</span></li>
				<li class="info_list"><span>회원가입</span></li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript" src="${path }/resources/js/kakaoLogin.js"></script>
<script>
	const setTestLogin =(target)=>{
		const loginId=document.querySelector('#login_id');
		const loginPw=document.querySelector('#login_password');
		
		if(target.id==="member"){
			loginId.value='qotkdrb@naver.com';
			loginPw.value='wnsvaf309';
		}else if(target.id="admin"){
			loginId.value='uiwv29l@naver.com';
			loginPw.value='wnsvaf309';
		}
	}
</script>
