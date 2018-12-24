package com.nonage.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.controller.action.Action;
import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;

public class AdminProductListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "admin/adminIndex.jsp";
		String key = request.getParameter("key");
		String tpage = request.getParameter("tpage");

		//key : 검색키워드
		//tpage : 타겟 페이지
		if (key == null)
			key = "";
		
		if (tpage == null)
			tpage = "1";
		else if (tpage.equals(""))
			tpage = "1";

		request.setAttribute("key", key);
		request.setAttribute("tpage", tpage);
		
		
		ArrayList<ProductVO> productList = null;
		ProductDAO productDAO = ProductDAO.getInstance();

		try {
//			productList = productDAO.selectProduct();
			productList = productDAO.listProduct(Integer.parseInt(tpage), key);
			String paging = productDAO.pageNumber(Integer.parseInt(tpage), key);
			request.setAttribute("productList", productList);
			
			int n = productList.size();
			request.setAttribute("productListSize", n);
			request.setAttribute("paging", paging);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		request.getRequestDispatcher(url).forward(request, response);

	}

}
