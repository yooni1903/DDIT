package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04Sem {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KDY11", "java");
			
			System.out.print("검색할 주소를 입력 : ");
			String addr = scan.next();
			
			String sql = "select * from member where mem_add1 like '%" + addr +"%'";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println();
			System.out.println(" == 검색 결과 ==");
			while(rs.next()){
				System.out.println("ID : " + rs.getString("mem_id"));
				System.out.println("이름 : " + rs.getString("mem_name"));
				System.out.println("우편번호 : " + rs.getString("mem_zip"));
				System.out.println("주소 : " + rs.getString("mem_add1") + " " + rs.getString("mem_add2"));
				System.out.println("-------------------------------------------------");
			}
			System.out.println("출력 끝");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			// 5. 자원 반납하기
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}

}
