package kr.or.ddit.basic.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpCheckFilter implements Filter {
	private Map<String, String> ipMap;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String ip = request.getRemoteAddr(); // 접속한 IP주소
		System.out.println("접속한 IP주소 ==> " + ip);
		
		if(ip != null && ipMap.containsKey(ip)) {	// 목록에 있는지 검사
			System.out.println("허용 여부 : " + ipMap.get(ip));
			
			if("T".equals(ipMap.get(ip))) {	// 허용
				chain.doFilter(request, response);
			}else { // 불허상태
				 response.setCharacterEncoding("utf-8");
				 response.setContentType("text/html; charset=utf-8");
				 PrintWriter out = response.getWriter();
				 out.println("<h2>접근이 거부된 IP입니다.<br>");
				 out.println("관리자에게 문의하세요.</h2>");
			}
			
			
		}else {	// 목록에 없으면
			// 무조건 특정 페이지로 이동한다.
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			
			req.getRequestDispatcher("/servletTest01.do").forward(req, res);
//			res.sendRedirect(req.getContextPath() + "/servletTest01.do");	
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 허용 또는 불허되는 IP주소 정보를 구성한다.
		ipMap = new HashMap<String, String>();
		ipMap.put("127.0.0.1", "T");	// 값이 T는 허용, F는 불허로 하자  127.0.0.1은 자신의 컴퓨터
		ipMap.put("0:0:0:0:0:0:0:1", "T");
		ipMap.put("192.168.0.101", "T");
		ipMap.put("192.168.0.93", "F");
		ipMap.put("192.168.0.103", "T");
	}

}
