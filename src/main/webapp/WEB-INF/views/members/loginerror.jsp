<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<div class="login_wrap">
	<div class="login_container">
		<div class="login_form">
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
			</form>
			<div class="error_form">
				<span>아이디 또는 비밀번호가 잘못 입력 되었습니다.</span><br> <span><span
					class="bold_colr">아이디</span>와 <span class="bold_colr">비밀번호</span>를
					정확히 입력해 주세요.</span>
			</div>
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

