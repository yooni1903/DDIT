//d:\contents\coffee1.jpg
//이 경로가 반대편에 있는 client가 접근할 수 있는 곳이 어디까지인지 파악해야한다.

package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.net.URLEncoder;

@WebServlet("/01/image.do")
public class ImageServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req,
                     HttpServletResponse resp)
              throws ServletException,
                     IOException{
		
		String imageFilename = req.getParameter("image");
		if(imageFilename == null || imageFilename.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST); // Client가 파라미터를 넘기지 않았을 경우에 
																// 잘못된 요청이라는 상태 코드를 보내주고 리턴한다.
			return;
		}
					 
		String folder = "d:/contents";
		File imageFile = new File(folder, imageFilename);
		if(!imageFile.exists()) {	// folder 경로에 imageFile이 존재하지 않을 때
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);  // 요청한 파일이 존재하지 않았을 때의 상태코드를 보내주고 리턴한다.
			return;
		}
		
		// --------------------- 상태 코드가 400번대라면 Client쪽의 잘못이 대부분, 500번대라면 개발자의 잘못이 대부분이다.
		
		
		String mime = getServletContext().getMimeType(imageFilename);
		if(mime == null || !mime.startsWith("image/")) {	// 모든 확장자를 구분할 수 있는 것이 아니기 때문에 null도 함께 겁사해준다.
			resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}

		
		
		
		resp.setContentType(mime);
					 
		try(
			FileInputStream fis = new FileInputStream(imageFile);
			OutputStream os = resp.getOutputStream();
		){
			byte[] buffer = new byte[1024];	// 
			int pointer = -1;
			while((pointer = fis.read(buffer)) != -1){
				os.write(buffer, 0, pointer);		// 데이터를 쓸 때에는 항상 0번째부터 읽어야하며, pointer가 읽어온만큼까지만 써야한다.
			}	
		}
		
	
	}
	
}
