package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapUtil;
import kr.or.ddit.vo.JdbcBoardVO;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private SqlMapClient smc;
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() {
		smc = SqlMapUtil.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) {
			dao = new JdbcBoardDaoImpl();
		}
		return dao;
	}
	
	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			Object obj = (int) smc.insert("board.insertBoard", jBoardVo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("board.deleteBoard", boardNo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			cnt = smc.update("board.updateBoard", jBoardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			
		}
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO jBoardVo = null;
		
		// 조회수를 1 증가시킨다.
		if(setCountIncrement(boardNo)==0) {
			return null;
		}
		
		try {
			jBoardVo = (JdbcBoardVO) smc.queryForObject("board.getBoard", boardNo);
			System.out.println("1");
		} catch (SQLException e) {
			jBoardVo = null;
			e.printStackTrace();
		} 
		
		return jBoardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = null;
		try {
			boardList = smc.queryForList("board.getAllBoardList");
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String jBoardTitle) {
		List<JdbcBoardVO> boardList = null;
		if(jBoardTitle==null) {
			jBoardTitle = "";
		}
		try {
			boardList = smc.queryForList("board.getSearchBoardList", jBoardTitle);
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.update("board.setCountIncrement", boardNo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

}
