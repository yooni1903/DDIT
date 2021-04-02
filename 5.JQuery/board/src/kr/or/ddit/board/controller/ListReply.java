package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.ReplyVO;

/**
 * Servlet implementation class ListReply
 */
@WebServlet("/ListReply.do")
public class ListReply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListReply() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 클라이언트 요청시 bonum 받기
		request.setCharacterEncoding("utf-8");
		int bonum = Integer.parseInt( request.getParameter("bonum"));
		
		// 2. service 객체 얻어오기
		IBoardService service = BoardServiceImpl.getService();
		
		// 3. service 메서드 호출하기
		List<ReplyVO> list = service.listReply(bonum);
		
		// 4. 결과값을 request에 저장
		request.setAttribute("replyList", list);
		
		// 5. view페이지로 forward - replyList.jsp
		request.getRequestDispatcher("board/replyList.jsp").forward(request, response);
		
	}

}
