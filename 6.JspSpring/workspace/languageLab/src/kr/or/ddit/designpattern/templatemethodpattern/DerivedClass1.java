package kr.or.ddit.designpattern.templatemethodpattern;

public class DerivedClass1 extends TemplateClass {
	
	// Template는 final이고 나머지는 private이기 때문에 메서드는 하나
	
	@Override
	protected void stepTwo() {
		System.out.println("2단계_A");
	}
}
