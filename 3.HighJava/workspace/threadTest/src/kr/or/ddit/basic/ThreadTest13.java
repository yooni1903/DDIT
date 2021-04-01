package kr.or.ddit.basic;

// yield() 메서드 연습

public class ThreadTest13 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 스레드");
		YieldThread th2 = new YieldThread("2번 스레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("11111111 +++++++++++++++++++++++++++++++++++++++++++++");
		th1.work = false;
		
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("22222222 +++++++++++++++++++++++++++++++++++++++++++++");
		
		th1.work = true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("33333333 +++++++++++++++++++++++++++++++++++++++++++++");
		
		th1.stop = true;
		th2.stop = true;
		
	}

}


// yield() 메서드 연습용 스레드
class YieldThread extends Thread{
	public boolean stop = false;
	public boolean work = true;
	
	public YieldThread(String name){
		super(name);		// 스레드의 이름 설정하기  		// 원래 Thread에는 스레드의 이름을 설정하는 name 변수가 있다.
		
	}
	
	@Override
	public void run() {
		while(!stop){ // stop 변수가 true이면 반복문이 끝난다.
			if(work){
				System.out.println(getName() + "-- 작업 중 --");	       // 여기서 getName이란 스레드 이름을 가져온다.
			}else{
				System.out.println(getName() + "-- 양보!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				Thread.yield();
			}
			
		}
	}
	
}