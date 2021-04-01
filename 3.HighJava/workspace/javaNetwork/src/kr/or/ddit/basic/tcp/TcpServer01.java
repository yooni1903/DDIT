package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {
		// TCP소켓 통신을 위한 ServerSocket객체를 생성한다.
				ServerSocket server = new ServerSocket(7777);
				System.out.println("서버가 접속을 기다립니다.");
				
				// accept()메서드
				// 	==> 클라이언트에서 연결 요청이 올 때까지 계속 기다린다.
				// 	==> 연결 요청이 오면 Socket객체를 생성해서 클라이언트의 Socket과 연결한다.
				Socket socket = server.accept();
				
				// 이 부분을 accept()에 의해서 클라이언트와 연결된 후에 처리할 내용을 기술한다.
				System.out.println("클라이언트와 연결되었습니다.");
				System.out.println();
				
				
				System.out.println("접속한 클라이언트 정보");
				System.out.println("IP 주소 : " + socket.getInetAddress().getHostAddress());	// 상대편(클라이언트) 아이피 정보
				System.out.println("Port번호 : " + socket.getPort());
				System.out.println();
				
				System.out.println("서버의 정보");
				System.out.println("IP 주소 : " + socket.getLocalAddress());
				System.out.println("Port번호 : " + socket.getLocalPort());
				System.out.println();
				
				
				// 클라이언트에게 메세지 보내기
				// 	==> Socket의 OutputStream 객체를 이용하여 전송한다.
				// 	==> Socket의 getOutputSTream() 메서드를 이용한다.
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new DataOutputStream(out);
				
				// 클라이언트에게 메세지 전송하기
				// 	위에서 구한 OutputStream객체의 write명령을 사용하면 된다.
				dos.writeUTF("어서오세요. 환영합니다!!!!!");
				System.out.println("메세지를 전송했습니다.");
				
				// 소켓과 스트림 닫기
				dos.close();
				socket.close();
				server.close();
				
				
	}

}
