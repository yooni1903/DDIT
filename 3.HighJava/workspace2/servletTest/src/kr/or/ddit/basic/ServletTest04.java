package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTest04
 */
@WebServlet("/ServletTest04.do")
public class ServletTest04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	Servlet 클래스나 JSP 페이지의 환경에 관련된 정보는 javax.servlet.ServletContext 인터페이스타입의 객체를 이용해서 얻을 수 있다.
		 	
		 	- getServerInfo()메서드는 Servlet이 속한 웹 서버의 종류를 구하고,
		 	- getMajorVersion()과 getMinorVersion()메서드는 
		 	  웹 컨테이너가 지원하는 Servlet 규격서의 메이저 버전과 마이너 버전을 구할 수 있다.
		 */
		ServletContext context = this.getServletContext();
		String serverInfo = context.getServerInfo();
		int majorVer = context.getMajorVersion();
		int minorVer = context.getMinorVersion();
		
		String servletName = this.getServletName();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charet=utf-8"); 
		
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>웹 서버의 정보</title>");
		out.println("<body>");
		out.println("웹서버의 종류 (ServerInfo) : " + serverInfo + "<br>");
		out.printf("ServletName : %s<br>", servletName);
		out.printf("지원하는 Servlet버전 : (%d.%d)<br><br>", majorVer, minorVer);
		
		/*
		 	- 웹 애플리케이션의 초기화 파라미터(web.xml문서에 설정한 값)
		 		==> 웹 애플리케이션 전체에 속하는 초기화 파라미터이다.
		 	- web.xml문서의 <web-app>엘리멘트 바로 아래에 <context-param>이라는 서브 엘리먼트를 작성하고, 
		 	  그 안에 <param-name>과 <param-value>라는 서브 엘리먼트에 값들을 설정한다.
		 	- 웹 애플리케이션의 초기화 파라미터를 읽기 위해서는 
		 	  1) servlet클래스에서 getServletContext()메서드를 통해서 ServletContext객체를 구하고, 
		 	     이 객체의 getInitParameter()메서드로 값을 읽어와 사용할 수 있다.
		 	  2) JSP페이지에서는 application 내장 변수의 getInitParameter() 메서드를 사용하면 된다.
		 */
		String fileName = context.getInitParameter("FILE_NAME");
		out.printf("web.xml에 설정된 파라미터값 : %s<br>", fileName);
		
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
