package kr.or.ddit.designpattern.templatemethodpattern;

public abstract class TemplateClass {
	// 외부에서 호출할 수 있는 메서드는 template()밖에 없다
	
	public final void template() {	// final은 오버라이딩이 불가하다.
		stepOne();
		stepTwo();
		stepThree();
	}
	
	private void stepOne() {
		System.out.println("1단계");
	}
	protected abstract void stepTwo();	// 추상 메서드는 private으로 지정할 수 없다. 
										// private은 내 자신 안에서만 접근할 수 있는데, 추상 메서드는 자식에게 상속한 후 사용되는 메서드이기 때문이다.
	
	private void stepThree() {
		System.out.println("3단계");
	}
}
