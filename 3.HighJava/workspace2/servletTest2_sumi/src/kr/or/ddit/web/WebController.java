package kr.or.ddit.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WebController")
public class WebController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		/* 
		 * URL ==> http://서버이름또는IP주소:포트번호/컨텍스트이름/폴더명/문서명
		 * 	   ==> http://localhost:80/servletTest/member/memberList.ddit
		 * 컨텍스트 이름 > 주로 프로젝트 만들 때 프로젝트 이름
		 * 
		 * URI ==> 컨텍스트 이름부터~ 
		 * 	   ==>  /servletTest/member/memberList.ddit
		 * 
		 * ContextPath ==> /servletTest
		 * 
		 * 원하는 요청URI ==> /member/memberList.ddit
		 *  > URI구하는 메서드가 있고 컨텍스트구하는 메서드 있음
		 *  > URI에서 컨텍스트 빼면 됨
		 */
		
		// 1. 사용자의 요청 정보 가져오기
		String uri = req.getRequestURI();	// 전체 요청 URI
		
		// 원하는 요청URI
		uri = uri.substring(req.getContextPath().length());
		
		String viewPage = null;
		IAction action = null;
		
		action = URIActionMapper.getAction(uri);
		
		if(action == null) {	// 실행할 URI가 없으면 404에러 처리
			// 404에러를 어떻게 처리하냐..
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}else {
			// 실행부분 ==> 작업 처리 후 view페이지를 받는다.
			viewPage = action.process(req, resp);
			
			if(viewPage !=null) {
				if(action.isRedirect()) {
					resp.sendRedirect(req.getContextPath() + viewPage);
				}else {
					RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view" + viewPage);
					rd.forward(req, resp);
				}
			}
		}
	}
}
