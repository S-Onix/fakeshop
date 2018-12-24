package com.nonage.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.dao.CartDAO;

public class CartDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "NonageServlet?command=cart_list";
		String[] checked = request.getParameterValues("cseq");
		CartDAO cartDAO = CartDAO.getInstance();
		try {
			for (String cseq : checked) {
				cartDAO.deleteCart(Integer.parseInt(cseq));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

}
