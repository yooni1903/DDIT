package kr.or.ddit.enumpkg;

public enum Operator {
	
	PLUS("+"), MINUS("-"), MUL("*"), DIV("/");
	Operator(String opName){
		this.opName= opName;
	}
	private String opName;
	
	public String getOpName() {
		return this.opName;
	}
	
	public static String getOpName(String operator, float left, float rigth) {
		Operator op = null;
		for(Operator tmp : values()) {
			if(operator.equals(op)) {
				op = tmp;
				break;
			}
		}
		String name = op.getOpName();
		return name;
	}
	

}
