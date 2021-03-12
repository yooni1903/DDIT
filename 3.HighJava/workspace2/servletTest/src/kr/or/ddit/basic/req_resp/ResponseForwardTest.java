package kr.or.ddit.basic.req_resp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseForwardTest
 */
@WebServlet("/responseForwardTest.do")
public class ResponseForwardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// form에서 구성한 데이터나 파라미터로 보낸 데이터 받기
		String name = request.getParameter("username");
		
		// setAttribute()메서드로 셋팅한 데이터 받기
		String tel = (String) request.getAttribute("tel");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Response forward 연습</title>");
		out.println("<body>");
		
		out.println("<h2>Response forward 결과</h2>");
		out.println("<hr>");
		out.println("<table border='1'>");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + name + "</td></tr>");
		out.println("<tr><td>전화</td>");
		out.println("<td>" + tel + "</td></tr>");
		out.println("</table>");
		
		out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
