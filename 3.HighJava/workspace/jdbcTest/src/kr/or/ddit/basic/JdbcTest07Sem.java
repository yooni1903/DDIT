package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class JdbcTest07Sem {
	private Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		new JdbcTest07Sem().memberStart();
	}

	private void memberStart() {
		while(true){
			int choice = displayMenu();
			
			switch(choice){
			case 1:				// 추가
				insertMember();
				break;
			case 2:				// 삭제
				deleteMember();
				break;
			case 3:				// 수정
				updateMember();
				break;
			case 4:				// 전체 출력
				displayMember();
				break;
			case 5:
				updateMember2();
				break;
			case 0:				// 작업 끝
				System.out.println("작업을 마칩니다.");
				return;
			default:
				System.out.println("번호를 잘못입력했습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}



	// 메뉴 출력 및 작업번호를 입력받아 반환하는 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println("   -- 작 업 선 택 --");
		System.out.println("   1. 자료 추가");
		System.out.println("   2. 자료 삭제");
		System.out.println("   3. 자료 수정");
		System.out.println("   4. 전체 자료 출력");
		System.out.println("   5. 자료 수정2");
		System.out.println("   0. 작업 끝");
		System.out.println("---------------------");
		System.out.print("작업 선택 >>");
		int num = scan.nextInt();
		return num;
	} 
	
	// 
	private void updateMember2() {
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
		
		int num;
		String updateField = null;	// 수정할 컬럼명이 저장될 변수
		String updateStr =  null;	// 수정할 컬럼의 한글명이 저장될 변수
		
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요");
			System.out.println("1. 회원 이름   2. 회원 전화번호   3. 회원 주소");
			System.out.println("---------------------------------------------------");
			System.out.print("수정항목 입력 >>");
			num = scan.nextInt();
			
			switch(num){
			case 1:
				updateField = "mem_name";
				updateStr = "회원 이름";
				break;
			case 2:
				updateField = "mem_tel";
				updateStr = "회원 전화번호";
				break;
			case 3:
				updateField = "mem_addr";
				updateStr = "회원 주소";
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력하세요.");
				
			}
			
		}while(num < 1 || num > 3);
		
		scan.nextLine(); 		// 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateStr + " >>");
		String updateData = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set " + updateField + " = ? where mem_id = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0){
				System.out.println(updateStr + "수정 성공!");
			}else{
				System.out.println(updateStr + "수정 실패...");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}

	

	private void updateMember() {
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
		
		System.out.print("세로운 회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화 번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_name = ?, mem_tel = ?, mem_addr = ?"
					+ " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0){
				System.out.println("수정 성공");
			}else{
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}

	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원 ID >>");
		String memId = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0){
				System.out.println(memId + " 회원 정보 삭제 성공!");
			}else{
				System.out.println(memId + " 회원 정보 삭제 실패...... ... ..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		
	}
	
	// 회원 정보를 추가하는 메서드
	private void insertMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int count = 0;
		String memId;
		
		do{
			System.out.print("회원ID >>");
			memId = scan.next();
			
			count = getMemberCount(memId);
			if(count > 0){
				System.out.println(memId + "은 이미 있는 아이디입니다.");
				System.out.println("다른 아이디를 입력하세요.");
			}
		}while(count > 0);
		
		System.out.print("회원 이름 >>");
		String memName = scan.next();
		
		System.out.print("전화번호 >>");
		String memTel = scan.next();
		
		scan.nextLine();
		System.out.print(" 주 소  >>");
		String memAddr = scan.nextLine();		// nextLine()이전에 다른 입력 명령이 있다면 입력버퍼를 비워줘야한다.
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr)"
					+ " values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0){
				System.out.println(memId + " 회원 추가 성공!!");
			}else{
				System.out.println(memId + " 회원 추가 실패 ... ... ..");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(pstmt != null) try{pstmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
		
	}
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayMember(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println(" ID      이름     전화번호       주소");
		System.out.println("---------------------------------------");
		
		try {
			// conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr= rs.getString("mem_addr");
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			
			System.out.println("---------------------------------------------");
			System.out.println("출력 끝");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();}catch(SQLException e){}
			if(stmt != null) try{stmt.close();}catch(SQLException e){}
			if(conn != null) try{conn.close();}catch(SQLException e){}
		}
	}
	
	// 회원 id를 인수로 받아서 해당 회원ID의 개수를 반환하는 메서드
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
