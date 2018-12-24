package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;

import db.DBAction;

public class OrderDAO {
	private static OrderDAO instance = new OrderDAO();

	private OrderDAO() {

	}

	public static OrderDAO getInstance() {
		return instance;
	}

	// 사용자가 주문하기 버튼을 클릭시에 동작하는 메소드
	public int insert(String id, ArrayList<CartVO> cartList) throws Exception {
		int maxOseq = 0;
		orderInsert(id);
		maxOseq = selectLastOrder();
		for (CartVO cart : cartList) {
			orderDetailInsert(maxOseq, cart);
			cartUpdate(cart.getCseq());
		}
		return maxOseq;
	}

	// order TAble에 데이터 추가
	public void orderInsert(String id) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into orders(id) values(?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	// 가장 최근 주문내역 주문번호 가져오기
	public int selectLastOrder() throws Exception {
		int oseq = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select max(oseq) from orders";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				oseq = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return oseq;

	}

	// orderdetail 테이블에 데이터 추가
	public void orderDetailInsert(int oseq, CartVO cartVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into order_detail(oseq, pseq, quantity) values(?,?,?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oseq);
			pstmt.setInt(2, cartVO.getPseq());
			pstmt.setInt(3, cartVO.getQuantity());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
	}

	public void cartUpdate(int cseq) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "update cart set result=2 where cseq=?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cseq);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}

	public ArrayList<OrderVO> selectOrderList(String id, int oseq, String result) throws Exception {
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from order_View where id = ? and result like '%" + result + "%' and oseq = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, oseq);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				OrderVO orderVO = new OrderVO();
	            orderVO.setOdseq(rs.getInt("ODSEQ"));
	            orderVO.setOseq(rs.getInt("OSEQ"));
	            orderVO.setId(rs.getString("ID"));
	            orderVO.setIndate(rs.getTimestamp("INDATE"));
	            orderVO.setMname(rs.getString("MNAME"));
	            orderVO.setPseq(rs.getInt("PSEQ"));
	            orderVO.setQuantity(rs.getInt("QUANTITY"));
	            orderVO.setPname(rs.getString("PNAME"));
	            orderVO.setPrice2(rs.getInt("PRICE2"));
	            orderVO.setResult(rs.getString("RESULT"));
				orderList.add(orderVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}

		return orderList;
	}
	
	public ArrayList<OrderVO> selectOrderList(String id, String result) throws Exception{
		ArrayList<OrderVO> orderList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from order_view where id = ? and result like '%" + result + "%' order by indate desc;";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVO orderVO = new OrderVO();
	            orderVO.setOdseq(rs.getInt("ODSEQ"));
	            orderVO.setOseq(rs.getInt("OSEQ"));
	            orderVO.setId(rs.getString("ID"));
	            orderVO.setIndate(rs.getTimestamp("INDATE"));
	            orderVO.setMname(rs.getString("MNAME"));
	            orderVO.setPseq(rs.getInt("PSEQ"));
	            orderVO.setQuantity(rs.getInt("QUANTITY"));
	            orderVO.setPname(rs.getString("PNAME"));
	            orderVO.setPrice2(rs.getInt("PRICE2"));
	            orderVO.setResult(rs.getString("RESULT"));
				orderList.add(orderVO);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return orderList;
	}

	public ArrayList<Integer> selectSeqOrderList(String id) throws Exception{
		ArrayList<Integer> seqList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select distinct oseq from order_view where id = ? and  result='1' order by oseq desc";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				seqList.add(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		}

		return seqList;
	}

}
