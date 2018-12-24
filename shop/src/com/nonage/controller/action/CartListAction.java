package com.nonage.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.dao.CartDAO;
import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;

public class CartListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "mypage/cartList.jsp";
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginUser");
		if(loginMember == null) {
			url = "NonageServlet?command=login_form";
		}else {
			try {
				CartDAO cartDAO = CartDAO.getInstance();
				ArrayList<CartVO> cartList = cartDAO.listCart(loginMember.getId());
				int totalPrice = 0;
				for(CartVO cartVO : cartList) {
					totalPrice+= cartVO.getPrice2() * cartVO.getQuantity();
				}
				
				request.setAttribute("cartList", cartList);
				request.setAttribute("totalPrice", totalPrice);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
