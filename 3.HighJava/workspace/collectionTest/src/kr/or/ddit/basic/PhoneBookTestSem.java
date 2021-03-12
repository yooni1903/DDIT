package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestSem {

	HashMap<String, Phones> phoneBookMap;
	Scanner scan;
	
	public PhoneBookTestSem() {
		phoneBookMap = new HashMap<>();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new PhoneBookTestSem().phoneBookStart();
	}

	private void phoneBookStart() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("                 전화 번호 관리 프로그램");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();
		
		while(true){
			int choice = displayMenu();
			switch(choice){
			case 1:			// 등록
				insert();
				break;
			case 2:			// 수정
				update();
				break;
			case 3:			// 삭제
				delete();
				break;
			case 4: 		// 검색
				search();
				break;
			case 5:			// 전체 출력
				displayAll();
				break;
			case 0:			// 종료
				System.out.println("프로그램을 종료합니다.");
				return;
			default :
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
				
			}
		}
		
	}
	
	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >>");
		String name = scan.next();
		
		//이미 등록된 사람인지 검사
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		Phones p = phoneBookMap.get(name);
		System.out.println("   검 색 결 과 ");
		System.out.println("==================");
		System.out.println(name + "씨 전화번호 정보");
		System.out.println("-----------------------------");
		System.out.println("이 름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주 소 : " + p.getAddr());
		System.out.println("-----------------------------");
		System.out.println();
	}

	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >>");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨 정보를 삭제했습니다.");
	}

	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >>");
		String name = scan.next();
		
		//이미 등록된 사람인지 검사
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		System.out.print("새로운 전화번호 >>");
		String newTel = scan.next();
		
		System.out.print("새로운 주소 >>");
		String newAdd = scan.next();
		
		phoneBookMap.put(name, new Phones(name, newAdd, newTel));
		System.out.println(name + " 수정 완료!");
	}

	//전체 전화번호 정보를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		Set<String> keySet = phoneBookMap.keySet();
		
		System.out.println("------------------------------------------------------------");
		System.out.println(" 번호         이름        전화번호        주소");
		System.out.println("------------------------------------------------------------");
		if(keySet.size()==0){
			System.out.println("출력할 데이터가 하나도 없습니다.");
		}else{
			int cnt = 0;
			for(String key : keySet){
				cnt++;
				Phones p = phoneBookMap.get(key);
				String tel = p.getTel();
				String add = p.getAddr();
				System.out.println(cnt + "\t" + key + "\t" + tel + "\t" + add);
				
			}
		}
		System.out.println("------------------------------------------------------------");
		System.out.println("출력 끝!");
	}

	// 새로운 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 검사
		if(phoneBookMap.containsKey(name)){
			System.out.println("'" + name + "'는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		System.out.print("주소 >>");
		String addr = scan.next();
		
/*		Phones p = new Phones(name, addr, tel);
		phoneBookMap.put(name, p);*/
		phoneBookMap.put(name, new Phones(name, addr, tel));
		System.out.println(name + "씨 전화번호 등록 완료!!");
		
		/*
		 next(), nextInt(), nextDouble() 등등
		 ==> 띄어쓰기, Tab키, Enter키를 구분문자로 분리해서
		 	 분리된 자료만 읽어간다.
		 	 
		 nextLine() ==> 한 줄 단위로 입력한다.
		 				즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다.
		 				
		 Scanner객체는 입력 버퍼에 자료가 남아있으면 새로 입력받지 않고 입력 버퍼에 있는 값을 가져간다.
		 */
		
		
	}
	
	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
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
		int num = scan.nextInt();
		return num;
	}

}

class Phones{
	private String name;
	private String addr;
	private String tel;
	
	public Phones(String name, String addr, String tel) {
		super();
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
}