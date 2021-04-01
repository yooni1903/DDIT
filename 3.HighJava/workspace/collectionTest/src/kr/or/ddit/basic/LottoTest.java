package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class LottoTest {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true){
			System.out.println("==========================");
			System.out.println("     Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("     1. Lotto 구입");
			System.out.println("     2. 프로그램 종료");
			System.out.println("==========================");
			System.out.println("     메뉴선택 :");
			int input = s.nextInt();
			
			switch(input){
			case 1:
				purchase(); break;
			case 2:
				System.out.println("감사합니다.");
				System.exit(2);
				
			}
			
			
		}
	}
	
	
	public  static void purchase(){
		
		Scanner s = new Scanner(System.in);
		System.out.println("(1000원에 로또번호 하나입니다. 100장까지 구입 가능)");
		System.out.print("금액 입력 : ");
		int input = s.nextInt();
		while(input < 1000 || input > 100000){
			if(input < 1000){
				System.out.println("입력한 금액이 너무 적습니다. 로또번호 구입 실패!!");
				System.out.print("금액 입력 : ");
				input = s.nextInt();
			}else if(input > 100000){
				System.out.println("입력한 금액이 너무 많습니다. 로또번호 구입 실패!!");
				System.out.print("금액 입력 : ");
				input = s.nextInt();
			}
		}
		
		int num = input / 1000;
		int exchange = input % 1000;
		System.out.println("\nLotto 구입 시작");
		for(int i = 1; i <= num; i++){
			System.out.print("로또번호" + i + " ");
			lotto();
		}
		System.out.println("\n받은 금액은 " + input + "원이고 거스름돈은 " + exchange + "원 입니다.\n");
		
	}

	
	public static void lotto(){
		HashSet<Integer> lottoSet = new HashSet<>();
		
		while(lottoSet.size() < 6){
			int lottoNum = (int)(Math.random() * 45 + 1);
			lottoSet.add(lottoNum);
		}
		
		ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
		
		Collections.sort(lottoList);
		
		for(int i = 0; i < lottoList.size(); i++){
			System.out.print(lottoList.get(i));
			if(i < lottoList.size() -1){
				System.out.print(", ");
			}
		}
		System.out.println();
		
		
	}
}








