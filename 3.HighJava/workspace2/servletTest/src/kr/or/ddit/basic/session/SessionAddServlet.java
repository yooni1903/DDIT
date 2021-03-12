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
 * Servlet implementation class SessionAddServlet
 */
@WebServlet("/sessionAddServlet.do")
public class SessionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*	
		- Session 저장하는 순서
		1. Session 객체 생성하기
			형식1) request객체.getSession(); 또는 request객체.getSession(true);			괄호 안에 true가 있든 없든 똑같음
					==> 현재 세션이 존재하면 현재 세션을 반환하고, 세션이 없으면 새로운 세션을 생성해서 반환한다.
			형식2) request객체.getSession(false);
					==> 현재 세션이 존재하면 현재 세션을 반환하고, 세션이 없으면 null을 반환한다.
	*/	
		HttpSession session = request.getSession();
		
	/*
		 2. setAttrivute()메서드로 Session값을 저장한다.
		 	형식) session객체.setAttribute("key값", session값);
		 			==> 'key값'은 문자열, 'session값'은 모든 종류의 데이터
	*/
		session.setAttribute("testSession", "연습용 세션입니다.");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 30);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Session 연습</title>");
		out.println("<body>");
		
		out.println("<h2>Session데이터가 저장되었습니다.</h2><br><br>");
		out.println("<a href='"
				+ request.getContextPath() + "/basic/03/sessionTest.jsp'>시작문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
