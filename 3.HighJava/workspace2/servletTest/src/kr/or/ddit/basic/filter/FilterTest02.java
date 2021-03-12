package kr.or.ddit.basic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
	- 서블릿 필터
	1. 사용 목적
		- 클라이언트의 요청을 수행하기 전에 작업을 가로채 필요한 작업을 수행할 수 있다.
		- 클라이언트에 대한 응답 정보를 제공하기 전에 응답 정보에 필요한 작업을 수행할 수 있다.
	2. 사용 예
		- 인증 작업용 필터
		- 데이터 압축용 필터
		- 인코딩용 필터
		- 로깅 작업용 필터
		- 이미지 변환 작업용 필터 등등...
 */

// 필터 class는 javax.servlet.Filter인터페이스를 구현해서 작성해야 한다.
// 작성된 필터는 web.xml에 등록해서 작동시킨다.
public class FilterTest02 implements Filter {

	// 첫번째 순서 - 초기화 작업
	// 필터 초기화 부분 ==> 웹서버가 실행될 때 필터들을 한번만 생성하는데 그 때 자동으로 호출되는 메서드(주로 초기화 작업을 수행한다.) 
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("두번째 Filter -- init()메서드 호출...");
		String tester = config.getInitParameter("tester");
		System.out.println("초기화 파라미터 tester = " + tester);
	}
	

	// 필터에서 실제 처리할 내용을 기술하는 메서드
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// chain.doFilter() 메서드의 앞쪽에 구현하는 내용은 요청에 관련된 작업을 구현한다.
		System.out.println("두번째 Filter =  doFilter() 호출 전");
	
		chain.doFilter(request, response);		// 다음 Filter로 체이닝하는 메서드
	
		System.out.println("두번째 Filter - doFilter() 호출 후");
		// chain.doFilter()메서드의 뒤쪽에 구현하는 내용은 응답에 관련된 작업을 구현한다.
	}

	// 필터 객체가 삭제될 때 자동으로 호출되는 메서드
	// ==> 주로 자원 해제 기능을 구현한다.
	@Override
	public void destroy() {
		System.out.println("두번째 Filter -- destroy()메서드 호출...");
	}

}
