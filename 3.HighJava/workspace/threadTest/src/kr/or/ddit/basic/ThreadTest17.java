package kr.or.ddit.basic;

/*
 	은행의 입출금을 스레드로 처리하는 예제
 	(synchronized를 이용한 동기화 처리 예제)
 */

public class ThreadTest17 {
	private int balance; 		// 잔액이 저장될 변수
	
	public synchronized int getBalance(){
		return balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money){
		balance += money;
	}
	
	// 출금하는 메서드(출금 성공 : true, 출금실패 : false 반환)
	public synchronized boolean withdraw(int money){
		if(balance >= money){
			for(int i = 1; i <= 1000000000; i++){}		// 시간 지연용
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		}else{
			return false;
		}
	}
	
	

	public static void main(String[] args) {
		// 공통으로 사용할 객체를 생성
		final ThreadTest17 account = new ThreadTest17();		// jdk 1.8 미만인 버전에서는 final을 붙여줘야하고 1.8이상에서는 final 없이 익명구현체를 사용할 수 있다.
		account.setBalance(10000);	// 잔액을 10000원으로 설정
		
		// 익명구현체로 스레드 구현
		Runnable test = new Runnable() {
			
			@Override
			public void run() {
				// 6000원 출금하기
				boolean result = account.withdraw(6000);
				System.out.println("스레드 안에서 result = " + result + ", balance = " + account.getBalance());
			}
		};
		
		//-------------------------------------------------------------------------------------------------
		Thread th1 = new Thread(test);
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
		
		

	}

}
