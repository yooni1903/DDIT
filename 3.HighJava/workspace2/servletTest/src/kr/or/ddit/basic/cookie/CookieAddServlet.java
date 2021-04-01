package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieAddServlet
 */
@WebServlet("/cookieAddServlet.do")
public class CookieAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- Cookie 저장하는 방법
		 	  1) Cookie객체를 생성한다. ==> '쿠키변수'와 '쿠키값'은 문자열로 지정한다.
		 	  	 형식) Cookie cookie변수 = new Cookie("쿠키변수", "쿠키값");
		 	  	 	==> '쿠키값'으로 한글을 사용할 경우에는 URLEncoder.encode()메서드로 인코딩한 후 저장한다.
		 */
		Cookie nameCookie = new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
		int age = 25;
		Cookie ageCookie = new Cookie("age", String.valueOf(age));			// String.valueOf()  String 타입으로 바꿔주는 메서드
		Cookie genderCookie = new Cookie("gender", "Male");
		
		/*
		 	2) 쿠키 속성 설정
		 	   - cookie변수.setPath("적용경로");
		 	    	==> 지정한 경로와 그 하위 경로에서 쿠키를 사용할 수 있다.
		 	    	==> 생략하면 쿠키를 설정한 당시의 경로가 설정된다.
		 	   - cookie변수.setMaxAge(유지시간); ==> 단위 : 초      // 시간을 지정하면 그 시간동안만 갖고 있다가 그 시간이 지나면 삭제된다.
		 	   		==> 유지시간이 -1이면 웹 브라우저가 종료될 때까지 유지된다. (기본값)
		 	   		==> 유지시간이 0이면 즉시 삭제된다.(= 0초동안 유지하라는 말이니까 바로 삭제되는거)
		 	   - cookie변수.setDomain("적용 도메인명");
		 	   		==> 예) ".ddit.or.kr"	==> "www.ddit.or.kr", "www2.ddit.or.kr"
		 	   - cookie변수.setSecure(보안여부);
		 	   		==> 값 : true(적용), false(미적용, 기본값)
		 	   		
		 	 ==> 설정값을 확인하려면 get으로 시작하는 해당 메서드를 호출하면 된다.
		 */
		
		/*
		 	3) Response객체를 이용하여 쿠키를 웹브라우저로 보내면 웹브라우저가 이 쿠키를 받아서 저장한다.
		 		형식) response객체변수.addCookie(1번에서 만든 Cookie객체);
		 */
		
			response.setCharacterEncoding("utf-8");
			response.setContentType("text.html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
			
			response.addCookie(nameCookie);
			response.addCookie(ageCookie);
			response.addCookie(genderCookie);
		
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><meta charset='utf-8'>");
			out.println("<title>Cookie 연습</title>");
			out.println("<body>");
			
			out.println("<h2>Cookie 데이터가 저장되었습니다.</h2><br><br>");
			out.println("<a href='" + request.getContextPath() + "/basic/02/cookieTest.jsp'>시작문서로 이동하기</a>");
			
			out.println("</body></html>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
