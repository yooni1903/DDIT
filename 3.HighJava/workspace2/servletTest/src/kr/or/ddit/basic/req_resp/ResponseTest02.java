package kr.or.ddit.basic.req_resp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseTest02
 */
@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- redirect
		 		1) 다른 페이지로 넘어가게 한다. (직접 파라미터를 넘길 수 없다.)
		 		2) 응답시 브라우저에게 '이동할 URL'을 전송하면 브라우저가 해당 URL로 이동 재요청하는 방식이다.
		 		3) 재요청할 때는 GET방식으로 요청하기 때문에 URL의 길이에 제한이 있다.
		 */
			// redirect는 Request객체를 유지하지 못한다.
			// (이유 : 브라우저에서 새롭게 요청하기 때문에...)
		/*
		 * request.setAttribute("tel", "010-1234-5678");
		 * 
		 * response.sendRedirect(request.getContextPath() + "/responseRedirectTest.do");
		 */
		
		// redirect로 데이터를 보내려면 GET방식으로 보낼 수 있다.
		String name = request.getParameter("username");
		String tel = "010-9876-5432";
		response.sendRedirect(request.getContextPath() + "/responseRedirectTest.do?username=" + name + "&tel=" + tel);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
