package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class RockSem {
	
	public static boolean inputCheck = false;
	
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		// 난수를 이용해서 컴퓨터의 가위바위보 정하기
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3);	// 0~2 사이의 난수
		String com = data[index];
		
		// 사용자의 가위 바위 보 입력받기
		String man = null;		// 사용자의 가위 바위 보가 저장될 변수
		gt.start();
		do{
			man = JOptionPane.showInputDialog("가위바위보를 입력하세요");
		}while(!(man.equals("가위") || man.equals("바위") || man.equals("보")))
			;
		inputCheck = true;
		
		// 결과 판정하기
		String result = "";
		if(com.equals(man)){
			result = "비겼습니다.";
		}else if(man.equals("가위") && com.equals("보") ||
				man.equals("바위") && com.equals("가위") ||
				man.equals("보") && com.equals("바위")){
			result = "당신이 이겼습니다.";
		}else{
			result = "당신이 졌습니다.";
		}
		
		System.out.println("     --  결과  --");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + man);
		System.out.println("결  과 : " + result);
			
		
		
	}

}

class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작");
		for(int i= 5; i >0; i--){
			// 가위 바위 보를 입력받는 스레드에서 입력이 완료되면 카운트 다운을 멈춘다.
			if(RockSem.inputCheck == true){
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} // for문
		
		System.out.println("   -- 결과 --");
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);
	}
	
}