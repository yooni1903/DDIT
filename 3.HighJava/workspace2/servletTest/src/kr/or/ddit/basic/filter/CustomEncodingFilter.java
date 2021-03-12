package kr.or.ddit.basic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CustomEncodingFilter implements Filter {
	private String encoding;	// 인코딩 문자가 저장될 변수
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 요청(Request)의 인코딩 설정
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
		
		// 응답(Response)의 인코딩 설정
		response.setCharacterEncoding(encoding);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		if(encoding == null) encoding = "utf-8";
	}

}
