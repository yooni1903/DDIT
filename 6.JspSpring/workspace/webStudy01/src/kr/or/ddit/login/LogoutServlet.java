package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout.do")
public class LogoutServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// isNew -> 이 세션이 가장 최신의 것인지. 이 페이지는 로그아웃 페이지이기 때문에 지금의 세션이 최신의 것이라면 비정상적인 요청이다.
		if(session.isNew()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		session.invalidate();
		
		// session을 끝냈으니 request가 끝났다고 생각해도 된다.
		String view = "/";
		resp.sendRedirect(req.getContextPath() + view);
	}
}
