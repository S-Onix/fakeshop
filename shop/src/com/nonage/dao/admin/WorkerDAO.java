package com.nonage.dao.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nonage.dto.admin.WorkerVO;

import db.DBAction;

public class WorkerDAO {

	private static WorkerDAO instance = new WorkerDAO();
	
	public static WorkerDAO getInstance() {
		return instance;
	}
	
	public WorkerVO checkWorker(String id, String pwd) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WorkerVO workerVO = null;
		try {
			String sql = "select * from worker where id = ? and pwd = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				workerVO = new WorkerVO();
				workerVO.setId(rs.getString("id"));
				workerVO.setPwd(rs.getString("pwd"));
				workerVO.setName(rs.getString("name"));
				workerVO.setPhone(rs.getString("phone"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return workerVO;
		
	}
}
