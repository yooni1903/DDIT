package kr.or.ddit.mvc.dao;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	private static final Logger logger = Logger.getLogger(MemberDaoImpl.class);

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성완료!");
			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr)"
					+ " values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			logger.info("PreparedStatement객체 생성!!");
			logger.info("실행 SQL문 : " + sql);
			logger.info("사용 데이터 : [" + memVo.getMem_id() + ", " + memVo.getMem_name() + ", " 
			+ memVo.getMem_tel() + ", " + memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			logger.info("insert 작업 성공!");
			
		} catch (SQLException e) {
			logger.error("insert 작업 실패ㅜㅠ");
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{
				pstmt.close();
				logger.info("PreparedStatement 객체 반납 성공");
				}catch(SQLException e){}
			if(conn != null) try{
				conn.close();
				logger.info("Connection 객체 반납 성공");
				}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성완료!");
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			logger.info("PreparedStatement객체 생성!!");
			logger.info("실행 SQL문 : " + sql);
			logger.info("사용 데이터 : [" + memId + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("delete 작업 성공!");
			
		} catch (SQLException e) {
			logger.error("delete 작업 실패ㅜ");
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{
				pstmt.close();
				logger.info("PreparedStatement 객체 반납 성공");
				}catch(SQLException e){}
			if(conn != null) try{
				conn.close();
				logger.info("Connection 객체 반납 성공");
				}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성완료!");
			String sql = "update mymember set mem_name = ?, mem_tel = ?, mem_addr = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setString(2, memVo.getMem_tel());
			pstmt.setString(3, memVo.getMem_addr());
			pstmt.setString(4, memVo.getMem_id());
			logger.info("PreparedStatement객체 생성!!");
			logger.info("실행 SQL문 : " + sql);
			logger.info("사용 데이터 : [" +  memVo.getMem_name() + ", " 
					+ memVo.getMem_tel() + ", " + memVo.getMem_addr() + ", " + memVo.getMem_id() + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("update 작업 성공!");
			
		} catch (SQLException e) {
			logger.error("delete 작업 실패ㅜ");
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{
				pstmt.close();
				logger.info("PreparedStatement 객체 반납 성공");
				}catch(SQLException e){}
			if(conn != null) try{
				conn.close();
				logger.info("Connection 객체 반납 성공");				
				}catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성완료!");
			String sql = "select * from mymember";
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement객체 생성!!");
			logger.info("실행 SQL문 : " + sql);
			
			rs = pstmt.executeQuery(sql);
			logger.info("getAllMember 작업 성공!");
			
			while(rs.next()){
				MemberVO mv = new MemberVO();
				mv.setMem_id(rs.getString("mem_id"));
				mv.setMem_name(rs.getString("mem_name"));
				mv.setMem_tel(rs.getString("mem_tel"));
				mv.setMem_addr(rs.getString("mem_addr"));
				
				list.add(mv);
			}
			
		} catch (SQLException e) {
			logger.error("getAllMember 작업 실패ㅜ");
			e.printStackTrace();
			list = null;
		} finally{
			if(rs != null) try{
				rs.close();
				logger.info("ResultSet 객체 반납 성공");	
			}catch(SQLException e){}
			if(pstmt != null) try{
				pstmt.close();
				logger.info("PreparedStatement 객체 반납 성공");	
			}catch(SQLException e){}
			if(conn != null) try{
				conn.close();
				logger.info("Connection 객체 반납 성공");		
			}catch(SQLException e){}
		}
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;		// 회원 ID개수가 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성 성공!");
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			logger.info("PreparedStatement 객체 생성!");
			logger.info("실행 SQL문 : " + sql);
			logger.info("사용한 데이터 : [" + memId + "]");
			
			rs = pstmt.executeQuery();
			logger.info("getMemberCount 작업 성공!");
			
			if(rs.next()){
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			logger.error("getMemberCount 작업 실패!");
			e.printStackTrace();
		} finally{
			if(rs != null) try{
				rs.close();
				logger.info("ResultSet 객체 반납 성공");	
				}catch(SQLException e){}
			if(pstmt != null) try{
				pstmt.close();
				logger.info("PreparedStatement 객체 반납 성공");	
				}catch(SQLException e){}
			if(conn != null) try{
				conn.close();
				logger.info("Connection 객체 반납 성공");		
				}catch(SQLException e){}
		}
		return count;
	}

/*	@Override
	public int updateMember2(String updateField, String updateData, String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update mymember set " + updateField +" = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return cnt;
	}*/

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// Key값 => 회원 ID(memid), 수정할 컬럼명(field), 수정할 데이터(data)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection 객체 생성 완료!");
			String sql = "update mymember set " + paramMap.get("field") +" = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			logger.info("PreparedStatement 객체 생성!");
			
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memid"));
			logger.info("사용한 sql문 : " + sql);
			logger.info("사용한 데이터 : [" + paramMap.get("data") + ", " + paramMap.get("memid") + "]");
			
			cnt = pstmt.executeUpdate();
			logger.info("updateMember2 작업 성공!");
			
			
		} catch (SQLException e) {
			logger.error("updateMember2 작업 실패 ㅜ");
			cnt = 0;
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{
				pstmt.close();
				logger.info("PreparedStatement 객체 반납 성공");
			}catch(SQLException e){}
			if(conn != null) try{
				conn.close();
				logger.info("PreparedStatement 객체 반납 성공");
			}catch(SQLException e){}
		}
		
		return cnt;
	}

	

}
