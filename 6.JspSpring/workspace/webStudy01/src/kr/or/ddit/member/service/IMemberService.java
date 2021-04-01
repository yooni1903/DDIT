package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리(CRUD)를 위한 Business Logic Layer
 *
 */
public interface IMemberService {
	/**
	 * 회원 정보 상세 조회
	 * @param mem_id
	 * @return 존재하지 않으면, custom exception 발생
	 */
	public MemberVO retrieveMember(String mem_id);
	
	/**
	 * 신규 등록
	 * 
	 * business logic layer에서는 persistence layer에서 어떤 쿼리를 사용할지 
	 * 모르기 때문에 메서드 이름을 행위에 관하여 지어준다.
	 * (insert X, create O)
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	
	/**
	 * 회원 수정
	 * @param member
	 * @return 존재하지 않으면, custom exception 발생,
	 * 		   INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않으면, custom exception 발생,
	 * 		   INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
	
	/**
	 * 회원 목록 조회
	 * @return 조건에 맞는 회원이 없으면, size() == 0
	 */
	public List<MemberVO> retrieveMemberList();
}
