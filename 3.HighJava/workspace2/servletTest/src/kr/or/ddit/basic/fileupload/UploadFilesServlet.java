package kr.or.ddit.basic.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadFilesServlet
 */
@WebServlet("/uploadFilesServlet.do")
public class UploadFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = "d:/d_other/uploadFiles";
		
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 파일이 저장된 폴더에서 전체 파일 목록을 구해와서 List에 담아준다.
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();
		File[] allFiles = uploadDir.listFiles();
		
		for(File file : allFiles) {
			UploadDetail detail = new UploadDetail();
			detail.setFileName(file.getName());
			detail.setFileSize(file.length());
			fileList.add(detail);
		}
		
		request.setAttribute("allFileList", fileList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/basic/fileUpload/allFiles.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
