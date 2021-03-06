package kr.or.ddit.enumpkg;

import kr.or.ddit.vo.CalculateVO;

public enum OperatorType {
	PLUS('+', new RealOperator() {
		public double operate(double left, double right) {
			return left + right;
		}
	}), 
	MINUS('-', new RealOperator() {
		public double operate(double left, double right) {
			return left - right;
		}
	}), 
	MULTIPLY('*', new RealOperator() {
		public double operate(double left, double right) {
			return left * right;
		}
	}), 
	DIBIDE('/', (left, right)->{	// lamda 표현 방법
		return left / right;
	}),
	MODULAR('%', (left, right)->{	// lamda 표현 방법
		return left % right;
	});
	
	// 이 어노테이션은 생략되어있었다.
	@FunctionalInterface
	private interface RealOperator{
		double operate(double left, double right);
	}

	private OperatorType(char sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	private RealOperator realOperator;
	
	private char sign;
	
	public char getSign() {
		return sign;
	}
	
	public double operate(double left, double right){
		return realOperator.operate(left, right);
	}
	
	private static final String EXPRPTRN = "%f %s %f = %f";
	
	public String expression(CalculateVO vo) {
		return String.format(EXPRPTRN, vo.getLeft(), sign, vo.getRight(), operate(vo.getLeft(), vo.getRight()));
	}
}
