package kr.or.ddit.basic;

public class ThreadTest02 {
	public static void main(String[] args) {
		// 멀티 스레드 프로그램
		
		// Thread를 사용하는 방법
		
		// 방법1
		// Thread 클래스를 상속한 class를 작성한 후
		// 이 class의 인스턴스를 생성한 후, 이 인스턴스의 start() 메서드를 호출한다.
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		// 방법2
		// Runnable 인터페이스를 구현한 class를 작성한 후
		// 이 class의 인스턴스를 생성한 후, 
		// 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 
		// 생성자에 '인자값'으로 넣어서 생성한다.
		// 생성된 Thread의 인스턴스의 start() 메서드를 호출한다.
		
		MyThread2 r2 = new MyThread2();
		Thread th2 = new Thread(r2);		// 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자에 '인자값'으로 넣어서 생성한다.
		th2.start();
		
		
		// 방법3 ==> 익명구현체를 이용하는 방법
		Runnable r3 = new Runnable(){	// 그냥은 인터페이스 객체를 선언할 수 없다 ==> 메서드를 구현해놓지 않았기 때문에
			@Override					// 한번만 사용할 수 있는 형태이다.
			public void run() {
				for(int i = 1; i <= 200; i++){
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};		
		Thread th3 = new Thread(r3);
		th3.start();
		
		System.out.println("main()메서드 끝..."); 	// 메인 메서드는 다른 스레드에 비해 비교적 빨리 끝난다.
		// 메인 메서드가 끝났다고 해서 이 프로세스가 끝난 것은 아니다.
		// 프로세스가 완전히 끝났다고 보는 시점은 모든 스레드가 끝났을 때이다.
		
	}
	
	
}

// 방법1 ==> Thread 클래스 상속하기
class MyThread1 extends Thread{
	@Override
	public void run() {
		// 이 run() 메서드 안에 이 Thread가 처리할 내용을 기술한다.
		for(int i = 1; i <= 200; i++){
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) ==> 주어진 '시간'동안 작업을 잠시 멈춘다.
				// '시간'의 단위는 밀리세컨즈이다. (1/1000초)  ==> 1초 == 1000
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} // 이 run()메서드의 실행이 끝나면 해당 Thread도 끝난다.
}

// 방법2 ==> Runnable 인터페이스 구현하기
class MyThread2 implements Runnable{
	@Override
	public void run() {
		for(int i = 1; i <= 200; i++){
			System.out.print("$");
			
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}