package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		
		/*
		 	Map ==> key값과 value값을 한 쌍으로 관리하는 객체
		 			- key값은 중복을 허용하지 않고 순서가 없다. (Set의 특징을 갖는다.)
		 			- value값은 중복을 허용한다.
		 */
		
		HashMap<String, String> map = new HashMap<>();
		
		// 자료 추가 ==> put(key값, value값)
		
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map ==> " + map);
		
		// 자료 수정 ==> 데이터를 추가할 때 key값이 같으면 나중에 입력한 값이 저장된다.
		
		map.put("addr", "서울");
		System.out.println("map ==> " + map);
		
		// 자료 삭제 ==> remove(key값)
		//			 ==> 'key값'이 같은 자료를 찾아서 삭제한다.
		//			 ==> 반환값 : 삭제된 자료의 value값
		
//		String removeTel = map.remove("tel");
//		System.out.println("map ==> " + map);
//		System.out.println("삭제된 value값 " + removeTel);
		
		// 자료 읽기 ==> get(key값);
		//			 ==> 주어진 'key값'과 같이 저장된 value값을 반환한다.
		System.out.println("이름 : " + map.get("name"));
		System.out.println("전화번호 : " + map.get("tel"));
		
		// key값이 존재하는지 여부를 나타내는 메서드 : containsKey(key값)
		//			==> 해당 'key값'이 있으면 true, 없으면 false
		System.out.println("addr 키값의 존재 여부 : " + map.containsKey("addr"));
		System.out.println("age 키값의 존재 여부 : " + map.containsKey("age"));
		
		// Map객체에 저장된 모든 key값을 읽어와 Map의 데이터를 사용하는 방법
		
		// 방법1. keySet() 메서드 이용하기
		// 	==> Map의 저장된 모든 key값들을 읽어와 Set형으로 반환한다.
		System.out.println("여기");
		System.out.println("-----------------------------------------------");
		Set<String> keyset = map.keySet();
		Iterator<String> it = keyset.iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------------------");
		
		// 방법2. keySet() 메서드 이용하기 (향상된 for문 사용하기)
		for(String key : map.keySet()){
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------------------");
		
		
		// 방법3. Map객체의 value값만 읽어와 처리하기
		//			==> value() 메서드를 이용한다.
		for(String value : map.values()){
			System.out.println(value);
		}
		System.out.println("-----------------------------------------------");
		
		
		/* 방법4. Map객체에는 Entry라는 내부 class가 만들어져 있다.
		 		- 이 Entry 클래스는 key와 value라는 이름의 멤버 변수로 구성되어 있다.
		 		- Map 객체에서는 이 Entry클래스를 Set형식으로 저장하여 관리한다.
		 		
		  1) Map에 저장되어 있는 Entry 객체 전체를 가져와서 처리하기
		  		==> entrySet() 메서드를 이용한다.
		 */
		
		// Entry라는 내부 객체 전체 가져오기
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		while(entryIt.hasNext()){
			// Entry객체 1개 구하기
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key값 : " + entry.getKey());
			System.out.println("value값 : " + entry.getValue());
			System.out.println();
		}
		System.out.println("-----------------------------------------------");
		
		
	}

}
