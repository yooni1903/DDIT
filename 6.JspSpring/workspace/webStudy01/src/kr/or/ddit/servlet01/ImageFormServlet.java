package kr.or.ddit.servlet01;

import javax.servlet.http.*;

import com.sun.jndi.url.dns.dnsURLContext;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import java.io.*;
import java.util.Date;

@WebServlet("/01/imageForm.tmpl")
public class ImageFormServlet extends AbstractUseTmplServlet {

	@Override
	protected void setContentType(HttpServletResponse resp) {
		resp.setContentType("text/html;charset=UTF-8");
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		System.out.println("서블리의 요청 받앗음." + application.hashCode());

		// img파일 아닌 것은 걸러내고 리스트에 출력하기

		String folder = application.getInitParameter("contentFolder");
		File contents = new File(folder);

		String[] children = contents.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String mime = application.getMimeType(name);
				return mime != null && mime.startsWith("image/");
			}
		});

		// String이 아니라 StringBuffer를 사용하는 이유
		// String 변수는 상수이기 때문에 +=로 계속 값을 더해준다면 상수pool에 점유하는 값들이 무수히 많아진다.
		// 그렇게 되면 하나만 필요로 하는 값들이 메모리를 많이 차지하기 때문에 StringBuffer를 사용해준다.

		Date today = new Date();
		req.setAttribute("today", today);

		StringBuffer options = new StringBuffer();
		for (String child : children) {
			options.append(String.format("<option>%s</option>", child));
		}
		req.setAttribute("options", options);
	}

}
