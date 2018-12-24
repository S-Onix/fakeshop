<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="sub_img.html"%>
<%@ include file="sub_menu.html"%>

<article>
	<h2>Join Us</h2>
	<form action="NonageServlet?command=join_form" id="join" method="post"
		name="formm">
	<br> 제 3자에게 제공되지 않으며, 철저히 보호되고 있사오니 안심하고 이용하시기 바랍니다. <br> <br>
	<textarea cols="100" rows="15">
		제 22 조 (재판권 및 준거법)
		(1) 이 약관에 명시되지 않은 사항은 전기통신사업법 등 관계법령과 상관습에 따릅니다.
	</textarea>
	<br>
	<br>
	<div style="text-align: center;">
		<input type="radio" name="okon1" checked > 동의함 &nbsp;&nbsp;&nbsp;
		<input type="radio" name="okon1" checked > 동의안함
	</div>
	<input type="button" value="Next" class="submit" onclick="go_next()" style="float: right;"/>
	</form>
</article>

<%@ include file="../footer.jsp" %>