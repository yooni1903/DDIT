package kr.or.ddit.basic.cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html); charset=utf-8");
		
		// Form에서 넘어오는 Parameter값들을 구한다.
		String userId = request.getParameter("userid");	// userid값 받기
		String pass = request.getParameter("pass");		// pass값 받기
		String chk = request.getParameter("chkid");		// 체크박스의 체크여부값 받기
		
		// 일단 Cookie 객체 생성
		Cookie cookie = new Cookie("userId", userId);
		
		// checkbox의 체크 여부에 따라 쿠키값을 저장한다.
		if(chk != null) {	// 체크박스는 애초에 체크되어있지 않으면 값이 넘어오지 않는다. null이 아니라는 얘기는 체크되어있다는 뜻
			response.addCookie(cookie);			// 쿠키 저장
		}else {				// 체크 안된 상태일 때
			cookie.setMaxAge(0);				// 쿠키의 유효시간을 0으로 설정하여 쿠키를 삭제한다.
			response.addCookie(cookie);
		}
		
		// 로그인 성공 여부 검사하기(성공 id값 : test, password : 1234)
		if(userId != null && pass != null) {	// 해당 변수의 null값 여부 검사
			if(userId.equals("test") && pass.equals("1234")){
				// 로그인 성공시 cookieMain.jsp로 이동
				response.sendRedirect(request.getContextPath() + "/basic/02/cookieMain.jsp");
			}else {
				// 로그인 실패시 cookieLogin.jsp로 이동
				response.sendRedirect(request.getContextPath() + "/basic/02/cookieLogin.jsp");
				
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
