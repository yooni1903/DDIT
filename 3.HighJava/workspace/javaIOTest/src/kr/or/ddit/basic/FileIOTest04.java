package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		try {
			// 바이트 기반의 입력용 스트림을 문자기반의 입력용 스트림으로 변환해주는 보조 스트림
			InputStreamReader isr = new InputStreamReader(System.in);		// System.in은 키보드로 하는 입력		InputStreamReader ==> 문자기반 입력으로 바꿔줌
			FileWriter fw = new FileWriter("d:/d_other/testchar.txt");
			
			System.out.println("아무 내용이나 입력하세요");
			System.out.println("(입력의 끝은 Ctrl + Z 입니다.)");
			
			int c;
			// 콘솔에서 입력할 때 입력의 끝은 (Ctrl + Z)를 누르면 된다.
			while((c = isr.read()) != -1){
				fw.write(c);		// 콘솔로 입력받은 데이터를 파일에 출력한다.
			}
			isr.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
