package kr.or.ddit.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.member.MemberVO;

import com.ibatis.sqlmap.client.SqlMapClient;

/*
 	SqlMapClient객체를 얻어서 mapper를 수행 - service로 리턴
 	
 	service에서 사용할 자신의 객체를 생성 - 리턴하는 get()메서드를 만든다.
 */

public class DaoImpl implements IDaoSample {

	private SqlMapClient client;
	private static IDaoSample dao;		// interface로 리턴받는 것은 표준 설계도이기 때문에 다형성이 좋다.
										// static은 dao객체를 공유하기 위함이다.
	
	// 생성자
	// private하는 이유 : 다른 곳에서 new (객체 생성)을 하지 못하도록 위함
	private DaoImpl(){
		client = SqlMapClientFactory.getClient();
	}
	
	public static IDaoSample getDao(){
		if(dao == null) dao = new DaoImpl();
		return dao;
	}
	
	
	
	@Override
	public List<MemberVO> getMemberAll() {
		List<MemberVO> list = null;
		
		// 메소드 이름과 아이디 이름을 같게 하면 나중에 편하다!
		try {
			
			// mapper에서 수행된 결과를 받는다.
			list = client.queryForList("member.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
