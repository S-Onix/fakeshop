package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.QnaDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.QnaVO;

public class QnaListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/qna/qnaList.jsp";
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginUser");
		
		if(loginMember == null) {
			url = "NonageServlet?command=login_form";
		}else {
			try {
				QnaDAO qnaDAO = QnaDAO.getInstance();
				ArrayList<QnaVO> qnaList = qnaDAO.listQna(loginMember.getId());
				request.setAttribute("qnaList", qnaList);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
