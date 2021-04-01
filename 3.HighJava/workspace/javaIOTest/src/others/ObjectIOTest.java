package others;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {

	// 객체를 파일에 저장하는 예제
	public static void main(String[] args) {

		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 22, "대전");
		Member mem2 = new Member("홍길서", 33, "울산");
		Member mem3 = new Member("홍길남", 44, "인천");
		Member mem4 = new Member("홍길북", 55, "원주");

		try {
			// 객체를 파일에 저장하기
			FileOutputStream fout = new FileOutputStream("d:/d_other/memObj.bin");
			BufferedOutputStream bos = new BufferedOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(bos);

			// 쓰기 작업
			System.out.println("객체 저장하기 시작 ...");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			System.out.println("객체 저장하기 끝 ...");

			oos.close();

			// ==================================================================

			// 저장된 객체를 읽어와 그 내용을 화면에 출력하기

			// 입력용 스트림 객체 생성
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream("d:/d_other/memObj.bin")
					)
			);

			Object obj; // 읽어온 객체를 저장할 변수

			try {
				System.out.println("객체 읽기 작업 시작...");

				// readObject()메서드가 데이터를 끝까지 다 읽어와
				// 더 이상 읽어올 데이터가 없으면 EOFException이 발생하고
				// 읽어온 데이터를 저장할 때는 해당 클래스에 대한
				// 검사를 위해서 ClassnNotFoundException이 발생 할 수 있다.
				while ((obj = ois.readObject()) != null) {
					// 읽어온 데이터를 원래의 객체형으로 형변환 후 사용한다.
					Member mem = (Member) obj;
					System.out.println("이름 : " + mem.getName());
					System.out.println("나이 : " + mem.getAge());
					System.out.println("주소 : " + mem.getAddr());
					System.out.println("-------------------------");
				}

			} catch (EOFException e) {
				System.out.println("객체 읽기 작업 끝...");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				ois.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

class Member implements Serializable {
	
	private static final long serialVersionUID = -9034511367080987457L;
	private String name;
	
	// transient ==> 직렬화가 되지 않을 멤버 변수에 지정한다.
	// 		==> 직렬화가 되지 않은 멤버 변수는 기본값으로 저장된다.
	//				(기본값 : 참조변수(null), 숫자유형변수(-> 0), 논리형변수(-> false) )				
	private /*transient*/ int age;
	private String addr;

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAdd(String addr) {
		this.addr = addr;
	}

}