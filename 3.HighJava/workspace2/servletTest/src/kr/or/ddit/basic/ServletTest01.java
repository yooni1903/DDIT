package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 	- 서블릿이란?
 		==> 컨테이너(서블릿 엔진)에 의해관리되는 자바 기반 컴포넌트로서 동적인 웹 컨텐츠 생성을 가능하게 해준다
 		
 	- URL 주소 : http://localhost:80/servletTest/servletTest01.do
 		- http ==> 프로토콜
 		- localhost ==> 컴퓨터 이름(도메인 명)또는 IP주소
 		- 80 ==> Port번호
 		- /servletTest ==> 컨텍스트 패스(보통 웹 프로젝트 이름으로 지정한다.)
 		- /servletTest01.do ==> 서블릿 요청 URL 패턴
 */

/*
 	이 예제는 배포 서술자(DD, Deployment Descriptor ==> web.xml)를 이용해서
 	실행할 Servlet을 설정하는 예제이다.
 */

// Servlet클래스는 HttpServlet을 상속해서 작성한다!!!!!!!!!!!!!! @@@@@@ 중요
public class ServletTest01 extends HttpServlet {
	// 직렬화 작업
	private static final long serialVersionUID = 1L;
	
	// 이 부분의 영역에서는 service()메서드 또는 doGet()메서드나 doPost()메서드를 다시 정의해서 작성한다.
	
	// doGet()메서드나 doPost()메서드는 service()메서드를 통해서 호출된다.
	
	// doGet()메서드와 doPost()메서드의 매개변수들
	// 1. HttpServletRequest객체 ==> 서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// 2. HttpServletResponse객체 ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	//---------------------------------------------------------------------------------------------------------
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청이 왔다고 가정하고 코딩
		
		// 응답 문서의 인코딩 방식 지정
		response.setCharacterEncoding("utf-8");
		
		// 응답 문서의 ContentType 지정
		response.setContentType("text/html; charset=UTF-8");
		
		// 처리한 내용을 응답으로 보내기위한 PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		
		// 처리한 내용 출력하기
		// 방법1 : append()메서드 이용하기
		out.append("<html>");
		out.append("<head>");
		out.append("<meta charset='utf-8'>");
		out.append("<title>첫번째 Servlet 연습</title>");
		out.append("</head>");
		out.append("<body>");
		out.append("<h1 style='text-align:center;'>");
		out.append("안녕하세요. 첫번째 Servlet 프로그램입니다. <br>");
		out.append("Server at :  " + request.getContextPath());
		out.append("</h1>");
		out.append("</body>");
		out.append("</html>");
	}
	
	// doPost()메서드 ==> POST방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}

}
