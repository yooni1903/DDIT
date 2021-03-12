package kr.or.ddit.basic;

import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/*
	LPROD테이블에 새로운 데이터를 추가하시오.
	조건) lprod_gu와 lprod_nm은 직접 입력 받아서 처리하고
		  lprod_id는 현재의 lprod_id 중 제일 큰 값보다 1 증가하여 처리한다.
		  그리고, 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
*/

// jdbcTEst 프로젝트에 잇는 'jdbcTest06.java'의 처리방법을
// ibatis를 이용하여 처리하는 것으로 변경하시오.

// 쿼리문이 저장될 xml문서 이름은 'jdbc06.xml'로 한다.
public class JdbcTest06ToIbatis {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			// max id 가져오는 과정
			LprodVO lprodVo = new LprodVO();
			int lprodId = (Integer) smc.queryForObject("lprod2.maxLprodId");
			lprodVo.setLprod_id(lprodId);
			
			System.out.print("새로 등록할 lprod_gu를 입력 : ");
			String lprodGu = scan.next();
			lprodGu = (lprodGu + "      ").substring(0,4);
			
			LprodVO chk = (LprodVO) smc.queryForObject("lprod2.chkGu", lprodGu);
			if(chk != null) {
				System.out.println("이미 존재하는 lprod_Gu입니다.");
				System.out.println("작업을 종료합니다.");
				return;
			}
			
			System.out.print("새로 등록할 lprod_nm을 입력 : ");
			String lprodNm = scan.next();
			lprodVo.setLprod_gu(lprodGu);
			lprodVo.setLprod_nm(lprodNm);
			
			Object obj = smc.insert("lprod2.insertLprod", lprodVo);
			if(obj == null) {
				System.out.println("insert 작업 성공!!!");
			}else {
				System.out.println("insert 작업 실패ㅠㅜ");
			}
			System.out.println("insert 작업 끝!!!!");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
