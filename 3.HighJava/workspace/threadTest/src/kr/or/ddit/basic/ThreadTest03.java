package kr.or.ddit.basic;

public class ThreadTest03 {
	
	public static void main(String[] args) {
		// 스레드가 수행되는 시간을 체크해보자.
		Thread th = new Thread(new MyRunner());
		
		// 1970년 1월1일 0시0분0초(표준 시간)으로 부터 경과한 시간을 밀리세컨즈 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		th.start();
		
		try {
			th.join();		// 현재 실행 중인 스레드에서 대상이 되는 스레드(여기에서는 th)가 종료될 때까지 기다린다.
		} catch (Exception e) {
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + (endTime - startTime));
		
	}

}

// 1 ~ 10억까지의 합계를 구하는 스레드
class MyRunner implements Runnable{
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = 1L; i <= 1_000_000_000L; i++){
			sum += i;
		}
		System.out.println("합계 : " + sum);
		
		
	}
	
}