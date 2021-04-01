package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {
		/*
		 Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
		 
		 Map은 key값과 value값에 모든 형태의 객체를 다 사용할 수 있지만
		 Properties는 key값과 value값에 String만 사용할 수 있다.
		 
		 Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만
		 Properties는 setProperty(), getProperty()메서드를 통해서
		 데이터를 입출력한다.
		 
		 Properties는 데이터를 파일로 저장하거나 파일의 내용을 읽어올 수 있다.
		 */
		
		Properties prop = new Properties();
		
		prop.setProperty("name", "홍길동");
		prop.setProperty("age", "20");
		prop.setProperty("tel", "010-1234-0987");
		prop.setProperty("addr", "대전");
		
		String name = prop.getProperty("name");
		int age = Integer.parseInt(prop.getProperty("age"));
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + addr);
	}

}
