package kr.or.ddit.basic.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class sessionLogoutSem
 */
@WebServlet("/sessionLogoutSem.do")
public class SessionLogoutSem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃은 세션을 삭제한 후 로그인 페이지로 이동한다.
		HttpSession session = request.getSession();
		
		// session.removeAttribute()
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/basic/03/sessionLoginSem.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
