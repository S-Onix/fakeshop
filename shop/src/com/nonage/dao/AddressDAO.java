package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nonage.dto.AddressVO;

import db.DBAction;

public class AddressDAO {
	private static AddressDAO instance = new AddressDAO();
	
	
	public static AddressDAO getInstance() {
		return instance;
	}
	
	public ArrayList<AddressVO> selectAddressByDong(String dong) throws Exception{
		ArrayList<AddressVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from address where dong like'%" + dong + "%'";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AddressVO addressVO = new AddressVO();
				addressVO.setZipNum(rs.getString("zip_num"));
				addressVO.setSido(rs.getString("sido"));
				addressVO.setGugun(rs.getString("gugun"));
				addressVO.setDong(rs.getString("dong"));
				addressVO.setZipCode(rs.getString("zip_code"));
				addressVO.setBunji(rs.getString("bunji"));
				
				list.add(addressVO);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		
		return list;
	}
}
