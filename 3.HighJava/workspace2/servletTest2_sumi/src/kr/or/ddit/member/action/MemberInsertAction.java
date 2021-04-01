package kr.or.ddit.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.web.IAction;

public class MemberInsertAction implements IAction {

	@Override
	public boolean isRedirect() {
		// 아래의 process()메서드의 반환값이 요청 URI일 경우에 true를 반환하도록 하여
		// 지정한 요청 URI로 redirect시킨다.
		return true;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mem_id = request.getParameter("mem_id");
		String mem_name = request.getParameter("mem_name");
		String mem_tel = request.getParameter("mem_tel");
		String mem_addr = request.getParameter("mem_addr");
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(mem_id);
		memVo.setMem_name(mem_name);
		memVo.setMem_tel(mem_tel);
		memVo.setMem_addr(mem_addr);
		
		IMemberService service = MemberServiceImpl.getInstance();
		service.insertMember(memVo);	// DB에 Insert하기
		
		// 주소창이 바뀌게 하기 위해선 sendRedirect로 바꿔야 함 > 위의 메서드 수정
		// 반환값으로 가고 싶은 URI입력하면 됨
		return "/member/memberList.ddit";
	}

}
