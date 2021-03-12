package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 	문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone 클래스를 작성하고, 
 		  Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 		  
 		  - 삭제, 검색기능은 '이름'을 입력받아서 처리한다.
 		  
 		  (Map의 구조는 key값으로 '이름'을 사용하고,
 		   value값으로는 Phone클래스의 인스턴스'로 한다.)
 		   
 		   아래 메뉴의 내용을 프로그램하시오.
 		   
 		   실행 예시)
 		   		--------------------------------------
 		   		 다음 메뉴를 선택하세요.
 		   		 
 		   		 1. 전화번호 등록
 		   		 2, 전화번호 수정
 		   		 3. 전화번호 삭제
 		   		 4. 전화번호 검색
 		   		 5. 전화번호 전체 출력
 		   		 6. 프로그램 종료
 		   		--------------------------------------
 		   		번호 입력 >>
 		   		
 		   		새롭게 등록할 전화번호 정보를 입력하세요.
 		   		이름  >> 홍길동
 		   		전화 번호 >> 010-1111-1111
 		   		주소 >> 대전시 중구 대흥동
 		   		
 		   		'홍길동' 전화번호 등록 완료!!
 		   		
 		   		--------------------------------------
 		   		 다음 메뉴를 선택하세요.
 		   		 
 		   		 1. 전화번호 등록
 		   		 2, 전화번호 수정
 		   		 3. 전화번호 삭제
 		   		 4. 전화번호 검색
 		   		 5. 전화번호 전체 출력
 		   		 0. 프로그램 종료
 		   		--------------------------------------
 		   		번호 입력 >>
 		   		
 		   		새롭게 등록할 전화번호 정보를 입력하세요.
 		   		이름  >> 홍길동
 		   		
 		   		'홍길동'은 이미 등록된 사랍입니다.
 		   		
 		   		--------------------------------------
 		   		 다음 메뉴를 선택하세요.
 		   		 
 		   		 1. 전화번호 등록
 		   		 2, 전화번호 수정
 		   		 3. 전화번호 삭제
 		   		 4. 전화번호 검색
 		   		 5. 전화번호 전체 출력
 		   		 0. 프로그램 종료
 		   		--------------------------------------
 		   		번호 입력 >> 5
 		   		
 		   		--------------------------------------
 		   		 번호 		이름	전화번호	주소
 		   		--------------------------------------
 		   		   1 	   홍길동 010-111-1111	대전시 중구 대흥돟
 		   		--------------------------------------
 		   		출력 완료..
 		   		
 		   		--------------------------------------
 		   		 다음 메뉴를 선택하세요.
 		   		 
 		   		 1. 전화번호 등록
 		   		 2. 전화번호 수정
 		   		 3. 전화번호 삭제
 		   		 4. 전화번호 검색
 		   		 5. 전화번호 전체 출력
 		   		 0. 프로그램 종료
 		   		--------------------------------------
 		   		번호 입력 >> 0
 		   		
 		   		프로그램을 종료합니ㅏ.
 		   		 
 */

public class PhoneBookTest {
	
	HashMap<String, Phone> phoneBookMap;

	public static void main(String[] args) {
		new PhoneBookTest().start();;

	}

	
	public void start(){
		phoneBookMap = new HashMap<>();
		while(true){
			
			System.out.println("------------------------------------------------------------");
			System.out.println("                 다음 메뉴를 선택하세요.");
			System.out.println();
			System.out.println("                 1. 전화번호 등록");
			System.out.println("                 2. 전화번호 수정");
			System.out.println("                 3. 전화번호 삭제");
			System.out.println("                 4. 전화번호 검색");
			System.out.println("                 5. 전화번호 전체 출력");
			System.out.println("                 0. 프로그램 종료");
			System.out.println("------------------------------------------------------------");
			System.out.print("번호 입력 >>");
			int input = ScanUtil.nextInt();
			
			switch(input){
			case 1:
				phoneInsert(); break;
			case 2:
				phoneUpdate(); break;
			case 3:
				phoneDelete(); break;
			case 4:
				phoneSearch(); break;
			case 5:
				phoneList(); break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}
	}



	private void phoneInsert() {
		
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		String name ="";
		do{
			System.out.print("이름  >>");
			name = ScanUtil.nextLine();
			
			if(phoneBookMap.containsKey(name)){
				System.out.println("'" + name + "'은 이미 등록된 사람입니다.");
			}
			
		}while(phoneBookMap.containsKey(name));
		System.out.print("전화번호 >>");
		String tel = ScanUtil.nextLine();
		System.out.print("주소 >>");
		String add = ScanUtil.nextLine();
		
		phoneBookMap.put(name, new Phone(name, add, tel));
		System.out.println("'" + name + "' 전화번호 등록 완료!");
		
		
	}


	private void phoneList() {
		Set<String> keyset = phoneBookMap.keySet();
		Iterator<String> it = keyset.iterator();
		int count = 1;
		System.out.println("------------------------------------------------------------");
		System.out.println(" 번호         이름        전화번호        주소");
		System.out.println("------------------------------------------------------------");
		while(it.hasNext()){
			String key = it.next();
			Phone p = phoneBookMap.get(key);
			System.out.println(count + "\t\t" + key + "\t" + p.tel + "\t" + p.add);
			count++;
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("출력 완료");
		
		
	}


	private void phoneSearch() {
		System.out.println("검색하고 싶은 사람의 이름을 입력하세요");
		String name = ScanUtil.nextLine();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println("연락처에 없는 이름입니다.");
		}else{
			Phone p = phoneBookMap.get(name);
			System.out.println("'" + name + "'으로 검색한 결과입니다.\n");
			System.out.println("------------------------------------------------------------");
			System.out.println(" 이름        전화번호        주소");
			System.out.println("------------------------------------------------------------");
			System.out.println(p.name + "      " + p.tel + "      " +p.add);
			System.out.println("------------------------------------------------------------");
			
		}
		
		
	}


	private void phoneDelete() {
		System.out.println("삭제하고 싶은 사람의 이름을 입력하세요");
		String name = ScanUtil.nextLine();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println("연락처에 없는 이름입니다.");
		}else{
			System.out.println("정말 삭제하시겠습니까? (y/n)");
			String input = ScanUtil.nextLine();
			
			if(input.equals("y") || input.equals("Y")){
				phoneBookMap.remove(name);
				System.out.println("'" + name + "'의 연락처가 삭제 되었습니다.");
			}else{
				
				System.out.println("삭제가 취소되었습니다.");
			}
			
		}
		
	}


	private void phoneUpdate() {
		System.out.println("수정하고 싶은 사람의 이름을 입력하세요");
		String name = ScanUtil.nextLine();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println("연락처에 없는 이름입니다.");
		}else{
			Phone p = phoneBookMap.get(name);
			System.out.print("수정할 번호를 입력하세요 >>");
			String tel = ScanUtil.nextLine();
			p.tel = tel;
			phoneBookMap.put(name, p);
			
			System.out.println("수정이 완료되었습니다.");
			
		}
		
	}


}
	



class Phone{
	String name;
	String add;
	String tel;
	
	public Phone(String name, String add, String tel) {
		super();
		this.name = name;
		this.add = add;
		this.tel = tel;
	}
	
	
	
}