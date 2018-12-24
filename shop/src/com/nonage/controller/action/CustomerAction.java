package com.nonage.controller.action;

public class CustomerAction {
	public Action action(String command) {
		Action action = null;
		if(command.equals("index")) {
			action = new IndexAction();
		}else if(command.equals("contract")) {
			action = new ContractAction();
		}else if(command.equals("join_form")) {
			action = new JoinFormAction();
		}else if(command.equals("join")) {
			action = new JoinAction();
		}else if(command.equals("id_check_form")) {
			action = new IdCheckFormAction();
		}else if(command.equals("find_zip_num")) {
			action = new FindZipNumAction();
		}else if(command.equals("login_form")) {
			action = new LoginFormAction();
		}else if(command.equals("login")) {
			action = new LoginAction();
		}else if(command.equals("logout")) {
			action = new LogoutAction();
		}else if(command.equals("catagory")) {
			action =  new ProductKindAction();
		}else if(command.equals("product_detail")) {
			action = new ProductDetailAction();
		}else if(command.equals("cart_insert")) {
			action = new CartInsertAction();
		}else if(command.equals("cart_list")) {
			action = new CartListAction();
		}else if(command.equals("cart_delete")) {
			action = new CartDeleteAction();
		}else if(command.equals("order_insert")) {
			action = new OrderInsertAction();
		}else if(command.equals("order_list")) {
			action = new OrderListAction();
		}else if(command.equals("mypage")) {
			action = new MyPageAction();
		}else if (command.equals("order_detail")) {
			action = new OrderDetailAction();
		}else if (command.equals("order_all")) {
			action = new OrderAllAction();
		}else if (command.equals("qna_write_form")) {
			action = new QnaWriteFormAction();
		}else if (command.equals("qna_write")) {
			action = new QnaWriteAction();
		}else if (command.equals("qna_list")) {
			action = new QnaListAction();
		}else if(command.equals("qna_detail")) {
			action = new QnaDetailAction();
		}
		
		return action;
	}
}
