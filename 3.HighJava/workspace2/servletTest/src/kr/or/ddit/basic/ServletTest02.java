package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/*
	이 예제는 애너테이션(@webServlet)을 이용해서 Servlet을 설정하여 처리하는 예제
	
*/

/*
 	@webServlet 애너테이션의 속성들
 	 1. name : 서블릿 이름을 설정한다. (기본값 : 빈 문자열 - "")
 	 2. urlPatterns : 서블릿의 URL패턴의 목록을 설정한다. (기본값 : 빈 배열- {})
 	 	예) urlPatterns = "/url1" 또는 urlPatterns={"/url1"}
 	 			==> 패턴이 1개일 경우의 표기법
 	 	예) urlPatterns = {"/url1", "/url2", ... }
 	 			==> 패턴이 2개 이상일 경우의 표기법
 	 3. value : urlPatterns와 동일한 기능을 한다.
 	 4. description : 주석을 설정한다.			
  
 */

@WebServlet(urlPatterns = {"/servletTest02.lee"}, description = "애너테이션을 이용한 서블릿 설정")
public class ServletTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			// 처리한 내용 출력하기
			// 방법2 : println() 메서드 이용하기
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='utf-8'>");
			out.println("<title>두번째 Servlet 연습</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1 style='text-align:center;'>");
			out.println("안녕하세요. 두번째 Servlet 프로그램입니다. <br>");
			out.println("Server at :  " + request.getContextPath());
			out.println("</h1>");
			out.println("</body>");
			out.println("</html>");
			
		
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
