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
 * Servlet implementation class Insert
 */
@WebServlet("/Insert.do")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Insert() {
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
		
		String wip = request.getRemoteAddr();
		vo.setWip(wip);
		
		// 2. service객체 얻기
		IBoardService service = BoardServiceImpl.getService();
		
		// 3. service 메소드 호춣하기
		int num = service.insertBoard(vo);
		
		// 4. 결과값을 request에 저장하기
		request.setAttribute("result", num);
		
		// 5. view페이지로 forward-result.jsp
		request.getRequestDispatcher("board/result.jsp").forward(request, response);
	}

}
