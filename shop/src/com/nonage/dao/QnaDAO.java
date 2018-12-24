package com.nonage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nonage.dto.QnaVO;

import db.DBAction;

public class QnaDAO {
	private static QnaDAO instance = new QnaDAO();
	
	public static QnaDAO getInstance() {
		return instance;
	}
	
	public void insert(QnaVO qnaVO, String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "insert into qna(subject, content, id) values (?,?,?)";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qnaVO.getSubject());
			pstmt.setString(2, qnaVO.getContent());
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	
	public ArrayList<QnaVO> listQna(String id) throws Exception {
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			String sql = "select * from qna where id = ? order by qseq desc";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaVO qnaVO = new QnaVO();
				qnaVO.setQseq(rs.getInt(1));
				qnaVO.setSubject(rs.getString(2));
				qnaVO.setContent(rs.getString(3));
				qnaVO.setReply(rs.getString(4));
				qnaVO.setId(id);
				qnaVO.setRep(rs.getString(6));
				qnaVO.setIndate(rs.getTimestamp(7));
				
				qnaList.add(qnaVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return qnaList;
	}
	
	public QnaVO getQna(int qseq) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		QnaVO qnaVO = null;
		try {
			String sql = "select * from qna where qseq = ?";
			conn = DBAction.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qseq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qnaVO = new QnaVO();
				qnaVO.setQseq(rs.getInt(1));
				qnaVO.setSubject(rs.getString(2));
				qnaVO.setContent(rs.getString(3));
				qnaVO.setReply(rs.getString(4));
				qnaVO.setRep(rs.getString(6));
				qnaVO.setIndate(rs.getTimestamp(7));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
		
		return qnaVO;
	}
	
}























