package kr.or.ddit.member.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	
	// Controller는 Service 객체를 사용하기 때문에
	// Service 객체가 저장될 변수가 필요하다
	private IMemberService service;
	
	private MemberController(){
		service = MemberServiceImpl.getInstance();
	}
	
	
	public static void main(String[] args) {
		new MemberController().memberStart();
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
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원 ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
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
		
		
		// 수정할 정보를 Map에 추가한다.
		// Key값 - > 회원ID(memid), 수정할 컬럼명(field), 수정할 데이터(data)
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("memid", memId);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		int cnt = service.updateMember2(paramMap);
		
		if(cnt > 0){
			System.out.println(updateStr + "수정 성공");
		}else{
			System.out.println(updateStr + "수정 실패");
		}
		
		
		
	}

	

	private void updateMember() {
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요");
		System.out.print("회원 ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
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
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.updateMember(memVo);
		if(cnt > 0){
			System.out.println("update 성공!");
		}else{
			System.out.println("update 실패ㅜㅠ");
		}
		
	}

	
	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원 ID >>");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		
		if(cnt > 0){
			System.out.println("delete 성공!");
		}else{
			System.out.println("delete 실패ㅜㅠ");
		}
	}
	
	// 회원 정보를 추가하는 메서드
	private void insertMember(){
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		int count = 0;
		String memId;
		
		do{
			System.out.print("회원ID >>");
			memId = scan.next();
			
			count = service.getMemberCount(memId);
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
		
		// Service로 보낼 MemberVO객체를 생성하고 
		// 입력한 자료를 셋팅한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		// Service의 insert하는메서드 호출하기
		int cnt = service.insertMember(memVo);
		
		if(cnt > 0){
			System.out.println("insert 성공!");
		}else{
			System.out.println("insert 실패ㅜㅠ");
		}
		
		
	}
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayMember(){
		
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println(" ID      이름          전화번호           주소");
		System.out.println("-----------------------------------------------");
		
		List<MemberVO> memList = new ArrayList<>();
		memList = service.getAllMember();
		if(memList == null || memList.size() == 0){
			System.out.println("등록된 회원이 없습니다.");
		}else{
			for(MemberVO m : memList){
				System.out.println(m.getMem_id() + "\t" + m.getMem_name() + "\t" 
						+ m.getMem_tel() + "\t" + m.getMem_addr());
			}
		}
		System.out.println("출력 끝");
		
	}
	
	
}
