<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin page</title>
<link href="../css/admin.css" rel="stylesheet">
<script type="text/javascript" src="admin/admin.js"></script>

</head>
<body>
<header>
<div id="wrap">
	<!-- 헤더파일 들어가는 곳 시작 -->
	
		<nav id="catagory_menu" style="float: right">
		<ul>
			<li><input type="button" value="logout" onclick="location.href='NonageServlet?command=logout"/></li>
		</ul>
	</nav>
	<div style="clear: both;"></div>
	<hr>
	</header>
