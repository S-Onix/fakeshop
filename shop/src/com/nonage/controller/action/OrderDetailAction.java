package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.OrderDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;

public class OrderDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "mypage/orderDetail.jsp";

		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginUser");

		if (loginMember == null) {
			url = "NonageServlet?command=login_form";
		} else {
			int oseq = Integer.parseInt(request.getParameter("oseq"));
			OrderDAO orderDAO = OrderDAO.getInstance();
			ArrayList<OrderVO> orderList = null;
			int totalPrice = 0;
			try {
				orderList = orderDAO.selectOrderList(loginMember.getId(), oseq, "%");
				for (OrderVO order : orderList) {
					totalPrice += order.getPrice2() * order.getQuantity();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("orderDetail", orderList.get(0));
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

}
