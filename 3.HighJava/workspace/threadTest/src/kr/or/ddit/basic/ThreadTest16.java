package kr.or.ddit.basic;

public class ThreadTest16 {

	public static void main(String[] args) {
		// 공통 객체 생성
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("Test1", sObj);
		TestThread th2 = new TestThread("Test2", sObj);
		
		th1.start();
		th2.start();
	}

}

class TestThread extends Thread{
	private ShareObject sObj;
	
	public TestThread(String name, ShareObject sObj){
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			sObj.add();
		}
	}
	
}


class ShareObject{
	private int sum = 0;
	
	// 동기화하기
	
	// 방법1 ==> 메서드 자체에 동기화 설정을 한다.
//	public synchronized void add(){
	public void add(){
		
		// 방법2 ==> 동기화 블럭으로 설정한다.
		synchronized (this) {
			int n = sum;
			n += 10;
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
			
		}
	}
}