package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.CryptoUtil;
import kr.or.ddit.util.SqlMapUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	private SqlMapClient smc;
	private static final String KEY = "a1b2c3d4e5f6g7h8";
	
	// 1번
	private static MemberDaoImpl memDao;
	
	// 2번	- 생성자
	private MemberDaoImpl() {
		smc = SqlMapUtil.getSqlMapClient();
	}
	
	// 3번
	public static  MemberDaoImpl getInstance(){
		if(memDao == null) memDao = new MemberDaoImpl();
		return memDao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("mymember.insertMember", memVo);
			
			if(obj == null) cnt = 1;	// insert성공 여부 판단
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			cnt = smc.delete("mymember.deleteMember", memId);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("mymember.updateMember", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = new ArrayList<>();
		try {
			memList = smc.queryForList("mymember.getAllMember");
			
		} catch (SQLException e) {
			memList = null; 
			e.printStackTrace();
		} 
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("mymember.getMemberCount", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// Key값 정보 => 회원ID(memid), 수정할 컬럼명(field), 수정할 데이터(data)
		int cnt = 0;
		
		try {
			cnt = smc.update("mymember.updateMember2", paramMap);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO memVo = null;
		
		try {
			memVo = (MemberVO)smc.queryForObject("mymember.getMember", memId);
		} catch (SQLException e) {
			memVo = null;
			e.printStackTrace();
		}
		return memVo;
	}
	
}



