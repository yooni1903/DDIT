package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 현재 자신의 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 192.168.0.117 (예)
		// 2) 지정된 IP주소 : 127.0.0.1 (어디서든지 자기 자신의 컴퓨터를 가리키는 고유 주소)
		// 3) 원래의 컴퓨터 이름 : SEM-PC (예)
		// 4) 지정된 컴퓨터 이름 : localhost
		
		String serverIp = "localhost";
		System.out.println(serverIp + " 서버에 접속 중입니다");
		
		
		// 서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
		// Socket객체가 생성이 완료되면 해당 서버로 요청 신호를 보내고
		// 서버의 Socket객체와 연결이 완료된다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 이 부분의 코드는 서버와 연결된 후에 처리할 내용을 기술한다.
		System.out.println("서버에 연결되었습니다.");
		System.out.println();
		
		// 서버에서 보내온 메세지를 받아서 출력하기
		// Socket의 InputStream 객체를 이용한다.
		
		// InputStream객체 생성
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		// 서버가 보낸 자료를 받아서 출력한다.
		System.out.println("서버에서 보낸 메세지 : " + dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다.");
		
		// 소켓과 스트림 닫기
		dis.close();
		socket.close();
		
		
		
	}

}
