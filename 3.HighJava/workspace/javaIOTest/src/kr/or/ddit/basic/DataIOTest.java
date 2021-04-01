package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/D_Other/test.data");
			
			// 자료형 단위로 출력할 보조 스트림 DataOutputStream객체 생성
			DataOutputStream dos = new DataOutputStream(fout);
			
			// 출력하기
			dos.writeInt(200);		 // 정수형으로 데이터를 출력
			dos.writeFloat(123.45f); // 실수형(float형)으로 데이터를 출력
			dos.writeBoolean(true); // 논리형으로 데이터를 출력
			dos.writeUTF("ABCDabcd");// 문자열 형식으로 데이터 출력
			
			System.out.println("출력 완료...");
			dos.close();	// 스트림 닫기
			// 확인해보기 > 확인해보고 싶지만 메모장이나 notepad++같은 프로그램들은 기본적으로 데이터를 txt로 인식
			// 그래서 공유폴더에서 HxD프로그램 설치해서 확인함(16진수로 데이터를 읽어주는 프로그램) - 봐도 모르겠다
			
			// --------------------------------------------------------
			
			// 출력한 자료 읽어오기
			FileInputStream fin = new FileInputStream("d:/D_Other/test.data");
			DataInputStream din = new DataInputStream(fin);
			
			// DataInputStream으로 자료를 읽어올 때는 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			System.out.println("정수형 : " + din.readInt());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("논리형 : " + din.readBoolean());
			System.out.println("문자열 : " + din.readUTF());
			
			System.out.println();
			System.out.println("읽기 작업 완료...");
			din.close();	// 스트림 닫기
			
			// 출력하는 어떤 포맷이 딱 정해져 있을 때 사용
			// 숫자값들을 문자처럼 저자을 할 수 있는데 그러면 그 데이터를 읽어다가 정수형으로 변환하고 그런 작업이 필요하지만
			// 이렇게 하면 나중에 따로 형변환하지 않아도 된다 > 다만 ** 출력한 -자료형 -순서와 일치하게 읽어와야 한다 **
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}

}
