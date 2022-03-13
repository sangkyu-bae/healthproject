<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="join_form_box">
	<div class="join_form_container">
		<form method="post" action="/healthproject/members/join" id="join_sub">
			<div class="join_box">
				<label for="email" class="join_label">이메일</label><br> <input
					id="email" class="join_input" type="text" name="email">
			</div>
			<div class="join_box">
				<label for="password" class="join_label">비밀번호</label><br> <input
					id="password" class="join_input" type="password" name="password">
			</div>
			<div class="join_box">
				<label for="password2" class="join_label">비밀번호 재확인</label><br>
				<input id="password2" class="join_input" type="password">
			</div>
			<div class="join_box">
				<label for="name" class="join_label">이름</label><br> <input
					id="name" class="join_input" type="text" name="name">
			</div>
			<div class="join_box">
				<label for="phone" class="join_label">핸드폰 번호</label><br> <input
					id="phone" class="join_input" type="text" name="phone">
			</div>
			<div class="join_box">
				<label for="address" class="join_label">주소</label><br> <input
					id="address" class="join_input" type="text" name="address">
			</div>
			<div>
				<label></label>
				<button type="button" onclick="checkSubmit();" class="join_submit">회원가입</button>
			</div>
		</form>
	</div>
</div>
