package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com의 IP정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		// throw하면 try-catch안해도 됨
		
		System.out.println("HostName : " + naverIp.getHostName());
		System.out.println("Hostaddress : " + naverIp.getHostAddress());
		System.out.println();

		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		System.out.println("HostName : " + localIP.getHostName());
		System.out.println("HostAddress : " + localIP.getHostAddress());
		System.out.println();
		
		// IP주소가 여러 개인 호스트의 정보 가져오기
		InetAddress[] addrs = InetAddress.getAllByName("ddit.or.kr");
		for(InetAddress addrIp : addrs){
			System.out.println(addrIp.toString());
		}
		
	}

}
