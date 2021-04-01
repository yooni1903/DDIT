package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 접속한 클라이언트 정보를 저장할 Map객체 변수 선언
	// 	==> key값 : 접속한 사람의 이름, value값 : 접속한 Socket객체
	private Map<String, Socket> clientMap;
	
	// 생성자
	public TcpMultiChatServer(){
		clientMap = Collections.synchronizedMap(
				new HashMap<String, Socket>());
	}
	
	// 서버 프로그램의 시작 메서드
	public void serverStart(){
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			System.out.println();
			
			while(true){
				socket = server.accept();		// 클라이언트의 접속을 기다린다.
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속했습니다.");
				
				// 데이터를 받아서 전체에게 전송하는 스레드 작동
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(server != null) try{ server.close(); }catch(IOException e){}
		}
			
	}
	
	// clientMap에 저장된 모든 사용자에게 메세지를 전송하는 메서드
	private void sendToAll(String msg){
		// clientMap의 데이터 갯수만큼 반복
		for(String name : clientMap.keySet()){
			try {
				DataOutputStream dos = new DataOutputStream(clientMap.get(name).getOutputStream());
				dos.writeUTF(msg);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	// ----------------------------------------------------------------------------------------
	// 서버에서 클라이언트로 메세지를 전송하는 Thread를 inner Class로 작성한다.
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		
		// 생성자
		public ServerReceiver(Socket socket){
			this.socket = socket;
			try {
				// 수신용 스트림 객체 생성
				dis = new DataInputStream(socket.getInputStream());
				
				// 송신용 스트림 객체 생성
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}	// 생성자 끝..
		
		@Override
		public void run() {
			String name = "";	// 클라이언트가 보낸 이름이 저장될 변수
			try {
				while(true){
					// 클라이언트가 최초로 보낸 데이터는 사용자의 이름인데
					// 이 이름이 중복되는지 여부를 feedback으로 클라이언트에게 보낸다.
					name = dis.readUTF();		// 클라이언트가 보낸 이름 (문자열로 보낼 거니까 readUTF)
					
					if(clientMap.containsKey(name)){	// 이름이 중복되면
						dos.writeUTF("이름중복");		// '이름중복' 메세지 전송
					}else{								// 이름이 중복되지 않을 때
						dos.writeUTF("OK");
						break;							// 반복문 탈출
					}
				}	// while문
				
				// 대화명(이름)을 받아서 전체 클라이언트들에게 대화방 참여한 사람이 있다는 메세지를 전송한다.
				sendToAll("[" + name + "]님이 들어오셨습니다.");
				
				// 대화명과 클라이언트가 연결된 Socket을 Map에 추가한다.
				clientMap.put(name, socket);
				
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
				
				// 하나의 클라이언트가 보내온 메세지를 받아서 전체 클라이언트들에게 보낸다.
				while(dis != null){
					sendToAll(dis.readUTF());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				// 이 finally 절이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미가 된다.
				sendToAll("[" + name + "]님이 퇴장하셨습니다.");
				
				// 사용자 목록(Map)에서 삭제한다.
				clientMap.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "]에서 접속을 종료했습니다.");
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
			}
			
			
		}
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TcpMultiChatServer().serverStart();
	}

}
