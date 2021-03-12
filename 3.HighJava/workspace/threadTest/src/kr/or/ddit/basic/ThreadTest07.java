package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 	문제) 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 		  컴퓨터의 가위바위보는 난수를 이용해서 정하고,
 		  사용자는 showinputDialog() 메서드를 이용해서 입력받는다.
 		  
 		  입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 		  5초 안에 입력이 없으면 게임에 진것으로 처리한다.
 		  
 		  5초 안에 입력이 완료되면 승패를 구해서 출력한다.
 		  
 		  
 		  1) 5초 안에 입력을 못햇을 경우 실행 예시
 		  	-- 결과 --
 		  	시간 초과로 당신이 졌습니다.
 		  	
 		  2) 5초 안에 입력을 했을 경우 실행 예시
 		  	-- 결과 --
 		  	컴퓨터 : 가위
 		  	사용자 : 바위
 		  	결  과 : 당신이 이겼습니다.
 		  
 */

public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new Input();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
		
	}
	
}

// 데이터를 입력받는 스레드
class Input extends Thread{
	
	String[] computerList = {"가위", "바위", "보"};
	String computer = computerList[(int)(Math.random() * computerList.length)];
	
	public static boolean inputCheck = false;
	
	@Override
	public void run() {
		String rock = JOptionPane.showInputDialog("가위바위보!");
		
		inputCheck = true;
		
		System.out.println("컴퓨터 : " + computer);
		System.out.println("사용자 : " + rock);
		if(computer.equals(rock)){
			System.out.println("결  과  : 비겼습니다."); 
		}
		
		if(!(rock.equals("가위") || rock.equals("바위") || rock.equals("보"))){
			System.out.println("가위, 바위, 보 중에 입력해주십시오.");
			System.exit(0);
		}
		// switch에 rock이었다면 default로 if문을 하나 생략할 수 있었겠다..!
		while(inputCheck){
			switch(computer){
			case "가위":
				if(rock.equals("보")) System.out.println("결  과 : 당신이 졌습니다.");
				if(rock.equals("바위"))System.out.println("결  과 : 당신이 이겼습니다!!");
				return;
			case "바위":
				if(rock.equals("가위")) System.out.println("결  과 : 당신이 졌습니다.");
				if(rock.equals("보"))System.out.println("결  과 : 당신이 이겼습니다!!");
				return;
			case "보":
				if(rock.equals("바위"))System.out.println("결  과 : 당신이 졌습니다.");
				if(rock.equals("가위"))System.out.println("결  과 : 당신이 이겼습니다!!");
				return;
			
			}
		}
		
	}
	
}


// 카운트 다운을 처리하는 스레드
class CountDown extends Thread{
	
	@Override
	public void run() {
		
		for(int i = 5; i > 0; i--){
			if(Input.inputCheck == true){
				return;
			}
			
			System.out.println(i + "초");
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
			
		}
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
		
	}
	
}