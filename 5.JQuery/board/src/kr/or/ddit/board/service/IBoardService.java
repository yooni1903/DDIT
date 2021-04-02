package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public interface IBoardService {
	
	// 전체 목록 가져오기
	public List<BoardVO> selectAll();
	
	// 페이지별 목록 가져오기 
	public List<BoardVO> selectByPage(Map<String, Integer> map);
	
	// 게시글 저장하기
	public int insertBoard(BoardVO vo);
	
	// 게시글 수정하기
	public int updateBoard(BoardVO vo);
	
	// 게시글 삭제하기
	public int deleteBoard(int num);
	
	// 댓글 저장하기
	public int insertReply(ReplyVO vo);
	
	// 댓글 수정하기
	public int updateReply(ReplyVO vo);
	
	// 댓글 삭제하기
	public int deleteReply(int renum);
	
	// 댓글 리스트 가져오기
	public List<ReplyVO> listReply(int bonum);
	
	// 조회수 증가
	public int updateHit(int num);
	
	// 전체글 갯수 가져오기
	public int listCount();
}
