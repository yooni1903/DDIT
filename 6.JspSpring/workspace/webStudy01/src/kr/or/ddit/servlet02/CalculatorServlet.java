package kr.or.ddit.servlet02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.Operator;

//@WebServlet("/03/calculator")
public class CalculatorServlet extends HttpServlet{
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			double left = Double.parseDouble(req.getParameter("left"));
			String operator = req.getParameter("operator");
			double right = Double.parseDouble(req.getParameter("right"));
			
			
			String CAL = "결과 : %f %s %f = %f";
			System.out.println(left + operator + right);
			double result = 0;
			switch(operator) {
			case "+":
				result = left + right;
				break;
			case "-":
				result = left - right;
				break;
			case "*":
				result = left * right;
				break;
			case "/":
				result = left / right;
				break;
			}
			CAL = String.format(CAL, left, operator, right, result);
			System.out.println("여기 마지막 출력" + CAL);
			
			req.setAttribute("cal", CAL);
			
			req.getRequestDispatcher("/03/calculator.jsp").forward(req, resp);
			
		}
}
