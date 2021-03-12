package kr.or.ddit.basic.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



/*
 	Servlet 3.0이상에서 파일 업로드를 하려면 @MultipartConfig 애노테이션을 설정해야한다.
 	- @MultipartConfig 애노테이션에서 설정할 변수들
 	1. fileSizeThreshold : 이 곳에 지정한 크기가 넘어가면 디스크의 임시 디렉토리에 저장한다.(단위 : byte, 기본값 : 0(즉, 무조건 임시 디렉토리를 사용한다)
 	2. maxFileSize : 1개의 파일의 최대 크기(단위 : byte, 기본값 : -1L(무제한))
 	3. maxRequestSize : 서버로 전송되는 request의 최대 크기(단위 " byte, 기본값 : -1ㅣ(무제한))
 	4. location : Part.write()메서드를 통해서 파일을 저장할 디렉토리 지정(기본값 : null)
*/
@WebServlet("/fileUploadServlet.do")
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 10, maxFileSize = 1024* 1024 * 30, maxRequestSize =1024* 1024 * 50)
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// Upload한 파일이 저장될 디렉토리 설정
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장할 디렉토리가 없으면 새로 생성한다.
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 파일이 아닌 데이터들은 getParameter()메서드나 getParameterValues()메서드를 이용하여 구한다
		// getParameterValues()메서드를 이용하여 구한다.
		String memId = request.getParameter("memid");
		System.out.println("파일이 아닌 일반 데이터 : " + memId);
		
		String fileName = ""; 	// 파일명이 저장될 변수 선언
		// 업로드한 파일의 정보가 저장될 List객체 생성
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();
	/*
		 - Servlet 3.0 이상에 새롭게 추가된 파일 Upload용 메서드
		 1. request.getParts(); ==> Part객체의 컬렉션을 반환한다.
		 2. request.getPart("part이름"); ==> 지정한 이름을 가진 개별 Part객체를 반환한다.
	*/
		
		for(Part part : request.getParts()) {
			// Part객체에서 파일명을 구해온다.
			fileName = extractFileName(part); 
			
			// 파일명이 공백("")이면 이것은 일반 파라미터라는 의미이다.
			if(!"".contentEquals(fileName)) {
				// 1개의 업로드 파일 정보를 저장할 객체 생성
				UploadDetail detail = new UploadDetail();
				detail.setFileName(fileName);
				detail.setFileSize(part.getSize());
				
				try {
					// 파일 저장하기
					part.write(uploadPath + File.separator + fileName);
					detail.setUploadStatus("Success");
				} catch (IOException e) {
					detail.setUploadStatus("Fail : " + e.getMessage());
				}
				
				fileList.add(detail);		// 파일 정보를 저장한 객체를 List에 추가
			}
			
		}
		request.setAttribute("memId", memId);
		request.setAttribute("uploadList", fileList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/basic/fileUpload/uploadFiles.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Part객체에서 읽어올 '파일명'을 찾아 반환하는 메서드
	/*
		- Part객체의 구조
		1) 파일이 아닌 경우
		----------------------111233dkfkndse		==> Part를 구분하는 구분선
		Content-Disposition : form-data : name = "age" ==> 파라미터명
							==> 빈 줄
		30					==> 파라미터 값
		
		2) 파일인 경우
		-----------------1234dkfnse					==> 구분선
		Content-Disposition : form-data : name="file1"; filename="test1.txt"			==> 파일 정보
		Content-Type: text/plain					==> 파일의 종류
							==> 빈 줄
		abcd12안녕			==> 파일의 내용
	 */
	private String extractFileName(Part part) {
		String fileName = "";		// 파일명이 저장될 변수
		
		// Content-Disposition 헤더 정보값을 가져온다.
		String contentDisposition = part.getHeader("Content-Disposition");
		String[] items = contentDisposition.split(";");		// 파일인 경우에만 name 뒤에 ;이 붙기 때문이다
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
			}
		}
		
		
		return fileName;
	}
}

