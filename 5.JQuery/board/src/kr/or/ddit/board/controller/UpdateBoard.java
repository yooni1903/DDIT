package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

/**
 * Servlet implementation class UpdateBoard
 */
@WebServlet("/UpdateBoard.do")
public class UpdateBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateBoard() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 클라이언트 요청시 전송되는 데이터를 받아온다.
				request.setCharacterEncoding("utf-8");
				
				BoardVO vo = new BoardVO();
				
				vo.setSubject(request.getParameter("subject"));
				vo.setWriter(request.getParameter("writer"));
				vo.setMail(request.getParameter("mail"));
				vo.setPassword(request.getParameter("password"));
				vo.setContent(request.getParameter("content"));
				vo.setNum(Integer.parseInt(request.getParameter("num")));
				
				
				// 2. service객체 얻기
				IBoardService service = BoardServiceImpl.getService();
				
				// 3. service메소드 호출 - 결과값 받기
				int num = service.updateBoard(vo);
				
				// 4. request에 결과값 저장하기
				request.setAttribute("result", num);
				
				// 5. view페이지로 forward - result.jsp
				request.getRequestDispatcher("board/result.jsp").forward(request, response);
	}

}
