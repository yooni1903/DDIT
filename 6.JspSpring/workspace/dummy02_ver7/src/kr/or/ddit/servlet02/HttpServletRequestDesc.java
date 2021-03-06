package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServletRequest : 클라이언트와 요청에 대한 모든 정보를 캡슐화한 객체
 * 
 * Http의 요청 패키지 방식
 * 	1. Request Line : URL, request method, protocol
 * 		1) request method : 행위 + 방식(수단)
 * 			- GET(R) : 서버 상의 자원을 조회하는 목적 (method의 기본 설정)
 * 			- POST(C) : 신규 등록
 * 	        - PUT/PATCH(U) : 갱신
 * 			- DELETE(D) : 삭제
 * 
 * 			- OPTION : preflight 요청에 사용
 * 			- HEADER : body가 응답을 요청할 때 사용한다.
 * 			- TRACE : 서버 디버깅 요청에 사용 (보통의 클라이언트에게 서버 디버깅을 허용하지 않기 때문에 잘 쓰이지 않는 method이다.)
 * 			// GET과 POST는 기본적으로 모든 서블릿에서 지원하는 method
 *			// 나머지는 서버에 따라 지원이 달라진다.
 *			// 때문에 먼저 서버가 이 메서드를 지원하는지 검사해봐야하는데 이를 preflight라고 한다.
 * 			
 *  2. Request Header : meta data(부가데이터) 영역
 *  	Accept, Content-Type, User-Agent
 *  3. Request Body(only POST) : 서버로 전송할 컨텐츠
 *
 */
@WebServlet("/requestDesc.do")
public class HttpServletRequestDesc extends HttpServlet{
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //do메소드의 사용하지않겠다 라는 의미 super을 지우는것은 
      //메소드 상관없이 호출하는?
      String uri = req.getRequestURI();
      String method = req.getMethod();
      String protocol = req.getProtocol();
      System.out.printf("Request Line : %s %s %s\n", uri, method, protocol);
      System.out.println("==================Request Header ================");
      
      Enumeration<String> names= req.getHeaderNames();
      while (names.hasMoreElements()) {
         String headerName = (String) names.nextElement();
         String headerValue = req.getHeader(headerName);
         System.out.printf("%s : %s\n", headerName, headerValue);
      }
      
      System.out.println("==================Request Body ================");
//      InputStream is = req.getInputStream();
//      int byteLength = is.available();
//      System.out.printf("body length : %d", byteLength);
      
      // RFC2397 규약 - 특수문자를 percent encoding(URL encoding) 방식으로 인코딩됨.
      req.setCharacterEncoding("utf-8");	//	애는 request에 body가 있을 경우에만 인코딩해준다.
      
      
      System.out.println("==================Request Parameter ================");
      Map<String, String[]> parameterMap =req.getParameterMap();
      Set<String> parameterNames= parameterMap.keySet();
      Iterator<String> it = parameterNames.iterator();
      while (it.hasNext()) {
         String parameterName = (String) it.next();
         String[] values = req.getParameterValues(parameterName);
         System.out.printf("%s: %s\n", parameterName, Arrays.toString(values));
         
      }
      }
}


