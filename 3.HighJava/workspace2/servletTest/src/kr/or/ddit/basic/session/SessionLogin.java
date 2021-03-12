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
 * Servlet implementation class SessionLogin
 */
@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Session 연습</title>");
		out.println("<body>");
		
		userId = userId.equals("")?null:userId;
		pass = pass.equals("")?null:pass;
		
		System.out.println("id : " + userId + "pass : " + pass);
		
		if(userId != null && pass != null) {
			if(userId.contentEquals("admin") && pass.equals("pass123")) {
				session.setAttribute("userId", userId);
				session.setAttribute("pass", pass);
				
				
				out.println("<h2>" + userId + "님 반갑습니다.</h2>");
				out.println("<a href='"
						+ request.getContextPath() + "/basic/03/sessionLogin.jsp'>로그아웃</a>");
				
				
			}else {
				out.println("<h3>로그인에 실패했습니다.</h3>");
				out.println("<a href='"+ request.getContextPath() + "/basic/03/sessionLogin.jsp'>다시 로그인하기</a>");
				
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/basic/03/sessionLogin.jsp");
		}
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
