package kr.or.ddit.basic;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
 	문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 작성한다.
 		  이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리한다.
 		 
 		  이 Student 객체는 List에 저장하여 관리한다.
 		  
 		  List에 저장된 데이터들은 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
 		  총점의 역순(내림차순)으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스를 작성하여 정렬된 결과를 출력하시오.
 		  
 		  (등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.)
 */


public class StudentTest {
	
	public void setRanking(List<Student> stuList){
		/*for(int i = 0; i < stdList.size(); i++){ 							//기준 데이터를 구하기 위한 반복문
			int rank = 1; 													//처음에는 1등으로 설정하고 시작
			for(int j = 0; j < stdList.size(); j++){ 						//비교 대상을 나타내는 반복문
				if(stdList.get(i).getSum() < stdList.get(j).getSum()){		//기준보다 큰 값을 만나면 rank값을 증가시킨다.
					rank++;
				}
			}	//for - j
			// 구해진 등수를 Student 객체의 rank변수에 저장한다.
			stdList.get(i).setRank(rank);
		}
		*/
		
		for(Student std1 : stuList){
			int rank = 1;
			
			for(Student std2 : stuList){
				if(std1.getSum() < std2.getSum()){
					rank++;
				}
			}
			std1.setRank(rank);
		}
		
	}
	

	public static void main(String[] args) {
		
		StudentTest t = new StudentTest();
		
		ArrayList<Student> stuList = new ArrayList<>();
		
		stuList.add(new Student(1001, "홍길동", 56, 75, 89));
		stuList.add(new Student(1005, "이순신", 12, 78, 59));
		stuList.add(new Student(1010, "성춘향", 78, 12, 59));
		stuList.add(new Student(1003, "강감찬", 45, 54, 23));
		stuList.add(new Student(1006, "일지매", 12, 87, 63));
		
		//등수를 정해주는 메서드 호출
		t.setRanking(stuList);
		
		Collections.sort(stuList);
		System.out.println("학번 순 오름차순");
		for(Student stu : stuList){
			System.out.println(stu);
		}
		
		
		
		Collections.sort(stuList, new sumDesc());
		
		
		System.out.println("최종");
		for(Student stu : stuList){
			System.out.println(stu);
		}
		

	}

}

class Student implements Comparable<Student>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
	}

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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor
				+ ", eng=" + eng + ", math=" + math + ", sum=" + sum
				+ ", rank=" + rank + "]";
	}
	
	//학번의 오름차순
	@Override
	public int compareTo(Student student) {
		return new Integer(this.num).compareTo(student.getNum());
	}
	
}
	//총점의 내림차순
	class sumDesc implements Comparator<Student>{
		@Override
		public int compare(Student stu1, Student stu2) {
			
			if(stu1.getSum() > stu2.getSum()){
				return -1;
			}else if(stu1.getSum() < stu2.getSum()){
				return 1;
			}else{
				
				
				/*if(stu1.getName().compareTo(stu2.getName()) < 0){
					return -1;
				}else if(stu1.getName().compareTo(stu2.getName()) > 0){
					return 1;
				}else{
					return 0;
				}*/
				return new nameDesc().compare(stu1, stu2);
			}
		}
	}
	
	//총점이 같을 경우 이름의 오름차순
	class nameDesc implements Comparator<Student>{
		@Override
		public int compare(Student stu1, Student stu2) {
			
			if(stu1.getName().compareTo(stu2.getName()) < 0){
				return -1;
			}else if(stu1.getName().compareTo(stu2.getName()) > 0){
				return 1;
			}else{
				return 0;
			}
		}
	}
	
	
	//선생님이 하신 외부정렬
	class semDesc implements Comparator<Student>{
		@Override
		public int compare(Student stu1, Student stu2) {
			
			if(stu1.getSum() == stu2.getSum()){
				return stu1.getName().compareTo(stu2.getName());
			}else if(stu1.getSum() > stu2.getSum()){
				return -1;
			}else{
				return 1;
			}
		}
	}
	
	
	
	