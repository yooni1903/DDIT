package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	LPROD테이블에 새로운 데이터를 추가하시오.
 	
 	조건) lprod_gu와 lprod_nm은 직접 입력 받아서 처리하고
 		  lprod_id는 현재의 lprod_id 중 제일 큰 값보다 1 증가하여 처리한다.
 		  그리고, 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
 */
public class JdbcTest06 {

	public static void main(String[] args) {
Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"KDY11", "java");
			stmt = conn.createStatement();
			System.out.println("LPROD 추가");
			String lprodGu;
			String sql1;
			while(true){
				System.out.print("분류번호 입력 : ");
				lprodGu = scan.next();
				sql1 = "select count(*) from lprod where lprod_gu = upper('" + lprodGu +"')";
				
				rs = stmt.executeQuery(sql1);
				int cnt = 0;
				while(rs.next()){
					cnt = rs.getInt("count(*)");
				}
				if(cnt == 0){
					break;
				}
				System.out.println("이미 있는 분류번호입니다. 다시 입력하세요");
			}
			
			System.out.println("분류명 입력 : ");
			String lprodNm = scan.next();
			
			String sql2 = "select max(lprod_id) + 1 as lprod_id from lprod";
			rs = stmt.executeQuery(sql2);
			int lprodId = 0;
			
			while(rs.next()){
				lprodId = rs.getInt("lprod_id");
			}
			
			String sql3 = "insert into lprod(lprod_id, lprod_gu, lprod_nm) "
					+ "values ('" +  lprodId + "', ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setString(1, lprodGu);
			pstmt.setString(2, lprodNm);
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값 : " + cnt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			// 5. 자원 반납하기
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}

}
