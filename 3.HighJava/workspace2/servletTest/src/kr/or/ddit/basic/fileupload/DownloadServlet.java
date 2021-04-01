package kr.or.ddit.basic.fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadServlet.do")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int buffer_size = 1024 * 100;	// 100kB
		request.setCharacterEncoding("utf-8");
		
		String fileName = request.getParameter("filename");
		
		String downloadPath = "d:/d_other/uploadFiles";
		String filePath = downloadPath + File.separator + fileName;
		
		File file = new File(filePath);
		OutputStream outstream = null;
		FileInputStream fin = null;
		if(file.exists()) {	// Download할 파일이 있는지 검사
			// Download진행 순서
			// 1. ContentType 설정
			response.setContentType("application/octet-stream; charset=utf-8");
			
			// 2. Response객체의 헤더에 'Content-Disposition'속성을 설정한다.
			String headerKey = "Content-Disposition";
//			String headerValue = "attachment; filename=\"" + file.getName() + "\";";
			String headerValue = "attachment; filename=\"" + getDispsiotion(file.getName(), getBrowser(request)) + "\";";
			response.setHeader(headerKey, headerValue);
			
			// 3. 디스크에서 파일을 읽어서 클라이언트로 전송
			try {
				// 출력용 스트림 객체 생성
				outstream = response.getOutputStream();
				
				// 파일 입력을 스트림 객체 생성
				fin = new FileInputStream(file);
				byte[] buffer = new byte[buffer_size];
				int len = -1;
				
				// byte배열을 이용해서 파일 내용을 읽어와 출력용 스트림으로 출력한다.
				while((len=fin.read(buffer)) > 0) {
					outstream.write(buffer, 0, len);
				}
				
			} catch (IOException e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			}finally {
				outstream.flush();
				if(fin != null) {
					fin.close();
				}
				if(outstream != null) {
					outstream.close();
				}
			}
			
		}else {				// Download할 파일이 없을 경우
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println("<h3>" + fileName + "파일은 존재하지 않습니다.</h3>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");  
		if(header.indexOf("MSIE") > -1) {
			return "MSIE";		// 익스플로러
		}else if(header.indexOf("Chrome") > -1){
			return "Chrome";
		}else if(header.indexOf("Opera") > -1) {
			return "Opear";
		}else if(header.indexOf("Tridet/7.0") > -1) {
			return "MSIE";
		}
		return "FireFox";
	}
	
	
	
	// 웹 브라우저별로 파일명을 인코딩하는 메소드
	private String getDispsiotion(String filename, String browser) {
		String encodedFilename = null;		//인코딩된 파일명이 저장될 변수
		try {
			if(browser.contentEquals("MSIE")) {
				encodedFilename = URLEncoder.encode(filename, "utf-8").replaceAll("\\", "%20");
			}else if(browser.equals("Firefox")) {
				encodedFilename = "\"" + new String(filename.getBytes("utf-8"), "8859_1") +"\"";
			}else if(browser.equals("Opera")) {
				encodedFilename = "\"" + new String(filename.getBytes("utf-8"), "8859_1") +"\"";
			}else if(browser.equals("Chrome")) {
				StringBuffer sb = new StringBuffer();
				
				for(int i = 0; i < filename.length(); i++) {
					char c = filename.charAt(i);
					if(c > '~') {
						sb.append(URLEncoder.encode("" + c, "utf-8"));
					}else {
						sb.append(c);
					}
				}
				encodedFilename = sb.toString();
				
			}else {			// 기타 브라우저
				throw new RuntimeException("Not supported Browser");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encodedFilename;
	}

}
