package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.MimeType;
import kr.or.ddit.enumpkg.OperatorType;
import kr.or.ddit.servlet03.view.JsonView;
import kr.or.ddit.servlet03.view.XmlView;
import kr.or.ddit.vo.CalculateVO;

@WebServlet("/03/calculator")
public class CalculateServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		// 요청 -> 분석(검증)
		int status = bindAndValidate(req);
		if(status != HttpServletResponse.SC_OK) {
			resp.sendError(status);
			return;
		}
		// -> 연산
		CalculateVO vo = (CalculateVO) req.getAttribute("vo");
		OperatorType operator =  vo.getOperator();
		double result = operator.operate(vo.getLeft(), vo.getRight());
		String expr = operator.expression(vo);
		vo.setResult(result);
		vo.setExpression(expr);
		// request에 저장된 vo는 값이 변경된 채로 저장되었기 때문에 request는 다시 설정해줄 필요가 없다.
		
		// -> 응답 -> 전송
		String accept = req.getHeader("accept");
		MimeType mimeType = MimeType.searchMimeType(accept);
		resp.setContentType(mimeType.getMime());	// 출력 스트림 개방하기 전에 mime을 설정한다.
		
		StringBuffer respData = new StringBuffer();
		String view = null;
		
		// Model1방식 요청을 받은 애가 응답도 처리하니까 Model1방식이다.
		switch (mimeType) {
			case JSON:
				new JsonView().mergeModelAndView(vo, resp);
				break;
			case XML:
				new XmlView().mergeModelAndView(vo, resp);
				break;
			case PLAIN:
				respData.append(expr);
				break;
	
			default:
				view = "/WEB-INF/views/calculatorView.jsp";
				break;
		}
		
		if(view!=null) {
			req.getRequestDispatcher(view).forward(req, resp);
		}else {
			try (
					// 직렬화(selialize)
					PrintWriter out = resp.getWriter();
					){
				out.println(respData);
			}
		}
		
	}

	private int bindAndValidate(HttpServletRequest req) {
		// 보낸 값을 검증하는 메서드
		
		int status = HttpServletResponse.SC_OK;		//미리 ok로 생성하고 통과하지 못하는 경우를 잡아내자!
		String leftParam = req.getParameter("left");
		String rightParam = req.getParameter("right");
		String operatorParam = req.getParameter("operator");
		
		double left = -1;
		double right = -1;
		OperatorType operator = null;
		
		
		if(leftParam == null || rightParam == null || operatorParam == null) {
			status = HttpServletResponse.SC_BAD_REQUEST;
		}else {
			try {
				left = Double.parseDouble(leftParam);
				right = Double.parseDouble(rightParam);
				
				operator = OperatorType.valueOf(operatorParam);
			}catch(IllegalArgumentException e) {
				status = HttpServletResponse.SC_BAD_REQUEST;
			}
		}
		
		if(status==HttpServletResponse.SC_OK) {
			CalculateVO vo = new CalculateVO(left, right, operator);
			req.setAttribute("vo", vo);
		}
		
		return status;
	}
}
