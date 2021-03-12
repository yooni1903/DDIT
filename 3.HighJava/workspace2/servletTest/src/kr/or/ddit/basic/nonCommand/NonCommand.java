package kr.or.ddit.basic.nonCommand;

// 호출자 역할의 클래스
public class NonCommand {
	/*
	// 램프 켜기
	private Lamp lamp;
	
	public NonCommand(Lamp lamp) {
		this.lamp = lamp;
	}
	
	public void run() {
		lamp.turnOn();
	}
	*/
	
	// 램프를 켜는 기능의 호출자를 히터를 켜는 호출자로 변경
	private Heater heater;
	
	public NonCommand(Heater heater) {
		this.heater = heater;
	}
	
	public void run() {
		heater.powerOn();
	}
}
