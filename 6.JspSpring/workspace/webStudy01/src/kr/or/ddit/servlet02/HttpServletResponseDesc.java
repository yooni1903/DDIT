package kr.or.ddit.servlet02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * HttpServletResponse : client 로 전송될 응답과 관련된 모든 정보를 가진 객체
 * 
 * Http 응답 패키징 방식
 * 	1. Response Line : 상태코드(status code), protocol
 * 		** status code : 요청 처리 결과를 의미하는 숫자
 * 			1) 1XX (Http 1.1 부터) : ing...(채팅 따위를 이용할 때에 서버와 클라이언트 사이의 통로를 끊지 않고 유지시킬 때의 상태코드이다)
 * 			2) 2XX : OK(success)
 * 			3) 3XX : 클라이언트의 추가 액션이 요구되는 상태 코드
 * 				304(Not Modified)
 * 				302/307(Moved, location 헤더와 함께 사용) - 캐시에 저장된 리소스가 변경되었을 때 발생시키는 코드 -> Redirection을 발생시킨다(추가 액션)
 * 			4) 4XX : client side Fail
 * 				404(Not Found), 400(Bad Request),
 * 				405(Method Not Allowed -> do계열의 메소드에 super()메서드가 남아있을 경우), 
 * 				415(Unsupported Media Type), 
 * 				401(UnAuthorized), 403(Forbidden)
 * 			5) 5XX : server side Fail, Internal server Error -> server에서 발생한 error이기 때문에 client에게 자세히 알려주지 않는다.
 * 																때문에 어떠한 오류가 발생해도 500으로만 나옴
 *  2. Response Header : meta data
 *  	Content-Type(mime), Content-Length(length)
 *  		response.setContentType(mime)
 *  		response.setHeader(name, value)
 *  		response.setDateHeader(name, value(long타입)) -> 날짜타입은 기본적으로 long타입이다. (milliseconds로 계산되기 때문에)
 *  		response.setIntHeader(name, value(int타입))
 *  	
 *  	1) 캐시 제어
 *  	2) auto request(polling)
 *  	3) 흐름 제어(redirect/forward[include])
 *  
 *  
 *  3. Response Body(message body, content body)
 *		response.getWriter() (-> 서버는 클라이언트를 특정할 수 없기 때문에 response를 통해서 출력 스트림을 이용해야한다), response.getOutputStream() 으로 기록
 *
 */
@WebServlet("/respDesc.do")
public class HttpServletResponseDesc extends HttpServlet{
	
}
