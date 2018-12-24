package com.nonage.controller;

import com.nonage.controller.action.Action;
import com.nonage.controller.action.CartDeleteAction;
import com.nonage.controller.action.CartInsertAction;
import com.nonage.controller.action.CartListAction;
import com.nonage.controller.action.ContractAction;
import com.nonage.controller.action.CustomerAction;
import com.nonage.controller.action.FindZipNumAction;
import com.nonage.controller.action.IdCheckFormAction;
import com.nonage.controller.action.IndexAction;
import com.nonage.controller.action.JoinAction;
import com.nonage.controller.action.JoinFormAction;
import com.nonage.controller.action.LoginAction;
import com.nonage.controller.action.LoginFormAction;
import com.nonage.controller.action.LogoutAction;
import com.nonage.controller.action.MyPageAction;
import com.nonage.controller.action.OrderAllAction;
import com.nonage.controller.action.OrderDetailAction;
import com.nonage.controller.action.OrderInsertAction;
import com.nonage.controller.action.OrderListAction;
import com.nonage.controller.action.ProductDetailAction;
import com.nonage.controller.action.ProductKindAction;
import com.nonage.controller.action.QnaDetailAction;
import com.nonage.controller.action.QnaListAction;
import com.nonage.controller.action.QnaWriteAction;
import com.nonage.controller.action.QnaWriteFormAction;
import com.nonage.controller.action.admin.AdminAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		CustomerAction cAction = new CustomerAction();
		if(cAction.action(command) != null) {
			action = cAction.action(command);
		}
		AdminAction aAction = new AdminAction();
		if(aAction.action(command) != null) {
			action = aAction.action(command);
		}
		
		return action;
	}
}
