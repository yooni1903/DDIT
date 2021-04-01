package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		// ArrayList는 기본적인 사용법이 Vector와 같다.
		
		ArrayList list1 =  new ArrayList<>();
		
		// add()메서드를 이용하여 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(false);
		list1.add(123.45);
		
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1.toString());
		
		//get()메서드로 데이터를 꺼내온다.
		System.out.println("1번째 데이터 : " + list1.get(1));
		
		//데이터 끼워넣기도 같다.
		list1.add(3, "zzz");
		System.out.println("list1 => " + list1);
		
		// 데이터 변경하기
	    String temp = (String)list1.set(3, "yyy");
	    System.out.println("temp => " + temp);
	    System.out.println("list1 => " + list1);

		
		//삭제도 같다.
		list1.remove(3); //인덱스가 3번째인 값 삭제
		System.out.println("list1 => " + list1);

		list1.remove("bbb");
		System.out.println("list1 => " + list1);
		
		//제네릭을 사용할 수 있다.
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("AAAAA");
		list2.add("BBBBB");
		list2.add("CCCCC");
		list2.add("DDDDD");
		list2.add("EEEEE");
		
		for(int i = 0; i < list2.size(); i++){
			System.out.println(i + " ==> " + list2.get(i));
		}
		System.out.println("----------------------------------------------------------");
		
		for(String str : list2){
			System.out.println(str);
		}
		System.out.println("--------------------------------------------------------------");
		
		// contains(비교객체) ==> 리스트에 '비교객체'가 있으면 true, 없으면 false를 반환한다.
		System.out.println("DDDDD값 존재 여부 : " + list2.contains("DDDDD"));
		System.out.println("ZZZZZ값 존재 여부 : " + list2.contains("ZZZZZ"));
		System.out.println();
		
		//indexOf(비교객체) ==> 리스트에 '비교객체'가 있으면 '비교객체'가 위치한 index값을 반환하고
		//						'비교객체'가 없으면 -1을 반환한다.
		System.out.println("DDDDD의 위치 값 : " + list2.indexOf("DDDDD"));
		System.out.println("ZZZZZ의 위치 값 : " + list2.indexOf("ZZZZZ"));
		
		// toString() ==> 리스트 안의 데이터를 배열로 변환하여 반환한다.
		//			  ==> 기본적으로는 Object형 배열로 반환한다.
		
		// toString 
		
		Object[] strArr = list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		for(int i=0; i < strArr.length; i++){
			System.out.println(i + "===>" + strArr[i]);
		}
		System.out.println("===========================================================");
		
		String[] strArr2 = list2.toArray(new String[0]);
		for(String str : strArr2){
			System.out.println(str);
		}
	
	}

}
