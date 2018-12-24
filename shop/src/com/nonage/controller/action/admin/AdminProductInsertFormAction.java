package com.nonage.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nonage.controller.action.Action;

public class AdminProductInsertFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "NonageServlet?command=product_insert_form";	
		
		request.getRequestDispatcher(url).forward(request, response);
		
		
		
	}

}
