package kr.or.ddit.basic;

/*
 	데몬 스레드 연습 ==> 자동 저장하는 스레드
 */

public class ThreadTest09 {

	public static void main(String[] args) {
		
		AutoSaveThread auto = new AutoSaveThread();
		
		// 데몬 스레드로 설정하기 ==> 반드시 start()메서드를 호출하기 전에 설정해야한다.
		auto.setDaemon(true);		// ==> 데몬스레드는 main() 스레드가 끝나면 자동으로 종료된다.
		
		auto.start();
		
		try {
			for(int i = 1; i <= 20; i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("main 스레드 종료");

	}

}


// 자동 저장하는 스레드(3초에 한번씩 자동 저장하는 스레드)
class AutoSaveThread extends Thread{
	// 작업 내용을 저장하는 메서드
	public void save(){
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			save();
			
			
		}
		
	}
}