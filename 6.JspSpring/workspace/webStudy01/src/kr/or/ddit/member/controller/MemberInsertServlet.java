package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/member/memberInsert.do")
public class MemberInsertServlet extends HttpServlet {
	private IMemberService service = new MemberServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/WEB-INF/views/member/memberForm.jsp";
		boolean redirect = false;
		// logic
		if (redirect) {
			resp.sendRedirect(req.getContextPath() + view);
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 1. 요청 접수
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		
		
		// 2. 검증(필수 데이터, 데이터의 길이, 데이터의 타입 등등 모든 것을 검증해야함)
		Map<String, String> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		boolean valid = validate(member, errors);
		boolean redirect = false;
		String view = null;
		String message = null;
		if (valid) {
			ServiceResult result = service.createMember(member);
			switch (result) {
			case PKDUPLICATED:
				view = "/WEB-INF/views/member/memberForm.jsp";
				message = "아이디 중복";
				break;
			case OK:
				redirect = true;
				view = "/login/loginForm.jsp";
//				view = "/webStudy01/login/loginForm.jsp";
				break;
			default:
				view = "/WEB-INF/views/member/memberForm.jsp";
				message = "서버 오류, 잠시 후 다시 시도하세요.";
				break;
			}
		} else {
			// 검증 불통
			view = "/WEB-INF/views/member/memberForm.jsp";

		}

		req.setAttribute("message", message);

		if (redirect) {
			resp.sendRedirect(req.getContextPath() + view);
		} else {
			req.getRequestDispatcher(view).forward(req, resp);
		}

	}

	private boolean validate(MemberVO member, Map<String, String> errors) {
		boolean valid = true;

		if (member.getMem_id() == null || member.getMem_id().isEmpty()) {
			valid = false;
			errors.put("mem_id", "회원 아이디 누락");
		}
		if (member.getMem_pass() == null || member.getMem_pass().isEmpty()) {
			valid = false;
			errors.put("mem_pass", "비밀번호 누락");
		}
		if (member.getMem_name() == null || member.getMem_name().isEmpty()) {
			valid = false;
			errors.put("mem_name", "이름 누락");
		}
		if (member.getMem_zip() == null || member.getMem_zip().isEmpty()) {
			valid = false;
			errors.put("mem_zip", "우편번호 누락");
		}
		if (member.getMem_add1() == null || member.getMem_add1().isEmpty()) {
			valid = false;
			errors.put("mem_add1", "주소1 누락");
		}
		if (member.getMem_add2() == null || member.getMem_add2().isEmpty()) {
			valid = false;
			errors.put("mem_add2", "주소2 누락");
		}
		if (member.getMem_mail() == null || member.getMem_mail().isEmpty()) {
			valid = false;
			errors.put("mem_mail", "이메일 누락");
		}

//		if(member.getMem_id() == null || member.getMem_id().isEmpty()) {
//			valid = false;
//			errors.put("mem_id", "아이디 누락");
//		}
//		if (member.getMem_pass() == null || member.getMem_pass().isEmpty()) {
//			valid = false;
//			errors.put("MEM_PASS", "비밀 번호 누락");
//		}
//		if (member.getMem_name() == null || member.getMem_name().isEmpty()) {
//			valid = false;
//			errors.put("MEM_NAME", "이름 누락");
//		} 
//		if (member.getMem_zip() == null || member.getMem_zip().isEmpty()) {
//			valid = false;
//			errors.put("MEM_ZIP", "우편번호 누락");
//		} 
//		if (member.getMem_add1() == null || member.getMem_add1().isEmpty()) {
//			valid = false;
//			errors.put("MEM_ADD1", "주소1 누락");
//		} 
//		if (member.getMem_add2() == null || member.getMem_add2().isEmpty()) {
//			valid = false;
//			errors.put("MEM_ADD2", "주소2 누락");
//		} 
//		if (member.getMem_mail() == null || member.getMem_mail().isEmpty()) {
//			valid = false;
//			errors.put("MEM_MAIL", "이메일 누락");
//		}

		return valid;
	}
}
