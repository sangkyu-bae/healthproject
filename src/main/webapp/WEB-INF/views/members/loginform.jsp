<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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

