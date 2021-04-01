package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest2 {

	public static void main(String[] args) throws IOException {
		// URLConnection 클래스
		//			==> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		
		// 특정 서버의 정보와 파일 내용을 가져와 출력
		URL url = new URL("https://www.naver.com/");
		
		// 주어진 URL주소에 접속하기 위해 URLConnection객체를 구한다.
		URLConnection urlCon = url.openConnection();
		
		// Header 정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// headerMp의 key값과 value값 출력하기
		for(String headerKey : headerMap.keySet()){
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		System.out.println();
		
		// URL주소에 지정한 해당 문서의 내용 가져오기
		// (index.html문서의 내용을 가져온다.)
		
		// 방법1) ==> URLConnection객체를 이용하는 방법
		/*
		// 파일 내용을 읽어오기 위한 스트림 객체 생성
		InputStream is = urlCon.getInputStream();							// 바이트기반
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");			// 문자기반으로 바꿔줌
		BufferedReader br = new BufferedReader(isr);						// 위에서 문자기반으로 받아왔기 때문에 버퍼도 동일 (버퍼는 안써도 되지만 쓰는게 더 좋음)
		
		// 자료를 읽어와 화면에 출력하기
		while(true){
			String str = br.readLine();				// 한 줄씩 읽어온다.
			if(str == null) break;
			System.out.println(str);
		}
		
		br.close();*/
		
		
		// 방법2) ==> URL객체의 openStream()메서드 이용하기
		InputStream is2 = url.openStream();							// 바이트기반
		InputStreamReader isr2 = new InputStreamReader(is2, "UTF-8");			// 문자기반으로 바꿔줌
		BufferedReader br2 = new BufferedReader(isr2);						// 위에서 문자기반으로 받아왔기 때문에 버퍼도 동일 (버퍼는 안써도 되지만 쓰는게 더 좋음)
		
		// 자료를 읽어와 화면에 출력하기
		while(true){
			String str = br2.readLine();				// 한 줄씩 읽어온다.
			if(str == null) break;
			System.out.println(str);
		}
		
		br2.close();

	}

}
