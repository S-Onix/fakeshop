package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.OrderDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;

public class OrderListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "mypage/orderList.jsp";
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginUser");
		ArrayList<OrderVO> orderList = null;
		int totalPrice = 0;
		int oseq= 0;
		if(loginMember == null) {
			url = "NonageServlet?command=login_form";
		}else {
			try {
				OrderDAO orderDAO = OrderDAO.getInstance();
				String oseqStr = request.getParameter("oseq");
				oseq = Integer.parseInt(oseqStr);
				orderList = orderDAO.selectOrderList(loginMember.getId(), oseq, "1");
				for(OrderVO orderVO : orderList) {
					totalPrice += orderVO.getPrice2()*orderVO.getQuantity();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("orderList", orderList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
