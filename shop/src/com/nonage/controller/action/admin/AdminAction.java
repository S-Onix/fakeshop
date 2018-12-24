package com.nonage.controller.action.admin;

import com.nonage.controller.action.Action;

public class AdminAction {
	public Action action(String command) {
		Action action = null;
		if(command.equals("admin_login_form")) {
			action = new AdminLoginFormAction();
		}else if(command.equals("admin_login")) {
			action = new AdminLoginAction();
		}else if(command.equals("admin_index")) {
			action = new AdminProductListAction();
		}else if(command.equals("admin_product_search")) {
			action = new AdminProductSearchAction();
		}
		
		return action;
	}
}
