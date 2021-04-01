package kr.or.ddit.basic;

/*
 	1 ~ 20억까지의 합계를 구하는 프로그램을 하나의 스레드가 단독으로 처리할 때와 여러 개의 스레드가 협력해서 처리할 때의 경과시간을 비교해보자.
 */


public class ThreadTest04 {

	public static void main(String[] args) {
		// 단독으로 처리하는 스레드
		SumThread sm = new SumThread(1L, 2000000000L);
		
		// 여럿이 협력해서 처리하는 스레드
		SumThread[] sumArr = new SumThread[]{
				new SumThread(1L, 500000000L),
				new SumThread(500000001L, 1000000000L),
				new SumThread(1000000001L, 1500000000L),
				new SumThread(1500000001L, 2000000000L),
				
		};
		
		// 단독으로 처리하기
		long startTime = System.currentTimeMillis();
		
		sm.start();
		
		try {
			sm.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독 처리의 경과시간 : " + (endTime - startTime));
		System.out.println();
		
		// 여러 스레드가 협력하는 경우
		startTime = System.currentTimeMillis();
		long sum = 0L;
		for(int i = 0; i < sumArr.length; i++){
			sumArr[i].start();
		}
	
		for(SumThread smt : sumArr){
			try {
				smt.join();
				sum += smt.getSum();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		endTime = System.currentTimeMillis();
		System.out.println("협력 처리 합계 : " + sum);
		System.out.println("협력 처리의 경과시간 : " + (endTime - startTime));
	}
	

}


// 주어진 구간의 합계를 구하는 스레드
class SumThread extends Thread{
	// 합계를 구할 영역의 시작값과 끝값을 저장할 변수 선언
	private long start, end;
	private long sum;
	public SumThread(long start, long end) {
		this.start = start;
		this.end = end;
	}
	
	
	public long getSum() {
		return sum;
	}


	public void setSum(long sum) {
		this.sum = sum;
	}


	@Override
	public void run() {
		sum = 0L;
		for(long i = start; i <= end; i++){
			sum += i;
		}
		
		System.out.println("합계 : " + sum);
		
	}
	
}