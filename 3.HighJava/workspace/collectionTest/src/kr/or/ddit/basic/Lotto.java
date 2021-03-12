package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Lotto {

	
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Lotto().lottoStart();
		
		

	}
	
	public void lottoStart(){
		while(true){
			int choice = displayMenu();
			
			switch(choice){
			case 1:				// 구입
				lottoBuy();
				break;
			case 2:				// 종료
				System.out.println("감사합니다.");
				System.exit(0);
			default: System.out.println("번호를 다시 선택하세요.");
			}
		}
		
	}
	
	// 로또 구입을 처리하는 메서드
	private void lottoBuy() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.println("금액 입력 : ");
		int money = scan.nextInt();
		
		if(money < 1000){
			System.out.println("입력한 금액이 너무 적습니다. 로또번호 구입 실패!!");
		}else if(money / 1000 > 100){
			System.out.println("입력한 금액이 너무 많습니다. 로또번호 구입 실패!!");
		}else{
			// 로또 번호를 생성하고 출력하는 메서드 호출
			lottoNum(money);
		}
	}

	private void lottoNum(int money){
		System.out.println();
		System.out.println("행운의 로또 번호는 아래와 같습니다.");
		for(int i = 0; i < money / 1000; i++){
			HashSet<Integer> lottoSet = new HashSet<>();
			while(lottoSet.size() < 6){
				lottoSet.add((int)(Math.random() * 45 + 1));
			}
			List<Integer> lottoList = new ArrayList<>(lottoSet);
			Collections.sort(lottoList);
			System.out.print("로또 번호" + (i+1) + " : ");
			for(int j = 0; j < lottoList.size(); j++){
				if(j > 0) System.out.print(", ");
				System.out.print(lottoList.get(j));
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("받은 금액 " + money + "원이고 거스름돈은 " + (money % 1000) + "원입니다.");
	}
	
	
	
	// 메뉴를 출력하고 선택한 메뉴 번호를 반환하는 메서드
	private int displayMenu(){
		System.out.println();
		System.out.println("==========================");
		System.out.println("     Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("     1. Lotto 구입");
		System.out.println("     2. 프로그램 종료");
		System.out.println("==========================");
		System.out.println("     메뉴선택 :");
		int num = scan.nextInt();
		
		return num;
	}
	

}
