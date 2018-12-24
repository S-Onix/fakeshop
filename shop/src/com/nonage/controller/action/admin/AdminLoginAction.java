package com.nonage.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nonage.controller.action.Action;
import com.nonage.dao.admin.WorkerDAO;
import com.nonage.dto.admin.WorkerVO;

public class AdminLoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "admin/login_fail.jsp";
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pwd = request.getParameter("password");
		WorkerDAO workerDAO = WorkerDAO.getInstance();
		WorkerVO workerVO = null;
		
		try {
			workerVO = workerDAO.checkWorker(id, pwd);
			if(workerVO != null) {
				session.setAttribute("adminUser", workerVO);
				url="NonageServlet?command=admin_index";
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
