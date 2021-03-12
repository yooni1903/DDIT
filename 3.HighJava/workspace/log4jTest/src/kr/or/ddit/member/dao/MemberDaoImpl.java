package kr.or.ddit.member.dao;

import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.SqlMapUtil;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	private SqlMapClient smc;
	private static MemberDaoImpl memDao;
	
	private  MemberDaoImpl() {
		smc = SqlMapUtil.getSqlMapClient();
	}
	
	public static MemberDaoImpl getInstance() {
		if(memDao == null) memDao = new MemberDaoImpl();
		return memDao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("mymember.insertMember", memVo);
			if(obj == null) cnt = 1;
		} catch (SQLException e) {
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
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> list = null;
		
		try {
			list = smc.queryForList("mymember.getAllMember");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		
		int count = 0;		// 회원 ID개수가 저장될 변수
		try {
			count = (int) smc.queryForObject("mymember.getCnt", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return count;
	}


	@Override
	public int updateMember2(Map<String, String> paramMap) {
		int cnt = 0;
		try {
			cnt = smc.update("mymember.updateMember2", paramMap);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	

}
