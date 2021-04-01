package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieReadServlet
 */
@WebServlet("/cookieReadServlet.do")
public class CookieReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	- 저장된 쿠키 읽어오기
		 	1) 전체 쿠키 정보를 Request객체를 통해서 가져온다.
		 		==> 가져온 쿠키 정보들은 배열에 저장된다.
		 		형식) Cookie[] 쿠키배열변수 = request변수.getCookies();
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
			out.println("<h2>저장된 Cookie 데이터 확인하기</h2><hr>");
			
			if(cookieArr == null || cookieArr.length == 0) {
				out.println("<h1>저장된 쿠키가 하나도 없습니다.</h1>");
			}else {
				// 2) 쿠키 배열에서 해당 쿠키 정보를 구해온다.
				for(Cookie myCookie : cookieArr) {
					String name = myCookie.getName();		// '쿠키변수'
					
					// cookie변수.getValue(); ==> '쿠키값' 가져오기 
					// 쿠키값을 가져올 때는 쿠키값에 한글이 포함되어 있을 수 있기 때문에 
					// URLDecoder.decode()메서드를 이용해서 디코딩하여 사용한다.
					String value = URLDecoder.decode(myCookie.getValue(), "utf-8");
					
					out.println("쿠키변수 : " + name + "<br>");
					out.println("쿠키 값 : " + value + "<br><br>");
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
