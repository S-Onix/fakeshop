package com.nonage.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.QnaDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

public class QnaWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "NonageServlet?command=qna_list";
		HttpSession session = request.getSession();
		MemberVO loginMember= (MemberVO) session.getAttribute("loginUser");
		
		if(loginMember == null) {
			url = "NonageServlet?command=login_form";
		}else {
			QnaDAO qnaDAO = QnaDAO.getInstance();
			QnaVO qnaVO = new QnaVO();
			qnaVO.setSubject(request.getParameter("subject"));
			qnaVO.setContent(request.getParameter("content"));
			try {
				qnaDAO.insert(qnaVO, loginMember.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect(url);
		
	}

}
