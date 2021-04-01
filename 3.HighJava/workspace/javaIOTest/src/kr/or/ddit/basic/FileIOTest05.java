package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
		// 한글이 저장된 파일 읽어오기
		
		try {
//			FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
			
			FileInputStream fis = new FileInputStream("d:/d_other/test_ansi.txt");
			
			// 기본 인코딩 방식으로 읽어온다.
//			InputStreamReader isr = new InputStreamReader(fis);
			
			// 인코딩 방식을 지정해서 읽어오기
			// 인코딩 방식 예시
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI 방식과 같다.)
			// - UTF-8 ==> 유니코드 UTF-8 인코딩 방식
			// - US-ASCII ==>> 영문 전용 인코딩 방식
			InputStreamReader isr = new InputStreamReader(fis, "ms949");	// 인코딩 방식은 대소문자를 가리지 않는다. 
			// 인코딩 방식이 맞지 않으면 원래 UTF-8이었던 파일도 깨져서 보인다.
			
			int c;
			while((c = isr.read()) != -1){
				System.out.print((char)c);
			}
			isr.close();		// 보조 스트림을 닫으면 보조 스트림에서 사용한 기반이 되는 스트림도 자동으로 닫힌다.
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
