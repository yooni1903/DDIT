package kr.or.ddit.servlet01;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet : 자바를 기반으로 웹 어플리케이션을 구현하기 위한 명세 혹은 그 명세에 따른 API 모음
 * 개발 단계
 * 1. HttpServlet의 구현체로 서블릿 소스 작성.
 * 2. WEB-INF/classes(Context classpath)에 컴파일 후 클래스 파일을 배포한다. (이 위치에 .class라는 확장자명을 가진 파일이 생김)
 * 3. 컨테이너에 서블릿을 등록.
 * 		1) 2.X : web.xml을 이용하여 등록
 * 				servlet = > servlet-name, servlet-class
 * 		2) since 3.xc : CoC(Convention over Configuration) 방법을 이용하여 등록. -> 명시적으로 이름을 설정하지 않으면 자동으로 클래스 이름으로 servlet-name이 설정된다.
 * 4. 서블릿 매핑으로 웹 상의 명령(URI)을 받을 수 있도록 연결.
 * 		1) 2.x : web.xml
 * 				servlet-mapping => servlet-name, url-pattern
 * 		2) 3.x : @WebServlet(value, urlPatterns)
 * 5. 컨테이너 재구동 (ex. tomcat)
 * 
 * 	** Servlet Container의 역할 : servlet의 lifecycle 관리자
 * 	   container : 컨테이너 내부에서 관리되는 컴포넌트의 생명주기 관리자
 *     
 *     컨테이너에서 발생할 수 있는 이벤트 3개
 *      - 생성 : init
 *      - 요청 : service, doXXX
 *      - 소멸 : destroy
 *      
 *      서블릿 관리 정책
 *      1. 특별한 설정(loadOnStartup)이 없는 한 해당 서블릿에 매핑된 최초의 요청이 발생하면, 그 때 인스턴스를 생성한다.
 * 	    2. 서블릿의 초기화 단계에서 초기화 파라미터를 전달할 수 있다(init-param)
 * 			** ServletConfig : 서블릿의 메다 정보를 캡슐화한 객체
 */


//@WebServlet(value="/desc.do", loadOnStartup=1, initParams= {
//			@WebInitParam(name="paramName", value="paramValue")
//		})		// loadOnStartup => 언제 이 서블릿 객체를 생성하고 싶은지 정해주는 속성
public class DescriptionServlet extends HttpServlet{
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String param = config.getInitParameter("paramName");
		System.out.printf("%s 서블릿 초기화, 전달 파라미터 : %s", this.getClass().getName(), param);		// this.getClass().getName() 현재 서블릿의 Qualified Name을 가져올 수 잇다.
		// 브라우저에서 해당 서블릿을 요청하여 새로고침을 눌러도 생성은 최초 1번에 한해서만 작동된다. (콘솔창으로 확인 가능)
	}
	
	
	@Override	// templete method - 틀이 있는 메서드
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("1. 요청 접수, 메소드 판단");
//		super.service(req, resp);
	}
	
	@Override 	// hook method - 호출을 받아야 작동하는 메서드
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.printf("특정 메소드(GET)를 처리할 수 있는 callback, thread name : %s\n", Thread.currentThread().getName());
	}
	
	// 소멸하는 권한은 virtual Machine이 갖고 있는데 이 때문에 소멸이 됐다는 문구가 출력이 될 수도, 안될 수도 있다는 것이다. (실행이 될 수도, 안될 수도)
	@Override
	public void destroy() {
		super.destroy();
		System.out.printf("%s 객체 소멸", DescriptionServlet.class.getName());
	}
}



















