package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private static MemberServiceImpl memService;
	
	
	private IMemberDao memDao; // Dao객체를 구현한 클래스 이름이 아닌 인터페이스 이름
	
	// 생성자
	private MemberServiceImpl() {
//		memDao = new MemberDaoImpl();
		memDao = MemberDaoImpl.getInstance();	// 객체 생성할 땐 getInstance()메서드 호출해서 
	}
	
	public static MemberServiceImpl getInstance(){
		if(memService == null) memService = new MemberServiceImpl();
		return memService;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		// 얘가 Dao를 호출해서 Dao에서 받은 반환값을 받아서 그걸 그대로 리턴해줌
		return memDao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return memDao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		return memDao.getAllMember();
	}

	@Override
	public int getMemberCount(String memId) {
		return memDao.getMemberCount(memId);
	}
	
//	@Override
//	public int updateMember2(String updateField, String updateData, String memId){
//		return memDao.updateMember2(updateField, updateData, memId);
//	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return memDao.updateMember2(paramMap);
	}

	@Override
	public MemberVO getMember(String memId) {
		return memDao.getMember(memId);
	}

}
