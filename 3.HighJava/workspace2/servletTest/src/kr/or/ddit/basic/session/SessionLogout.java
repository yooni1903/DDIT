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
 * Servlet implementation class SessionLogout
 */
@WebServlet("/sessionLogout.do")
public class SessionLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Session 연습</title>");
		out.println("<body>");
		
		out.println("<h2>Session 로그아웃</h2>");

		out.println("<hr>");
		
		out.println("</body></html>");
		
		response.sendRedirect(request.getContextPath() + "/basic/03/sessionLogin.jsp");
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
