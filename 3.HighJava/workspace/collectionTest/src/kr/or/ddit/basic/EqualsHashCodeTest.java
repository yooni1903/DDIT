package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Set;

public class EqualsHashCodeTest {

	public static void main(String[] args) {

		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setId(2);
//		p2.setName("일지매");
		p2.setId(1);
		p2.setName("홍길동");
		
		Person p3 = p1;		//p1이 갖고 있는 값(주소값)을 p3에 넣어라  ==> 결국 p1과 p3는 주소값이 같은 객체로 p1 == p3는 true로 반환한다.
		
		System.out.println(p1 == p2);  // 주소값을 비교한다. 값이 같다고 해서 true로 나오지 않음
		System.out.println(p1.equals(p2));
		System.out.println("--------------------");
		
		Set<Person> testSet = new HashSet<>();
		testSet.add(p1);
		testSet.add(p2);
		
		System.out.println("Set의 크기 : " + testSet.size());
		// equals를 재정의하여 p1과 p2를 비교한 반환값이 true로 나왔는데
		// Set에 넣어보니 size가 2가 나왔다. (Set의 특징 : 인덱스가 없고 중복데이터는 저장하지 않는다)
		
		System.out.println("p1의 hashcode : " + p1.hashCode());
		System.out.println("p2의 hashcode : " + p2.hashCode());
		System.out.println("p3의 hashcode : " + p3.hashCode());
		//p1의 해시코드와 p2의 해시코드가 다르기 때문에 값이 같아도 다른 것으로 인식한다.
		
		
		/*
		  - equals() 메서드 ==> 두 객체의 내용이 같은지 검사하는 메서드
		  - hashCode() 메서드 ==> 두 객체의 동일성을 검사하는 메서드
		  
		  - HashSet, HashTable, HashMap과 같은 Hash로 시작하는 컬렉션 객체들은 객체의 의미상의 동일성을 비교하기 위해서 hashCode() 메서드를 호출해서 비교한다.
		  	그러므로 객체가 같은지 여부를 결정하려면 hashCode()를 재정의 해야 한다.
		  	
		  - hashCode() 메서드에서 사용하는 '해싱 알고리즘'은 서로 다른 객체들에 대하여 같은 hashCode 값이 나타날 수 있다.
		 */
		
		
	}

}

class Person{
	private int id;
	private String name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
/*	@Override
	public boolean equals(Object obj) { // 파라미터를 Object로 받는다는 것은 모든 객체를 다 받을 수 있다는 의미이다.
		if(obj == null) return false;
		
		// 같은 유형의 클래스인지 검사
		if(this.getClass() != obj.getClass()){
			return false;
		}
		
		if(this == obj){ 	//참조값이 같은지 검사
			return true;
		}
		
		// 매개변수의 값을 현재 객체 유형으로 형변환 한다.
		Person that = (Person)obj;
		
		if(this.name == null && that.name != null){
			return false;
		}
		
		if(this.id == that.id && this.name == that.name){
			return true;
		}
		
		if(this.id == that.id && this.name.equals(that.name)){
			return true;
		}
			
		return false;
	}
	*/
	
	
	
	
}