package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;



public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		
		// 수신용, 송신용 패킷 객체 변수 선언
		DatagramPacket inpacket, outpacket;
		
		// 수신받은 데이터가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];
		
		try {
			// 포트번호를 지정하지 않고 Socket 객체를 생성한다.
			// 서버와 달리 포트번호가 필요하지 않기 때문이다. (서버는 8888)
			DatagramSocket socket = new DatagramSocket();
			
			// 접속할 곳(서버)의 주소 생성
//			InetAddress address = InetAddress.getByName("localhost");
			InetAddress address = InetAddress.getByName("127.0.0.1");	// localhost와 같은 의미
			
			while(true){
				// 전송할 메세지 입력
				System.out.print("보낼 메세지 입력 : ");
				String msg = scan.nextLine();
				
				if("/end".equals(msg)){	// 메세지 중지 여부 검사
					break;
				}
				
				byte[] sendMsg = msg.getBytes();
				// 전송할 패킷 객체 생성
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, 8888);
				
				
				// 전송하기
				socket.send(outpacket);
				
				// --------------------------------------------------------------------
				// 서버에서 온 데이터를 받아서 출력하기
				
				// 수신용 패킷객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 수신하기
				socket.receive(inpacket);
				
				System.out.println("서버의 응답 데이터 : " + new String(bMsg, 0, inpacket.getLength()));
				System.out.println();
			}// while
			
			System.out.println("통신 끝");
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
