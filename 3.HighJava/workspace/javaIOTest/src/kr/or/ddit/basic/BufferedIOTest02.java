package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// 문자 기반의 Bufferd스트림 사용하기
		try {
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");		// 실제 파일은 kr.or.ddit.basic에 만들었지만 실상은 javaIOTest에 있는 것으로 여겨진다.
			BufferedReader br = new BufferedReader(fr);
			
			String temp;
			
			// readLine(); ==> 한 줄 단위로 읽어온다.
			for(int i = 1; (temp = br.readLine()) != null; i++){
				System.out.printf("%4d : %s\n", i, temp);			// %d는 정수 %s는 문자
			}
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
