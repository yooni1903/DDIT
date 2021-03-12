package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.action.MemberListAction;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.web.IAction;

public class TestContoller2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		/*
			URL ==> http://서버이름:포트번호/컨텍스트이름/폴더명/문서명
				==> http://localhost:80/servletTest/member/memberList.ddit
			
			URI ==> servletTest/member/memberList.ddit
			
			ContextPath ==> /servletTest
			
			원하는 요청 URI ==> /member/memberList.ddit
		*/
		
		// 1. 사용자의 요청 정보 가져오기
		String uri = req.getRequestURI();	// 전체 요청 URI
		
		// 원하는 요청 URI
		uri = uri.substring(req.getContextPath().length());			// contextPath 뒤에부터가 원하는 요청 uri이기 때문에 
																	// substring을 이용하여 자르면 요청 uri를 얻을 수 있다.
		System.out.println("uri = " + uri);
		
		String viewPage = "";		// view 페이지가 저장될 변수 
		IAction action = null;
		
		if(uri == null) {
			
		}else if("/member/memberList.ddit".equals(uri)){
			
			action = new MemberListAction();
			viewPage = action.process(req, resp);
			
		}else if("/member/memberView.ddit".equals(uri)) {
			// 회원 상세 정보 보기
			System.out.println("회원 상세 보기");
		}else if("...".equals(uri)) {
			
		}else {
			
		}
		
		// redirect로 처리할지 forward로 처리할지를 확인해서 처리한다.
		if(action.isRedirect()) {
			// redirect 처리
			resp.sendRedirect(req.getContextPath() + viewPage);
		}else {
			// 설정한 view페이지로 포워딩을 한다.
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view" + viewPage);
			// ==> /WEB-INF/view//member/memberList.jsp 로 나올 것임.
			rd.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	public void destroy() {
		
	}
}
