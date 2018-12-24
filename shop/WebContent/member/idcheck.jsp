<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복 체크</title>
<!-- <link rel="stylesheet" href="CSS/subpage.css" /> -->
<style type="text/css">
	body{
		background-color: #B96DB5;
		font-family: Verdana;
	}
	
	#wrap{
		margin: 0 20px;
	}
	
	h1{
		font-family: "Times New Roman", times, serif;
		font-size: 45px;
		color: #CCC;
		font-weight: normal;	
	}
	
	input[type=button], input[type=submit]{
		float: right;
	}
</style>

<script type="text/javascript">
	function idok(){
		opener.formm.id.value="${id}";
		opener.formm.reid.value="${id}";
		self.close();
	}
</script>
</head>
<body>
<div id="wrap">
	<h1>ID 중복확인</h1>
	<form action="NonageServlet?command=id_check_form" method="post" name="formm" style="margin-right: 0">
		User ID <input type="text" name="id" value="" size="15"/>
				<input type="submit" value="검 색" class="submit"/><br>
				
		<div style="margin-top: 20px">
			<c:if test="${message == 1}">
				<script type="text/javascript">
					opener.document.formm.id.value="";
				</script>
				${id}는 이미 사용중인 아이디입니다.
			</c:if>
			<c:if test="${message == -1}">
				${id}는 사용 가능한 아이디입니다.
				<input type="button" value="사용" class="cancel" onclick="idok()" />
			</c:if>
		</div>
	</form>
</div>
</body>
</html>