package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


// 클라이언트가 보낸 파일을 받아서 'd:/d_other/data'폴더에 
// 보낸 파일과 같은 이름으로 저장한다.
public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataInputStream dis;
	
	public void serverStart(){
		// 저장될 폴더정보를 이용한 File 객체 생성
		File saveDir = new File("d:/d_other/data");
		
		if(!saveDir.exists()){	// 저장할 폴더가 없으면
			saveDir.mkdirs();	// 저장할 폴더를 새로 생성한다.
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept();		// 클라이언트의 접속요청을 기다린다.
			
			System.out.println("파일 다운로드 시작");
			InputStream in = socket.getInputStream();
			
			dis = new DataInputStream(in);
			
			// 클라이언트가 첫번째로 보내온 데이터를 읽어온다. (파일명 읽기)
			String fileName = dis.readUTF();
			
			// 저장할 파일
			File saveFile = new File(saveDir, fileName);
			
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(
					new FileOutputStream(saveFile));
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 소켓으로부터 받은 데이터를 파일로 저장한다.
			while((len = bis.read(temp)) > 0){
				bos.write(temp, 0 ,len);
			}
			bos.flush();
			System.out.println("파일 다운로드 완료");
			
			
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패\n" + e.getMessage());
		}finally{
			// 스트림과 소켓 닫기
			if(bos!=null) try{ bos.close(); }catch(IOException e){}
			if(bis!=null) try{ bis.close(); }catch(IOException e){}
			if(dis!=null) try{ dis.close(); }catch(IOException e){}
			if(socket!=null) try{ socket.close(); }catch(IOException e){}
			if(server!=null) try{ server.close(); }catch(IOException e){}
		}
	}
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
		
		
	}

}


