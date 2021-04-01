package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTestSem {

	public static void main(String[] args) {
		
		File file = new File("d:/D_Other/코알라.jpg");
		
		// 원본 파일이 있는지 없는지 검사
		if(!file.exists()){
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
		}
		
		// 바이트기반의 스트림을 이용해서 읽어오고 복사함 > 시간이 좀 오래걸림 > 파일이 크면 더 오래 걸림
		// 기능을 향상시키기 위해서 buffer를 이용할 수 있다.
		
		try {
			// 복사할 원본 파일의 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fin);	// 이렇게 buffer스트림 추가해준다 - 크기 따로 지정X 8kb
			
			// 저장될 파일 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/D_Other/연습용/복사본2-코알라.jpg");
			BufferedOutputStream bout = new BufferedOutputStream(fout);	// 이것도 이렇게 buffer스트림 추가
			
			System.out.println("복사 시작...");
			
			int data; // 읽어온 데이터가 저장될 변수
			
//			while((data = fin.read()) != -1){
//				fout.write(data);
//			}
//			
//			// 사용한 스트림 닫기
//			fout.close();
//			fin.close();
			
			// 버퍼 스트림 이용하기
			while((data = bis.read()) != -1){
				bout.write(data);
			}
			// buffer를 이용할 때는 출력 다 하고 나서 flush하기 (지금은 바로 닫았기 때문에 큰 문제는 없지만 출력한 다음에 flush해주는 버릇을 들이는 것이 좋다)
			bout.flush();
			
			// 사용한 스트림 닫기 - 보조 스트림만 닫으면 된다.
			bis.close();
			bout.close();
			
			System.out.println("복사 완료");
			
			// buffer로 하니까 시작과 동시에 완료가 됨 (속도가 훨씬 빨라진다)
			// buffer는 파일 용량이 큰 것을 처리할 때 처리 속도를 높이기 위해서 사용한다.
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	
	

}
