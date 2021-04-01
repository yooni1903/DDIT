package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieDeleteServlet
 */
@WebServlet("/cookieDeleteServlet.do")
public class CookieDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- 저장된 쿠키 정보 삭제하기
		 	1) 쿠키 데이터의 삭제는 쿠키의 남은 수명을 0으로 설정하는 방법을 사용한다.
		 	   쿠키의 수명은 addCookie()메서를 호출하기 전에 해당 쿠키객체의
		 	   setMaxAge()메서드를 사용한다.
		 	형식) Cookie cookie변수 = new Cookie("쿠키변수", "쿠키값");
		 	형식) cookie변수.setMaxAge(0);
		 	형식) response.addCookie(cookie변수)
		 */
		
			Cookie[] cookieArr = request.getCookies();
			
			response.setCharacterEncoding("utf-8");
			response.setContentType("text.html; charset=utf-8");
			
			PrintWriter out = response.getWriter();
		
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head><meta charset='utf-8'>");
			out.println("<title>Cookie 연습</title>");
			out.println("<body>");
			out.println("<h2>저장된 Cookie 데이터 삭제하기</h2><hr>");
			
			if(cookieArr == null || cookieArr.length == 0) {
				out.println("<h1>저장된 쿠키가 하나도 없습니다.</h1>");
			}else {
				// 반복문을 이용하여 쿠키 정보를 가져온다.
				for(Cookie myCookie : cookieArr) {
					String name = myCookie.getName();		// 쿠키변수 구하기
					
					// 삭제를 원하는 쿠키변수를 찾아서 삭제 작업을 진행한다.
					// 예) gender쿠키 삭제하기
					if("gender".equals(name)) {
						// 해당 쿠키의 setMaxAge()메서드를 호출해서 삭제한다.
						myCookie.setMaxAge(0);
						
						// 해당 쿠키를 다시 추가한다.
						response.addCookie(myCookie);
					}
				}
			}
			
			out.println("<a href='" + request.getContextPath() + "/basic/02/cookieTest.jsp'>시작문서로 이동하기</a>");
			
			out.println("</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
