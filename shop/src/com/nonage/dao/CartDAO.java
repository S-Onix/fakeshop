package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nonage.dto.CartVO;

import db.DBAction;

public class CartDAO {
	private static CartDAO instance = new CartDAO();
	
	public static CartDAO getInstance() {
		return instance;
	}
	
	public void insertCart(CartVO cart) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into cart(id, pseq, quantity) values(?,?,?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cart.getId());
			pstmt.setInt(2, cart.getPseq());
			pstmt.setInt(3, cart.getQuantity());
			
			String meg = pstmt.executeUpdate() > 0 ? "success" : "fail";
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
	}
	
	public ArrayList<CartVO> listCart(String id) throws Exception{
		
		ArrayList<CartVO> cartList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from cart_view where id = ? order by cseq desc";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVO cart = new CartVO();
				cart.setCseq(rs.getInt(1));
				cart.setId(rs.getString(2));
				cart.setPseq(rs.getInt(3));
				cart.setMname(rs.getString(4));
				cart.setPname(rs.getString(5));
				cart.setQuantity(rs.getInt(6));
				cart.setIndate(rs.getTimestamp(7));
				cart.setPrice2(rs.getInt(8));
				
				cartList.add(cart);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return cartList;
	}
	public void deleteCart(int cseq) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "delete from cart where cseq = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
}





















