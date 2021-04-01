package kr.or.ddit.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/mypage.do")
public class MypageServlet extends HttpServlet{
	IMemberService service = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		// 로그인 되어있는지 아닌지 항상 확인을 해줘야하는데 그건
		// 나중에 filter라는 것을 이용하여 없앨 것임!
		
		String mem_id = authMember.getMem_id();
		MemberVO detailMember =  service.retrieveMember(mem_id);
		
		req.setAttribute("member", detailMember);
		String view = "/WEB-INF/views/member/mypage.jsp";
		req.getRequestDispatcher(view).forward(req, resp);
	}
	
	
	
	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(req, resp);
//	}
}
