package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionDeleteServlet
 */
@WebServlet("/sessionDeleteServlet.do")
public class SessionDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
		 - Session 삭제하기
		 1. Session객체 생성
	*/
		HttpSession session = request.getSession();
	/*
	  	 2. removeAttribute()메서드로 Session값 삭제하기
	  	 형식) session객체.removeAttribute("key값");
	  	 		==> 세션 자체는 삭제되지 않고, 지정한 'key값'과 짝이 되는 '세션값'만 삭제된다.
	 */
		session.removeAttribute("testSession");
		
	/*
	 	 3. invalidate()메서드로 Session 삭제하기
	 	 형식) session객체.invalidate();
	 	 		==> 세션 자체가 삭제된다.
	 */
//		session.invalidate();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Session 연습</title>");
		out.println("<body>");
		
		out.println("<h2>Session 데이터 삭제하기</h2>");
		out.println("<a href='" + request.getContextPath() + "/basic/03/sessionTest.jsp'>시작문서로 이동하기</a>");

		out.println("<hr>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
