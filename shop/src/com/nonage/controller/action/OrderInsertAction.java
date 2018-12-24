package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.CartDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

public class OrderInsertAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "NonageServlet?command=order_list";
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO)session.getAttribute("loginUser");
		CartDAO cartDAO = CartDAO.getInstance();
		OrderDAO orderDAO = OrderDAO.getInstance();
		
		if(loginMember == null) {
			url = "NonageServlet?command=login_form";
		}else {
			try {
				// 1. 카트 내역 객체화 하기
				ArrayList<CartVO> cartList = cartDAO.listCart(loginMember.getId());	
				// 2. order db 및 order_detail db 데이터 넣어주기
				int maxOseq = orderDAO.insert(loginMember.getId(), cartList);
				url = "NonageServlet?command=order_list&oseq=" + maxOseq;
				
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.sendRedirect(url);
		
				
	}

}
