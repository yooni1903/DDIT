package kr.or.ddit.basic;

/*
 	collectionTest에 있는 PhoneBookTest javaIOTest에 추가 조건 구현하기

 * 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 작성하고, Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
 * 		- 삭제, 검색기능은 '이름'을 입력받아서 처리한다.
 * 		
 * 		(Map의 구조는 key값으로 '이름'을 사용하고, value값으로는 'Phone클래스의 인스턴스'로 한다.)
 * 		
 * 		- 추가 조건)
 *  	 1) '6. 전화번호 정보 저장' 메뉴를 추가하고 구현한다. (저장파일명은 'phoneData.dat'로 한다.)
 *  	 2) 프로그램이 시작할 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅하여 사용한다.
 *  	 3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가 또는 삭제되면 저장한 후 종료되도록 한다.
 *  		(저장할거냐고 물어보지 말고 그냥 무조건 저장하는 걸로)
 *  		6. 저장 누르면 저장되게 > 근데 6. 안 누르고 종료해도 자동으로 저장되고 종료되게?
 *  
 * 		아래 메뉴의 내용을 프로그램하시오.

 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestSem {

	HashMap<String, Phones> phoneBookMap;
	Scanner scan;
	String fileName = "d:/d_other/phoneData.dat";
	
	// 데이터가 변경되었는지 여부를 나타내는 변수 선언
	// 데이터가 변경되면 이 변수값이 true가 된다.
	boolean dataChange;
	
	public PhoneBookTestSem() {
		phoneBookMap = load();
		if(phoneBookMap == null){
			phoneBookMap = new HashMap<>();
		}
//		phoneBookMap = new HashMap<>();
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
			case 6:
				save();
				break;
			case 0:			// 종료
				if(dataChange == true){
					save();
				}
				System.out.println("프로그램을 종료합니다.");
				return;
			default :
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요.");
				
			}
		}
		
	}
	
	// 저장된 전화번호 정보를 읽어오는 메서드
	private HashMap<String, Phones> load(){
		// 읽어온 데이터가 저장될 변수
		HashMap<String, Phones> pMap = null;		
		
		File file = new File(fileName);
		if(!file.exists()){		// 저장된 파일이 없으면..
			return null;
		}
		
		// 저장된 객체를 읽어올 스트림 객체 변수 선언
		ObjectInputStream ois = null;
		
		try {
			// 입력용 스트림 객체 생성
			ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(file)));
			pMap = (HashMap<String, Phones>) ois.readObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		} finally{
			try { ois.close(); } catch (IOException e2) {}
		}
		return pMap;
	}
	
	private void save() {
		ObjectOutputStream oos = null;
		
		try {
			// 객체 출력용 스트림 생성
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(fileName)));
			
			// Map 객체를 파일로 저장한다.
			oos.writeObject(phoneBookMap);
			
			System.out.println("저장이 완료되었습니다.");
			
			dataChange = false;
			
			// oos.close();을 try-catch문 안에서 작성하는 방법은 좋지 않다. 예외발생으로 동작하지 않고 넘어갈 수 있기 때문
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			// 사용했던 스트림 객체 닫기
			try {oos.close();} catch (IOException e2) { e2.printStackTrace();}	// finally 안에서 close해주는 것이 가장 안전하다!
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
		
		dataChange = true;
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
		
		dataChange = true;
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
		scan.nextLine();
		System.out.print("주소 >>");
		String addr = scan.next();
		scan.nextLine();
/*		Phones p = new Phones(name, addr, tel);
		phoneBookMap.put(name, p);*/
		phoneBookMap.put(name, new Phones(name, addr, tel));
		System.out.println(name + "씨 전화번호 등록 완료!!");
		
		dataChange = true;
		
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
		System.out.println("                 6. 전화번호 정보 저장");
		System.out.println("                 0. 프로그램 종료");
		System.out.println("------------------------------------------------------------");
		System.out.print("번호 입력 >>");
		int num = scan.nextInt();
		return num;
	}

}

class Phones implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4575297723723780046L;
	
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