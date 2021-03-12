package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class JdbcTest06Sem {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Scanner scan = new Scanner(System.in);
		try {
			/*Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"KDY11",
					"java");*/
			conn = DBUtil.getConnection();
			
			// Lprod_id는 현재의 Lprod_id 중 제일 큰 값보다 1큰 값으로
			String sql = "select max(lprod_id) + 1 as maxid from lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int lprodId = 0;
			if(rs.next()){
				lprodId = rs.getInt("maxid");
			}
			
			// 입력받은 Lprod_gu가 이미있으면 다시 입력하기
			String lprodGu;
			int count = 0;
			do{
				System.out.print("상품 분류 코드 입력 : ");
				lprodGu = scan.next();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, lprodGu);
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				if(count == 1){
					System.out.println("입력한 상품 분류 코드 " + lprodGu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요");
				}
				
			}while(count == 1);
			
			System.out.println("상품 분류명 입력 : ");
			String lprodNm = scan.next();
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ "values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, lprodId);
			pstmt.setString(2, lprodGu);
			pstmt.setString(3, lprodNm);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0){
				System.out.println("등록성공");
			}else{
				System.out.println("등록 실패");
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			// 5. 자원 반납하기
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}

}
