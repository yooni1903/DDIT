package kr.or.ddit.basic;

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
		Member mem2 = new Member("홍길서", 33, "대구"); 
		Member mem3 = new Member("홍길남", 44, "울산"); 
		Member mem4 = new Member("홍길북", 55, "인천"); 
		
		
		try {
			// 객체를 파일에 저장하기
			FileOutputStream fout = new FileOutputStream("d:/D_Other/memObj.bin"); // 기반이 되는 스트림 만들고
			// 보조 스트림은 여러 개를 같이 섞어서 쓸 수 있음 
			BufferedOutputStream bos = new BufferedOutputStream(fout);	// 보조 스트림에 넣고
			ObjectOutputStream oos = new ObjectOutputStream(bos);	// 그걸 또 다른 보조 스트림에 넣고
			
			// 쓰기 작업
			System.out.println("객체 저장하기 시작...");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			System.out.println("객체 저장하기 끝...");
			
			// 스트림 닫기
			oos.close();	// 보조 스트림의 보조스트림일 땐 가장 마지막에 쓴 걸 닫아주면 된다 
							// (가장 바깥쪽에 있는 스트림만 닫아주면 그 안에 있는 것들은 자동으로 닫힘)
			System.out.println();
			
			// =================================================================
			
			// 저장된 객체를 읽어와 그 내용을 화면에 출력하기
			
			// 입력용 스트림 객체 생성
			// 제일 마지막을 감당할 보조스트림을 만든다
			ObjectInputStream ois = new ObjectInputStream(
									new BufferedInputStream(
									 new FileInputStream("d:/D_Other/memObj.bin")));
			
			Object obj; // 읽어온 객체를 저장할 변수
			
			try {
				System.out.println("객체 읽기 작업 시작...");
				
				// readObject()메서드가 데이터를 끝까지 다 읽어와 더 이상 읽어올 데이터가 없으면 EOFException이 발생하고
				// 읽어온 데이터를 저장할 때는 해당 클래스가 없으면 ClassNotFoundException이 발생할 수 있다.
				while((obj = ois.readObject()) != null){ // 읽어온 데이터가 정상적이면 obj라는 변수에 저장됨
					// 읽어온 데이터를 원래의 객체형으로 형변환 후 사용한다.
					Member mem = (Member)obj;
					System.out.println("이름 : " + mem.getName());
					System.out.println("나이 : " + mem.getAge());
					System.out.println("주소 : " + mem.getAddr());
					System.out.println("======================================");
				}
			} catch (EOFException e) { // EndOfFileException (더 이상 불러올 파일 없어)
				System.out.println("객체 읽기 작업 끝....");
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			} finally{
				ois.close(); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

}

// 데이터를 저장할 목적으로 많이 쓰는 클래스 > Member객체의 데이터들을 File객체에 넣고 싶다
class Member implements Serializable{
	
	// Serializable 하니까 Member에 노란 줄 생김 > Add defualt serial versionID 누르면
//	private static final long serialVersionUID = 1L; 	// 이게 생성됨 > 이건 무조건 1
	// Add generate serial versionID 누르면 난수가 생성됨(이 값은 선생님의 값과 다를 수 있다)
	private static final long serialVersionUID = -1096438140999733430L;
	
	// transient == > 직렬화가 되지 않을 멤버 변수에 저장한다.
	// 			==> 직렬화가 되지 않은 멤버 변수는 기본값으로 저장된다.
	//				(기본값 : 참조변수(-> null), 숫자유형변수(-> 0), 논리형변수(-> false))
	private String name;	
//	private int age;	// 얘 직렬화 대상에서 빼고 싶다 > transient 붙이면 됨
	private transient int age;	
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

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}