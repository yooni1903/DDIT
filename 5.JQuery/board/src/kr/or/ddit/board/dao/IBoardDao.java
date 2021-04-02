package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardDao {
		// 전체 목록 가져오기
		public List<BoardVO> selectAll() throws SQLException;		// 지금은 dao 줘도 괜찮다 -> 1대1 대응이기 때문에
		
		// 페이지별 목록 가져오기 
		public List<BoardVO> selectByPage(Map<String, Integer> map) throws SQLException;
		// 게시글 저장하기
		public int insertBoard(BoardVO vo) throws SQLException;
		
		// 게시글 수정하기
		public int updateBoard(BoardVO vo) throws SQLException;
		
		// 게시글 삭제하기
		public int deleteBoard(int num) throws SQLException;
		
		// 댓글 저장하기
		public int insertReply(ReplyVO vo) throws SQLException;
		
		// 댓글 수정하기
		public int updateReply(ReplyVO vo) throws SQLException;
		
		// 댓글 삭제하기
		public int deleteReply(int renum) throws SQLException;
		
		// 댓글 리스트 가져오기
		public List<ReplyVO> listReply(int bonum) throws SQLException;
		
		// 조회수 증가
		public int updateHit(int num) throws SQLException;
		
		// 전체글 갯수 가져오기
		public int listCount() throws SQLException;
}
