package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 	문제) 사용자로부터 도, 시, 군, 구 중 한가지를 입력 받아 DB의 Member 테이블에서 회원의 주소에 입력한 값이 포함되는 데이터를 모두 출력하시오.
 	id 이름 우편번호 주소 출력
 */

public class JdbcTest04 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("주소를 입력하세요(도/시/군/구)");
		String input = scan.nextLine();
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"KDY11",
					"java");
			String sql = "select * from member where mem_add1 like '%" + input + "%'";
			
			System.out.println("SQL => " + sql + "\n 출력 시작");
			System.out.println();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				System.out.println("MEM_ID : " + rs.getString("mem_id"));
				System.out.println("MEM_NAME : " + rs.getString("mem_name"));
				System.out.println("MEM_ZIP : " + rs.getString("mem_zip"));
				System.out.println("MEM_ADD : " + rs.getString("mem_add1") + " " + rs.getString("mem_add2"));
				System.out.println("-------------------------------------");
			}
			System.out.println("출력 끝");
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
