package others;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {

		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");

			// 자료형 단위로 출력할 보조 스트림 DataOutputStream객체 생성
			DataOutputStream dos = new DataOutputStream(fout);

			dos.writeInt(300); // 정수형으로 데이터 출력
			dos.writeFloat(123.45f); // 실수형(float)으로 출력
			dos.writeBoolean(false); // 논리형으로 데이터 출력
			dos.writeUTF("ABCDabc"); // 문자열 형식으로 데이터 출력

			System.out.println("출력 완료 ...");
			dos.close(); // 스트림 닫기

			//-------------------------------------------------------------------
			
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			DataInputStream din = new DataInputStream(fin);
			
			// DataInputStream으로 자료를 읽어올 때는
			// 출력할 때의 순서와 같은 순서로 읽어와야 한다.
			System.out.println("정수형 : " + din.readInt());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("논리형 : " + din.readBoolean());
			System.out.println("논리형 : " + din.readUTF());
			
			System.out.println();
			System.out.println("읽기 작업 완료 ...");
			din.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
