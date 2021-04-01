package kr.or.ddit;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DummyServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super코드를 항상 제거해줘야 한다. 왜냐하면 이 super코드는 항상 405 오류를 내보내기 때문
		resp.setContentType("text/plain;charset=utf-8");
		
//		DummyServlet.class
		URL url = this.getClass().getResource("/kr/or/ddit/dummy.xml");
		
		resp.getWriter().println("시원하다! " + url);
	}
}
