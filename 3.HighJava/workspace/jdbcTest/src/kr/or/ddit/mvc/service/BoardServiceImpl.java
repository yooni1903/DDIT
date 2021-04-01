package kr.or.ddit.mvc.service;

import java.util.List;

import kr.or.ddit.mvc.dao.BoardDaoImpl;
import kr.or.ddit.mvc.dao.IBoardDao;
import kr.or.ddit.mvc.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao bd;
	
	
	public BoardServiceImpl() {
		bd = new BoardDaoImpl();
	}

	@Override
	public List<BoardVO> boardList() {
		return bd.boardList();
	}

	@Override
	public int insertBoard(BoardVO bv) {
		return bd.insertBoard(bv);
	}

	@Override
	public BoardVO viewBoard(int board_no) {
		return bd.viewBoard(board_no);
	}

	@Override
	public int updateBoard(BoardVO bv) {
		return bd.updateBoard(bv);
	}

	@Override
	public int deleteBoard(int board_no) {
		return bd.deleteBoard(board_no);
	}

	@Override
	public List<BoardVO> searchBoard(String search) {
		return bd.searchBoard(search);
	}

}
