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

public class OrderAllAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "mypage/mypage.jsp";
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginUser");
		OrderDAO orderDAO = OrderDAO.getInstance();
//		ArrayList<ArrayList<OrderVO>> orderList = null;
		ArrayList<OrderVO> orderList = null;
		ArrayList<OrderVO> list = null;
		ArrayList<Integer> oseqList= null;
		int totalPrice = 0;
		if(loginMember == null) {
			url ="NonageServlet?command=login_form";
		}else {
			 try {
				 oseqList = orderDAO.selectSeqOrderList(loginMember.getId());
				 orderList = new ArrayList<>();
				 for(Integer oseq : oseqList) {
//					 orderList.add(orderDAO.selectOrderList(loginMember.getId(), oseq, "1"));
					 list = orderDAO.selectOrderList(loginMember.getId(), oseq, "%");
					 OrderVO orderVO = list.get(0);
					 orderVO.setPname(orderVO.getPname() + " 외 " + (list.size()-1) + "건");
					 for(OrderVO order : list) {
						 totalPrice += order.getPrice2()*order.getQuantity();
					 }
					 orderVO.setPrice2(totalPrice);
					 orderList.add(orderVO);
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("title", "총 주문 내역");
		request.setAttribute("orderList", orderList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
