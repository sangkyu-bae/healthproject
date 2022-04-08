<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>

<!-- 공통변수 처리 -->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<script>
	var path=`${path}`
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta charset="UTF-8">
	<title><tiles:insertAttribute name="title" /></title>
    <link href="${path}/resources/css/reset.css" rel="stylesheet" type="text/css">
    <link href="${path}/resources/css/index.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  </head>
  
  <body>
  		<header>
  			<tiles:insertAttribute name="header" />	
  		</header>
  		<section>
  			<tiles:insertAttribute name="body"/>
  		</section>
  		<footer>
  			<tiles:insertAttribute name="foot" />
  		</footer>
  </body>
  <script type="text/javascript" src ="${path }/resources/js/addressapi.js"></script>
  <script type="text/javascript" src ="${path }/resources/js/common.js"></script>
   <script type="text/javascript" src ="${path }/resources/js/index.js"></script>
</html>