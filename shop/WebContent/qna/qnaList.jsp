<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<%@ include file="sub_img.html"%>
<%@ include file="sub_menu.jsp"%>

<article>
	<h2>1:1 고객 게시판</h2>
	<form action="" name="formm" method="post">
		<h3>고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>
		<table id="cartList">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
				<th>답변 여부</th>
			</tr>
								
			<c:forEach items="${qnaList}" var="qnaVO">
			<tr>
				<td>${qnaVO.qseq}</td>
				<td><a href="NonageServlet?command=qna_detail&qseq=${qnaVO.qseq }"><span style="color: red">${qnaVO.subject}</span> </a></td>
				<td>
					<fmt:formatDate value="${qnaVO.indate}" type="date"/>
				</td>
				
				<td>
					<c:choose>
						<c:when test="${qnaVO.rep =='1'}"> NO</c:when>
						<c:otherwise>YES</c:otherwise>
					</c:choose>
				</td>
			</tr>	
			</c:forEach>
			
		</table>
		
		<div class="clear"></div>
		<div id="buttons" style="float: right;">
			<!-- 글쓰기 경로 추가 -->
			<input type="button" value="1:1 질문하기" class="submit" onclick="location.href='NonageServlet?command=qna_write_form'"/>
			<input type="button" value="쇼핑 계속하기" class="cancel" onclick="location.href='NonageServlet?command=index'" />
		</div>
	</form>
</article>

<%@ include file="../footer.jsp" %>