package kr.or.ddit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.MemberVO;
import kr.or.ddit.service.IServiceSample;
import kr.or.ddit.service.ServiceImpl;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 클라이언트 요청시 전송되는 데이터 받기
		String aa = request.getParameter("id");
		
		// crud처리 준비
		// 2. Service 객체를 생성(얻어오기)  new 명령어를 사용한 선언이 아니다!
		IServiceSample service = ServiceImpl.getService();
		
		
		// 3. Service 메소드 호출 - 결과 값을 리턴 받아 가져온다.
		List<MemberVO> list = service.getMemberAll();
		
		
		// 4. list결과 값을 request객체에 저장한다.
		request.setAttribute("listvalue", list);
		
		
		// 5. 수행된 결과값을 view 페이지로 보낸다. - forward 방식(HttpServletRequest request)
		RequestDispatcher disp =  request.getRequestDispatcher("0205/view.jsp");
		
		disp.forward(request, response);
		
		
		
	}

}
