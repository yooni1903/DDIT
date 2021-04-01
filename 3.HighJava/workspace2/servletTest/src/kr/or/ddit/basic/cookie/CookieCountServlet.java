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
 * Servlet implementation class CookieCountServlet
 */
@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿠키변수는 'count'로 하기로 한다.
		response.setCharacterEncoding("utf-8");
		
		// count라는 쿠키변수가 있는지 검ㅅ하ㅏㄴ다.
		Cookie[] cookieArr = request.getCookies();
		int count = 0;
		
		for(Cookie cookie : cookieArr) {
			String name = cookie.getName();
			if("count".equals(name)) {
				// 쿠키값(현재의 count값)
				String value = cookie.getValue();
				count = Integer.parseInt(value);
			}
		}
		
		count++;	// count값 증가하기
		
		
		// 증가된 count가 저장된 쿠키 객체 생성
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		response.addCookie(countCookie);
		
		response.setContentType("text.html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
	
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Cookie 연습</title>");
		out.println("<body>");
		out.println("<h2>어서오세요. 당신은" + count + "번째 방문입니다.</h2><hr>");
		out.println("<a href='"
				+ request.getContextPath() +"/cookieCountServlet.do'>카운트 증가하기</a><br><br>");
		
		out.println("<a href ='"
				+ request.getContextPath() +"'/basic/02/cookieCount.jsp>시작문서로 가기</a><br><br>");
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
