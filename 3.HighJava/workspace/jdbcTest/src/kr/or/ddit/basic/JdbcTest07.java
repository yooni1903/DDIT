package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 	- 회원을 관리하는 프로그램을 작성하시오.
 	(오라클 DB의 MYMEMBER 테이블 이용)
 	
 	- 아래 메뉴의 기능을 모두 구현하시오. (CRUD 구현하기 연습)
 	-- 작업 선택 --
 	1. 자료 추가
 	2. 자료 삭제
 	3. 자료 수정
 	4. 전체 자료 출력
 	0. 작업 끝.
 	----------------
 	작업선택 >>
 */

public class JdbcTest07 {
	Scanner scan;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement pstmt;
	public JdbcTest07(){
		scan = new Scanner(System.in);
		
	}
	
	public static void main(String[] args) {
		new JdbcTest07().start();
	}

	private void start() {
		System.out.println("  -- 작업 선택 -- ");
		while(true){
			int choice = displayMenu();
			switch(choice){
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				printAll();
				break;
			case 5:
				updateMember2();
				break;
			case 0:
				System.out.println("작업을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
	}

	private void updateMember2() {
		System.out.println();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원 ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count == 0){
			System.out.println(memId + " 는 등록되지 않은 아이디입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		System.out.println();
		System.out.println("수정할 항목을 고르세요");
		System.out.println("1. 이름   2. 전화번호   3. 주소");
		int input = scan.nextInt();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "";
			String para = "";
			scan.nextLine();
			switch(input){
			case 1:
				sql = "update mymember set mem_name = ? where mem_id = ?";
				System.out.println("수정할 이름 입력");
				break;
			case 2:
				sql = "update mymember set mem_tel = ? where mem_id = ?";
				System.out.println("수정할 번호 입력");
				break;
			case 3:
				sql = "update mymember set mem_addr = ? where mem_id = ?";
				System.out.println("수정할 주소 입력");
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				return;
			}
			
			para = scan.nextLine();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, para);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			switch(input){
			case 1:
				System.out.print("회원 이름");
				break;
			case 2:
				System.out.print("회원 전화번호");
				break;
			case 3:
				System.out.print("회원 주소");
				break;
			}
			if(cnt > 0){
				System.out.println(" 정보 수정 성공!");
			}else{
				System.out.println(" 정보 수정 실패..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}
	
	
	private void printAll() {
		try {
			System.out.println();
			System.out.println("-- 전체 자료 출력 --");
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println("MEM_ID : " + rs.getString("mem_id"));
				System.out.println("MEM_NAME : " + rs.getString("mem_name"));
				System.out.println("MEM_TEL : " + rs.getString("mem_tel"));
				System.out.println("MEM_ADDR : " + rs.getString("mem_addr"));
				System.out.println("-----------------------------");
			}
			System.out.println("출력 끝!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		
	}

	private void update() {
		try {
			System.out.print("수정하고 싶은 아이디 입력 >>");
			String id = scan.next();
			
			String sql = "select count(*) from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int count = 0;
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count(*)");
			}
			if(count == 1){
				System.out.println("수정할 이름 입력 >>");
				String name = scan.next();
				
				System.out.println("수정할 전화번호 입력 >>");
				String tel = scan.next();
				
				System.out.println("수정할 주소 입력 >>");
				String add = scan.next();
				
				String sql2 = "update mymember set mem_name = ?, mem_tel = ?, mem_addr = ?"
						+ " where mem_id = ? ";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, name);
				pstmt.setString(2, tel);
				pstmt.setString(3, add);
				pstmt.setString(4, id);
				
				int cnt = pstmt.executeUpdate();
				if(cnt > 0){
					System.out.println("수정 성공");
				}else{
					System.out.println("수정 실패");
				}
				
			}else{
				System.out.println("존재하지 않는 아이디입니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			// 5. 자원 반납하기
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		
	}

	private void delete() {
		
		try {
			System.out.print("삭제할 아이디 입력 >>");
			String id = scan.next();
			
			String sql = "select count(*) from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int count = 0;
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt("count(*)");
			}
			if(count == 1){
				String sql2 = "delete mymember where mem_id = ?";
				
				System.out.println("정말 삭제하시겠습니까? (y/n)");
				String input = scan.next();
				if(input.equals("y") || input.equals("Y")){
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, id);
					
					int cnt = pstmt.executeUpdate();
					if(cnt > 0){
						System.out.println("삭제 성공");
					}else{
						System.out.println("삭제 실패");
					}
				}
			}else{
				System.out.println("존재하지 않는 아이디입니다.");
			}
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally{
			// 5. 자원 반납하기
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		
	}

	private void insert() {
		
		int count = 0;
		String id = "";
		try {
			do{
				System.out.print("아이디 입력 >> ");
				id = scan.next();
				
				String sql = "select count(*) from mymember where mem_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					count = rs.getInt("count(*)");
				}
				if(count == 1){
					System.out.println("이미 있는 아이디입니다.");
					System.out.println("다시 입력하세요");
				}
			}while(count == 1);
		System.out.print("이름 입력 >>");
		String name = scan.next();
		
		System.out.print("전화번호 입력 >>");
		String tel = scan.next();
		
		System.out.print("주소 입력 >>");
		String add = scan.next();
		
		String sql = "insert into mymember(mem_id, mem_name, mem_tel, mem_addr) "
				+ "values (?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, tel);
		pstmt.setString(4, add);
		
		int cnt = pstmt.executeUpdate();
		if(cnt > 0){
			System.out.println("등록 성공!");
		}else{
			System.out.println("등록 실패!");
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

	private int displayMenu() {
		conn = DBUtil.getConnection();
		System.out.println();
		System.out.println("   1. 자료 추가");
		System.out.println("   2. 자료 삭제");
		System.out.println("   3. 자료 수정");
		System.out.println("   4. 전체 자료 출력");
		System.out.println("   5. 자료 수정");
		System.out.println("   0. 작업 끝");
		System.out.print("작업 선택 >>");
		int num = scan.nextInt();
		return num;
	}

	private int getMemberCount(String memId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;		// 회원 ID개수가 저장될 변수
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		return count;
	}
	
	
}
