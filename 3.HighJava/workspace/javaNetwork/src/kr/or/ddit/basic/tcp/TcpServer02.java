package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) {
		// 서버소켓을 만들고, 클라이언트가 접속해 오면
		// 클라이언트와 연결된 소켓을 만들어서 메세지를 받는 스레드와
		//ㅔ세지를 보내는 스레드에 이 소켓을 넣어서 생산한 후
		// 스레드들을 작동 시킨다.
		
		try {
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("서버가 준비되었습니다,");
			Socket socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
