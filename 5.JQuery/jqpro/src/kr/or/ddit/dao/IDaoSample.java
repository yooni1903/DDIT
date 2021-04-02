package kr.or.ddit.dao;

import java.util.List;

import kr.or.ddit.member.MemberVO;

public interface IDaoSample {
	
	// 메서드 선언(실제 구현은 클래스에서 한다.)
	public List<MemberVO> getMemberAll();
}
