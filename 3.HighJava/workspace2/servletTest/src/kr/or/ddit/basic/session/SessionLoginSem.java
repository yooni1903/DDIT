package kr.or.ddit.basic.session;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionLoginSem
 */
@WebServlet("/sessionLoginSem.do")
public class SessionLoginSem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어오는 id와 password를 받아온다.
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		HttpSession session = request.getSession();
		
		if(userId != null && pass != null) {
			if(userId.equals("admin") && pass.equals("1234")) {
				// 로그인 성공했을 때 ==> 로그인한 정보를 세션에 저장한다.
				session.setAttribute("userId", userId);
			}
		}
		
		// sessionLoginSem.jsp문서로 이동하기
		// 방법1)
//		response.sendRedirect(request.getContextPath() + "/basic/03/sessionLoginSem.jsp");
		
		// 방법2) forward방식 이용하기
		RequestDispatcher rd = request.getRequestDispatcher("/basic/03/sessionLoginSem.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
