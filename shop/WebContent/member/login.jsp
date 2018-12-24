<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="sub_img.html"%>
<%@ include file="sub_menu.html"%>

<article>
	<h1>Login</h1>
	<form action="NonageServlet?command=login" method="post" >
		<fieldset>
			<legend></legend>
			<label for="">User ID</label>
			<input type="text" name="id" value="${id}">
			<br>
			<label for="">Password</label>
			<input type="password" name="pwd" />
			<br />
		</fieldset>
		<div id="clear"></div>
		<div id="buttons">
			<input type="submit" value="로그인" class="submit">
			<input type="button" value="회원가입" class="cancel"
				onclick="location='NonageServlet?command=join_form'">
		<!-- 	<input type="button" value="아이디 비밀번호 찾기" class="submit"
				onclick="location='NonageServlet?command=find_id_form'" >
		 -->
		 </div>
	</form>
</article>

<%@ include file="../footer.jsp" %>