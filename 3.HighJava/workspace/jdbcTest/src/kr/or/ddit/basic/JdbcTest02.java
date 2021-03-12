package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제1) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다
//		 lprod_id가 큰 자료들을 출력하시오.

// 문제2) lprod_id값을 2개 입력받아 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오.

public class JdbcTest02 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"KDY11",
					"java");
			System.out.println("lprod_id를 입력하세요");
			int input = scan.nextInt();
			
			String sql = "select * from lprod where lprod_id >" + input;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println(" == 결과 출력 ==");
			while(rs.next()){
				
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("Lprod_gu : " + rs.getString(2));
				System.out.println("Lprod_nm : " + rs.getString("LPROD_NM"));
				System.out.println("---------------------------------------");
			
			}
			System.out.println("출력 끝!!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		
	}

}
