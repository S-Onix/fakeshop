package com.nonage.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.MemberDAO;
import com.nonage.dto.MemberVO;

public class JoinAction implements Action{

	//DB에 데이터 저장
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 클라이언트측으로부터 얻어온 데이터를 가지고 DB에 저장
		String url = "/member/login.jsp";
		HttpSession session = request.getSession();
		MemberVO memberVO = new MemberVO();
		memberVO.setId(request.getParameter("id"));
		memberVO.setPwd(request.getParameter("pwd"));
		memberVO.setName(request.getParameter("name"));
		memberVO.setEmail(request.getParameter("email"));
		memberVO.setZipNum(request.getParameter("zipNum"));
		memberVO.setAddress(request.getParameter("addr1") + request.getParameter("addr2"));
		memberVO.setPhone(request.getParameter("phone"));
		
		session.setAttribute("id", request.getParameter("id"));
		
		MemberDAO memberDAO = MemberDAO.getInstance();
		try {
		memberDAO.insertMember(memberVO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
