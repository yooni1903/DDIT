package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


// 외부의 properties파일의 내용을 읽어와 Properties객체로 처리하기
public class PropertiesTest {

	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일을 처리할 File객체 생성
		File file = new File("res/dbinfo.properties");
		FileInputStream fin = null;
		try {
			// 파일 내용을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			
			// 입력용 스트림 객체를 이용하여 내용을 일겅와서
			// Properties객체에 저장한다.
			prop.load(fin);		// 파일 내용을 읽어와 key 값과 value값을 분류한 후 Properties객체에 저장한다.
			
			// 읽어온 정보 출력해보기
			System.out.println("driver : " + prop.getProperty("driver"));
			System.out.println("url : " + prop.getProperty("url"));
			System.out.println("user : " + prop.getProperty("user"));
			System.out.println("pass : " + prop.getProperty("pass"));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fin!=null) try{fin.close();}catch(IOException e){}
		}
	}

}
