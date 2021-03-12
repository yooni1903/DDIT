package kr.or.ddit.basic.req_resp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class requestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String first = request.getParameter("first");
		
		String cal = request.getParameter("cal");
		
		String second = request.getParameter("second");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>");
		out.println("<title>Request 객체 연습</title>");
		out.println("<body>");
		
		out.println("<h2>계산 결과</h2>");
		out.println("<hr>");
		int num1 = Integer.parseInt(first);
		int num2 = Integer.parseInt(second);
		double result = 0;
		boolean calOk = true;	// 계산 성공 여부를 저장하는 변수
		
		switch(cal) {
		case "+":
			result = num1 + num2;
			break;
		case "-":
			result = num1 - num2;
			break;
		case "*":
			result = num1 * num2;
			break;
		case "/":
			if(num2 != 0) {
				result = (double)num1 / num2;
			}else {
				calOk = false;
			}
			break;
		case "%":
			if(num2 != 0) {
				result = num1 % num2;
			}else {
				calOk = false;
			}
			break;
		}
		
		
//		out.println(first + " " + cal + " " + second + " = " + result);
//		out.printf("%d %s %d = %f", num1, cal, num2, result);
		out.printf("%d %s %d = ", num1, cal, num2);
		
		// 계산 성공 여부를 확인해서 결과를 출력한다.
		if(calOk) {	// 정상적인 계산이 완료되었으면 결과 출력
			out.println(result);
		}else { // 분모가 0일 때, 나눗셈이나 나머지 연산을 했을 때
			out.println("계산 불가능");
		}
		
		out.println("</body></html>");
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
