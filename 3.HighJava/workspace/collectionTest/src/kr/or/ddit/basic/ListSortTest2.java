package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(10, "성춘향", "010-1111-5555"));
		memList.add(new Member(3, "강감찬", "010-1234-1111"));
		memList.add(new Member(6, "일지매", "010-1111-4321"));
		memList.add(new Member(2, "변학도", "010-1111-1131"));
		
		System.out.println("정렬 전...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		System.out.println();
		
		Collections.sort(memList);

		System.out.println("오름차순 정렬 후...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		System.out.println();
		
		//회원 번호의 내림차순으로 정렬하기(외부 정렬 기준으로...)
		Collections.sort(memList, new Descc());
		System.out.println("내림차순 정렬 후...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		
	}

}

// Member 클래스의 회원이름의 오름차순 정렬이 되도록 하는 내부정렬 기준 구현하기
// ==> Comparable 인터페이스를 구현한다.
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	//생성자 자동으로 만드는 단축키 Alt + Shift + s -> Generate Constructor using Fields
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	//Generate Getter and Setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	//Generate toString
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	//Comparable을 사용하려면 반드시 CompareTo()를 재정의해야한다. 컴파일 오류가 나면 Ctrl + 스페이스 눌러서 생성하기
	@Override
	public int compareTo(Member mem) {
		// 회원 이름의 오름차순 정렬 기준 만들기
		return this.name.compareTo(mem.getName());
		
		// 회원 이름의 내림차순 정렬 기준 만들기
//		return this.name.compareTo(mem.getName()) * -1;
		
	}
	
	
	
}

//회원 번호의 내림차순 정렬이 되도록하는 외부 정렬 기준 클래스 작성
class Descc implements Comparator<Member>{
	@Override
	public int compare(Member mem1, Member mem2) {
		
		/*if(mem1.getNum() > mem2.getNum()){
			return -1;
		}else if(mem1.getNum() < mem2.getNum()){
			return 1;
		}else{
			return 0;
		}
		*/
		
		//Wrapper클래스를 이용하는 방법1
//		return new Integer(mem1.getNum()).compareTo(mem1.getNum()) * -1;
		
		//Wrapper클래스를 이용하는 방법2
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		
	}
	
	
}