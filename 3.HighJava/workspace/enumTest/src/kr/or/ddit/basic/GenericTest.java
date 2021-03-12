package kr.or.ddit.basic;


class NonGenericClass{
	private Object value;
	
	
	/*
	 Getter와 Setter를 만드는 이유
	 외부에서 값을 변경하기 위해 메서드를 호출하면 메서드를 처리하는 동안에 값을 검사할 수 있는 기회가 생긴다.
	 */
	public void setValue(Object value){
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}
}


/*
 - 제네릭 클래스를 만드는 방법
 형식)
 	class 클래스명<제네릭타입글자>{
 		제네릭타입글자 변수명;			// 변수 선언에 제네릭을 사용할 경우
 		...
 		
 		제네릭타입글자 메서드명(){		// 반환값이 있는 메서드에서 사용할 경우
 			...
 			return 값;
 		}
 		
 		void 메서드명(제네릭타입글자 변수명){	// 메서드의 매개변수에 제네릭을 사용할 경우
 			...
 		}
 	
 	}
 	
 	-- 제네릭 타입 글자로 많이 사용되는 것 --
 	T ==> Type
 	K ==> Key
 	V ==> Value
 	E ==> Element(자료구조에 들어가는 것들을 주로 나타낸다.)
 	--> 꼭 여기 있는 4개만 쓸 수 있는 것은 아니다.
 */


class MyGeneric<T>{
	private T value;
	
	public void setValue(T value){
		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
}

public class GenericTest {

	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setValue("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setValue(100);
		
		String rtnNg1 = (String)ng1.getValue();
		System.out.println("문자열 반환값 : " + rtnNg1);
		int rtnNg2 = (int)ng2.getValue();
		System.out.println("정수 반환값 : " + rtnNg2);
		System.out.println();

/*		String rtnNg3 = (String)ng2.getValue();
		System.out.println("rtnNg3" + rtnNg3);
		*/
		
		System.out.println("------------------------------------");
		System.out.println();
		
		MyGeneric<String> mg1 = new MyGeneric<>();  // ==> 컴파일 했을 때 제네틱 타입이 T였던 자리가 String으로 변환되어 처리된다.
		MyGeneric<Integer> mg2 = new MyGeneric<>(); 
		
		mg1.setValue("우리나라");
		mg2.setValue(500);
		
		String rtnMg1 = mg1.getValue();
		int rtnMg2 = mg2.getValue();
		
		System.out.println("문자열 반환값 : " + rtnMg1);
		System.out.println("정수 반환값 : " + rtnMg2);
		
	}

}
