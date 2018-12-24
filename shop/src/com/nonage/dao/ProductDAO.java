package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nonage.dto.ProductVO;

import db.DBAction;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}

	public ArrayList<ProductVO> listNewProduct() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<>();

		try {
			String sql = "select * from new_pro_view";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("PSEQ"));
				product.setName(rs.getString("NAME"));
				product.setPrice2(rs.getInt("PRICE2"));
				product.setImage(rs.getString("IMAGE"));

				productList.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return productList;
	}

	public ArrayList<ProductVO> listBestProduct() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<>();

		try {
			String sql = "select * from best_pro_view";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductVO product = new ProductVO();
				product.setPseq(rs.getInt("PSEQ"));
				product.setName(rs.getString("NAME"));
				product.setPrice2(rs.getInt("PRICE2"));
				product.setImage(rs.getString("IMAGE"));

				productList.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return productList;
	}

	public ArrayList<ProductVO> listKindProduct(String kind) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<>();

		try {
			String sql = "select pseq, name, kind, price2, image from product where kind = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kind);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO productVO = new ProductVO();
				productVO.setPseq(rs.getInt("pseq"));
				productVO.setName(rs.getString("name"));
				productVO.setKind(rs.getString("kind"));
				productVO.setPrice2(rs.getInt("price2"));
				productVO.setImage(rs.getString("image"));

				productList.add(productVO);
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

		return productList;
	}

	// 상품 선택
	public ProductVO getProduct(String pseq) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO product = null;
		try {
			String sql = "select * from product where pseq = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pseq);
			rs = pstmt.executeQuery();

			// 상품에 대한 정보가 있을시
			if (rs.next()) {
				product = new ProductVO();
				product.setPseq(rs.getInt("pseq"));
				product.setName(rs.getString("name"));
				product.setPrice2(rs.getInt("price2"));
				product.setImage(rs.getString("image"));
			} else {
				// 상품에 대한 정보가 없음
				return null;
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

		return product;
	}

	public ArrayList<ProductVO> selectProductByPname(String pname) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<>();
		try {
			String sql = "select * from product where name like '%" + pname + "%'";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO productVO = new ProductVO();
				productVO.setPseq(rs.getInt("pseq"));
				productVO.setBestyn(rs.getString("bestyn"));
				productVO.setContent(rs.getString("content"));
				productVO.setImage(rs.getString("image"));
				productVO.setIndate(rs.getTimestamp("indate"));
				productVO.setKind(rs.getString("kind"));
				productVO.setPrice2(rs.getInt("price1"));
				productVO.setPrice1(rs.getInt("price2"));
				productVO.setPrice3(rs.getInt("price3"));
				productVO.setUseyn(rs.getString("useyn"));
				productList.add(productVO);
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

		return productList;
	}

	public ArrayList<ProductVO> listProduct(int tpage, String product_name) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVO> productList = new ArrayList<>();
		int absolutepage = 1;

		try {
//			String sql = "select * from product order by pseq desc";
			String sql = "select pseq, indate, name, price1, price2, useyn, bestyn " + "from product where name like '%"
					+ product_name + "%' order by pseq desc";

			conn = DBAction.getInstance().getConnection();

			// 레코드에 대한 커서에 대한 유연성이 필요함 / 중간 위치에서 시작해야됨
			// 몇번째에서 몇개까지 가져와야하는지에 대한 알고리즘 필요함
			// 시작 지점 : tpage
			absolutepage = (tpage - 1) * counts + 1;
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rs.absolute(absolutepage);
				int count = 0;
				while (count < counts) {
					ProductVO productVO = new ProductVO();
					productVO.setName(rs.getString("name"));
					productVO.setPseq(rs.getInt("pseq"));
					productVO.setBestyn(rs.getString("bestyn"));
//					productVO.setContent(rs.getString("content"));
//					productVO.setImage(rs.getString("image"));
					productVO.setIndate(rs.getTimestamp("indate"));
//					productVO.setKind(rs.getString("kind"));
					productVO.setPrice1(rs.getInt("price1"));
					productVO.setPrice2(rs.getInt("price2"));
//					productVO.setPrice3(rs.getInt("price3"));
					productVO.setUseyn(rs.getString("useyn"));
					productList.add(productVO);
					if (rs.isLast()) {
						break;
					}
					rs.next();
					count++;
				}
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

		return productList;
	}

	// 페이징 처리
	static int view_rows = 5;
	static int counts = 5;

	public String pageNumber(int tpage, String name) {
		String str = "";
		int total_pages = totalRecord(name);
		int page_count = total_pages / counts + 1;
		if (total_pages % counts == 0) {
			page_count--;
		}
		if (tpage < 1) {
			tpage = 1;
		}

		int start_page = tpage - (tpage % view_rows) + 1;
		int end_page = start_page + (counts - 1);

		if (end_page > page_count) {
			end_page = page_count;
		}
		if (start_page > view_rows) {
			str += "<a href='NonageServlet?command=admin_product_list&tpage=1&key=" + name
					+ "'>&lt;&lt;</a>&nbsp;&nbsp;";
			str += "<a href='NonageServlet?command=admin_product_list&tpage=" + (start_page - 1);
			str += "&key=<%=product_name%>'>&lt;</a>&nbsp;&nbsp;";
		}

		for (int i = start_page; i <= end_page; i++) {
			if (i == tpage) {
				str += "<font color=red>[" + i + "]&nbsp;&nbsp;</font>";
			} else {
				str += "<a href='NonageServlet?command=admin_product_list&tpage=" + i + "&key=" + name + "'>[" + i
						+ "]</a>&nbsp;&nbsp;";
			}
		}

		if (page_count > end_page) {
			str += "<a href='NonageServlet?command=admin_product_list&tpage=" + (end_page + 1) + "&key=" + name
					+ "'> &gt; </a>&nbsp;&nbsp;";
			str += "<a href='NonageServlet?command=admin_product_list&tpage=" + page_count + "&key=" + name
					+ "'> &gt; &gt; </a>&nbsp;&nbsp;";
		}
		return str;
	}

	public int totalRecord(String product_name) {
		int total_page = 0;
		String sql = "select count(*) from product where name like '%" + product_name + "%'";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet pageset = null;

		try {
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pageset = pstmt.executeQuery();
			if (pageset.next()) {
				total_page = pageset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pageset != null)
					pageset.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return total_page;
	}

}
