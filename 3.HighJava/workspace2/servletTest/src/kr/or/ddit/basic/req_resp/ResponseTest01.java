package kr.or.ddit.basic.req_resp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseTest01
 */
@WebServlet("/responseTest01.do")
public class ResponseTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
			- forward
				1) 특정 서블릿에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다.
				   (이때 파라미터를 넘길 수 있다. ==> 데이터 공유)
				2) URL주소는 처음 요청할 때의 값이 유지되고 바뀌지 않는다.
				3) redirect보다 성능이 좋다.
				4) 서버 내부에서만 접근이 가능하다.
		 */
		
		// 이동되는 페이지로 값을 넘기려면 Request객체의 setAttribute()
		// 메서드로 값을 셋팅하여 보내고, 받는 쪽에서는 getAttribute()
		// 메서드로 값을 읽어온다.
		// 형식) request객체변수.setAttribute("key값", 데이터값);
		// 형식) request객체변수.getAttribute("key값");
		request.setAttribute("tel", "010-1234-5678");
		
		RequestDispatcher rd = request.getRequestDispatcher("/responseForwardTest.do"); 	// path에는 요청 보낼 서블릿 이름을 쓴다
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
