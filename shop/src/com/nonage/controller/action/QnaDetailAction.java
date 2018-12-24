package com.nonage.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.QnaDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

public class QnaDetailAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "qna/qnaDetail.jsp";
		
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginUser");
		
		if(loginMember == null) {
			url = "NonageServlet?command=login_form";
		}else {
			int qseq = Integer.parseInt(request.getParameter("qseq").trim());
			QnaDAO qnaDAO = QnaDAO.getInstance();
			QnaVO qnaVO = null;
			try {
				qnaVO = qnaDAO.getQna(qseq);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("qna", qnaVO);
		}
		
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
