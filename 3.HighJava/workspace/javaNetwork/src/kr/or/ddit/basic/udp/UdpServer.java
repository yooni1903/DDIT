package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
	UDP방식 : 비연결 지향, 신뢰성이 없다. 데이터가 순서대로 도착한다는 보장이 없다.
			  그렇지만 TCP방식보다 속도가 빠르다.
	DatagramSocket객체와 DatagramPacket객체를 이용하여 통신한다.
		- DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다. (우체부)
		- DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행한다.(소포)
			==> 수신을 위한 생성자와 송신을 위한 생성자를 따로 제공한다.
	
	TCP의 경우에는 스트링 객체를 이용하여 송수신하지만 UDP의 경우에는 데이터 그램 객체를 이요하여 송수신한다.
	
*/

public class UdpServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 소켓을 생성한다.(서버용)
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 패킷변수와 송신용 패킷변수 선언
			DatagramPacket inpacket, outpacket;
			
			System.out.println("서버 실행 중...");
			
			while(true){
				// 데이터가 저장될 byte형 배열을 선언한다.
				byte[] bMsg = new byte[512];	// 상대편이 보낸 메세지를 받는 역할
				
				// 수신용 패킷 객체 생성
				// 	==> 데이터가 저장될 byte형 배열, 배열의 길이를 인수값으로 주어서 생성한다.
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				// 데이터를 수신한다. ==> receive()메서드 호출
				// 이 메서드는 데이터가 올 때까지 기다린다.
				// 수신된 데이터의 패킷정보는 지정된 패킷변수(inpacket)에 저장된다.
				socket.receive(inpacket);
				
				// 수신 받은 패킷에서 상대방의 주소, 포트번호를 알 수 있다.
				InetAddress address = inpacket.getAddress();
				int port = inpacket.getPort();
				
				System.out.println("상대방의 IP 정보 : " + address);
				System.out.println("상대방의 port 정보 : " + port);
				System.out.println();
				
				
				
				// 상대방이 보낸 메세지(데이터) 출력하기
				// inpacket.getLength() ==> 실제 읽어온 데이터의 길이
				// inpacket.getData() ==> 실제 읽어온 데이터를 byte배열로 반환한다.
				//				==> 실제 읽어온 데이터는 수신용 패킷을 생성할 때 사용한 byte배열에도 저장된다.
				
				// 방법1)
				String msg = new String(inpacket.getData(), 0, inpacket.getLength());	// byte배열을 String으로 갖고오는데 0번째부터 실제 일겅온 길이만큼만 가져온다.
				// 방법2)
				String msg2 = new String(bMsg, 0, inpacket.getLength());	
				
				System.out.println("상대방이 보낸 메세지1 : " + msg);
				System.out.println("상대방이 보낸 메세지2 : " + msg2);
				System.out.println();
				
				if("/end".equals(msg)){
					break;
				}
				
				// -------------------------------------------------------------------
				// 상대방에게 메세지 보내기(수신받은 메세지를 그대로 송신)
				
				// 송신할 메세지 byte배열로 변환한다.
				byte[] sendMsg = msg.getBytes();
				
				// 수신용 패킷 객체 생성
				// 		==> 전송할 데이터가 저장된 byte배열, 전송할 자료의 길이(배열의 길이)
				// 			상대방 주소 정보, 상대방 포트 번호를 인수로 지정하여 생성한다.
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				// 송신하기 ==> send()메서드 사용
				socket.send(outpacket);
				
				System.out.println("송신 완료");
				
			}
			
			System.out.println("통신 작업 끝");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
