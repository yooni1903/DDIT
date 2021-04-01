package kr.or.ddit.basic;

import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {
	// iBatis를 이용하여 DB자료를 처리하는 방법 및 순서
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. iBatis의 환경 설정 파일(sqlMapConfig.xml)을 읽어와서 실행하기
		try {
			// 1-1. 문자 인코딩 캐릭터셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경 설정 파일 읽어오기
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경 설정을 진행한 후 SQL문을 호출해서 실행할 객체를 생성한다.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			
			// -----------------------------------------------------------------------------------
			
			// 2. 실행할 SQL문에 맞는 문장을 찾아서 호출하여 실행한다.
			
			// 2-1. insert작업
//			System.out.println("insert 작업 시작");
//			System.out.print("lprod_id 입력 : " );
//			int lprodId = scan.nextInt();
//			
//			System.out.print("lprod_gu 입력 : ");
//			String lprodGu = scan.next();
//			
//			System.out.print("lprod_nm 입력 : ");
//			String lprodNm = scan.next();
//			
//			// 1) 저장할 데이터를 VO객체에 담는다.
//			LprodVO lvo = new LprodVO();
//			
//			lvo.setLprod_id(lprodId);
//			lvo.setLprod_gu(lprodGu);
//			lvo.setLprod_nm(lprodNm);
//			
//			// 2) sqlMapClient 객체변수를 이용하여
//			//	  처리할 쿼리문을 초월하여 실행한다.
//			// 형식) smc.insert("namespace값.id속성값", 파라미터클래스);
//			//		 반환값 : insert 성공 : null, insert 실패 : 오류 객체
//			Object obj = smc.insert("lprod.insertLprod", lvo);
//			if(obj == null) {
//				System.out.println("insert 작업 성공!!!");
//			}else {
//				System.out.println("insert 작업 실패ㅠㅜ");
//			}
//			System.out.println("insert 작업 끝!!!!!!!!!!!");
			
			// 2-2. update 작업
//			System.out.println("update 작업 시작");
//			System.out.print("작업할 lprod_gu 입력 : ");
//			String lprodGu = scan.next();
//			lprodGu = (lprodGu + "      ").substring(0,4);
//			
//			System.out.print("새로운 lprod_id 입력 : " );
//			int lprodId = scan.nextInt();
//			
//			System.out.print("새로운 lprod_nm 입력 : ");
//			String lprodNm = scan.next();
//			
//			
//			// 1) 수정할 데이터를 VO객체에 담는다.
//			LprodVO lvo2 = new LprodVO();
//			lvo2.setLprod_gu(lprodGu);
//			lvo2.setLprod_id(lprodId);
//			lvo2.setLprod_nm(lprodNm);
//			
//			// 2) 형식) smc.update("namespace값.id속성값", 파라미터클래스);
//			// 			반환값 : 성공한 레코드 수
//			int cnt = smc.update("lprod.updateLprod", lvo2);
//			if(cnt > 0) {
//				System.out.println("update 작업 성공!!!!");
//			}else {
//				System.out.println("update 작업 실패ㅜㅠㅜ");
//			}
//			System.out.println("update 작업 끝!!!!!");
			
			
//			// 2-3. delete 작업
//			System.out.println("delete 작업 시작...");
//			System.out.print("삭제할 lprod_gu 입력 : ");
//			String lprodGu = scan.next();
//			lprodGu = (lprodGu + "      ").substring(0,4);
//			
//			// 형식) smc.delete("namespace값.id속성값", 파라미터클래스);
//			// 		 반환값 : 작업에 성공한 레코드 수
//			int cnt2 = smc.delete("lprod.deleteLprod", lprodGu);
//			
//			if(cnt2 > 0) {
//				System.out.println("delete 작업 성공!!!!!!!");
//			}else {
//				System.out.println("delete 작업 실패ㅜㅠㅜ");
//			}
//			System.out.println("delete 작업 끝!!!!!!!!!");
//			
			
			
			
			// 2-4. select 작업
			
			// 1) select 응답 결과가 여러 개인 경우
//			System.out.println("select한 결과가 어러 개일 경우");
//			
//			// 응답 결과가 어러 개일 경우에는 queryForList()메서드를 사용하는데 
//			// 이 메서드는 여러 개의 레코드 각각은 resultClass에 지정한 VO클래스에 
//			// 담은 후에 이 VO클래스를 List에 추가해주는 작업을 자동으로 수행한다.
//			
//			// 형식) smc.qureyForList("namespace값.id속성값", 파라미터클래스);
//			
//			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");
//			for(LprodVO lvo : lprodList) {
//				System.out.println("ID : " + lvo.getLprod_id());
//				System.out.println("GU : " + lvo.getLprod_gu());
//				System.out.println("NM : " + lvo.getLprod_nm());
//				System.out.println("-----------------------------------------");
//			}
//			System.out.println("출력 끝!!!!!!!!!!!!");
			
			// 2) select 결과가 1개일 경우
			System.out.println("select한 결과가 1개일 경우");
			System.out.print("검색할 lprod_gu 입력 : " );
			String lprodGu = scan.next();
			lprodGu = (lprodGu + "      ").substring(0,4);
			
			// select한 결과가 1개의 레코드일 경우에는
			// queryForObject()메서드를 사용한다.
			// 형식) smc.queryForObject("namespace값.id속성값", 파라미터클래스);
			LprodVO lprodVo = (LprodVO) smc.queryForObject("lprod.getLprod", lprodGu);
			
			if(lprodVo == null) {
				System.out.println("검색 결과 : 원하는 데이터가 없습니다.");
				return;
			}
			System.out.println("-------------검색 결과!------------------");
			System.out.println("ID : " + lprodVo.getLprod_id());
			System.out.println("GU : " + lprodVo.getLprod_gu());
			System.out.println("NM : " + lprodVo.getLprod_nm());
			System.out.println("-----------------------------------------");
			System.out.println("출력 끝!!!");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
