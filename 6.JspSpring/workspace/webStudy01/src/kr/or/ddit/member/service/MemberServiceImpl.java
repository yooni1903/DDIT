package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.IMemeberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemeberDAO dao = new MemberDAOImpl();

	@Override
	public MemberVO retrieveMember(String mem_id) {
		MemberVO savedMember = dao.selectMemberDetail(mem_id);
		if(savedMember == null) {
			// custom exception 발생
		}
		return savedMember;
		
		
		
//		MemberVO member = dao.selectMember(mem_id);
//		if(member != null) {
//			return member;
//		}
//		return member;
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMemberDetail(member.getMem_id()) == null) {
			int rowcnt = dao.insertMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		
		// 내가 한 코드~
//		ServiceResult result = ServiceResult.OK;
//		int cnt = 0 ;
//		MemberVO existMember = retrieveMember(member.getMem_id());
//		if(existMember != null) {
//			result = ServiceResult.PKDUPLICATED;
//		}else {
//			cnt = dao.insertMember(member);
//			if(cnt <= 0) {
//				result = ServiceResult.FAIL;
//			}
//		}
		
		return result;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		// TODO Auto-generated method stub
		return null;
	}

}
