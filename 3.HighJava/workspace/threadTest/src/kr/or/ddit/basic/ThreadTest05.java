package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		// 사용자로부터 데이터 입력 받기
		String str = JOptionPane.showInputDialog("아무거나 입력하세요");			// 입력 창에서 입력할 때까지 실행은 여기에서 멈춘다.
		System.out.println("입력 값 : " + str);
		
		for(int i = 10; i > 0; i--){
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				
			}
		}
		
	}

}
