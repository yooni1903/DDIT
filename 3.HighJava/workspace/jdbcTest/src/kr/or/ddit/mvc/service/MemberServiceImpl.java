package kr.or.ddit.mvc.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDao;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	// Service 객체는 Dao객체를 사용하기 때문에
	// Dao 객체가 저장될 변수가 필요하다.
	// IMemberDao로 선언하고 생성자에서 MEmberDaoImpl로 생성하는 이유는 다형성 때문이다! 
	// => 현재는 oracle 문법으로 작성을 했지만 나중에는 다른 문법으로도 바꿔 사용할 수 잇도록 하기 위해
	private IMemberDao memDao;
	
	// 생성자
	public MemberServiceImpl(){
		memDao = new MemberDaoImpl();
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
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

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return memDao.updateMember2(paramMap);
	}

	
	
	

}
