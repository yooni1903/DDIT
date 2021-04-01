package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements IBoardDao{

	@Override
	public List<BoardVO> boardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setTitle(rs.getString("board_title"));
				bv.setWriter(rs.getString("board_writer"));
				bv.setDate(rs.getDate("board_date"));
				bv.setCnt(rs.getInt("board_cnt"));
				bv.setContent(rs.getString("board_content"));
				list.add(bv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return list;
	}

	@Override
	public int insertBoard(BoardVO bv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board (board_no, board_title, board_writer,"
					+ " board_date, board_content)"
					+ " values (board_seq.nextval, ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getWriter());
			pstmt.setString(3, bv.getContent());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public BoardVO viewBoard(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bv = new BoardVO();
		try {
			cntBoard(board_no);
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_no = ? order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setTitle(rs.getString("board_title"));
				bv.setWriter(rs.getString("board_writer"));
				bv.setDate(rs.getDate("board_date"));
				bv.setCnt(rs.getInt("board_cnt"));
				bv.setContent(rs.getString("board_content"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			bv = null;
		} finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return bv;
	}

	
	private void cntBoard(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_cnt = board_cnt + 1"  
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}


	@Override
	public int updateBoard(BoardVO bv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_content = ?"
					+ " where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getTitle());
			pstmt.setString(2, bv.getContent());
			pstmt.setInt(3, bv.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public List<BoardVO> searchBoard(String search) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_title like '%" + search + "%' order by board_no desc";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()){
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setTitle(rs.getString("board_title"));
				bv.setWriter(rs.getString("board_writer"));
				bv.setDate(rs.getDate("board_date"));
				bv.setCnt(rs.getInt("board_cnt"));
				bv.setContent(rs.getString("board_content"));
				list.add(bv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		} finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return list;
	}

}
