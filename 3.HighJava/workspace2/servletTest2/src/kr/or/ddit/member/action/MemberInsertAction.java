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
		return true;		// 아래의 process()메서드의 반환값이 요청 URI일 경우에 true를 반환한도록하여 지정한 요청 URI로 redirect 시킨다.
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
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
		service.insertMember(memVo);		// DB에 insert하는 작업
		
		
		return "/member/memberList.ddit";	// 리스트로 돌아감
	}

}
