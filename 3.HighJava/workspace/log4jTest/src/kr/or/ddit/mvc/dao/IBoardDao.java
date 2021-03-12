package kr.or.ddit.mvc.dao;

import java.util.List;

import kr.or.ddit.mvc.vo.BoardVO;

public interface IBoardDao {
	
	/**
	 * DB의 게시글을 가져와 List에 담아서 출력하는 메서드
	 * @return BoardVO 객체를 담고 있는 List 객체를 반환
	 */
	public List<BoardVO> boardList();
	
	/**
	 * BoardVo 객체에 담겨진 자료를 DB에 insert하는 메서드
	 * @param bv insert할 데이터가 저장된 BoardVO
	 * @return insert 성공 :1 실패 : 0
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * board_no를 인수로 받아서 해당 게시글 내용을 BoardVO 객체에 담아오는 메서드
	 * @param board_no 게시글을 가져올 board_no
	 * @return 게시글 내용을 담고 있는 BoardVO 객체
	 */
	public BoardVO viewBoard(int board_no);
	
	/**
	 * BoardVo자료를 이용하여 게시글 내용을 update하는 메서드
	 * @param bv
	 * @return
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * board_no를 이용하여 해당 게시글을 삭제하는 메서드
	 * @param board_no
	 * @return
	 */
	public int deleteBoard(int board_no);
	
	/**
	 * search를 이용하여 해당되는 게시글들을 List에 담아서 반환하는 메서드
	 * @param search 게시글 제목을 검색할 인자
	 * @return BoardVO 객체를 담고 있는 List 객체
	 */
	public List<BoardVO> searchBoard(String search);
	
	
}
