package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nonage.dto.MemberVO;

import db.DBAction;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	// 회원가입을 위한 멤버 넣기
	public int insertMember(MemberVO memberVO) throws Exception {
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "insert into member(id, pwd, name, email, zip_num, address, phone) " + "values(?,?,?,?,?,?,?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, memberVO.getId());
			pstmt.setString(2, memberVO.getPwd());
			pstmt.setString(3, memberVO.getName());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getZipNum());
			pstmt.setString(6, memberVO.getAddress());
			pstmt.setString(7, memberVO.getPhone());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	// db에 데이터가 있는지 검사하기
	// 데이터가 있으면 중복

	public int confirmID(String userid) throws Exception{
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from member where id = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			// 아이디 존재
			if (rs.next()) {
				result = 1;
			}
			// 아이디 존재 x
			else {
				result = -1;
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
		
		return result;
	}
	
	public MemberVO getMember(String id) throws Exception{
		MemberVO memberVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from member where id= ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(rs.getString("email"));
				memberVO.setZipNum(rs.getString("zip_num"));
				memberVO.setAddress(rs.getString("address"));
				memberVO.setPhone(rs.getString("phone"));
				memberVO.setUseryn(rs.getString("useyn"));
				memberVO.setIndate(rs.getTimestamp("indate"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return memberVO;
	}
}
