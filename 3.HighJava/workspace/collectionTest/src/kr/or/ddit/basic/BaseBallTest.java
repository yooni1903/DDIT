package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

/*
 	문제) Set과 List를 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 		  컴퓨터의 숫자는 난수를 이용하여 구한다.
 		  (스트라이크는 S, 볼은 B로 나타낸다.)
 		  
 	
 	예시)
 		컴퓨터의 난수 ==> 9 5 7 
 		
 	실행 예시)
 		숫자입력 => 3 5 6
 				 => 1S1B
 		숫자입력 => 7 8 9 
 				 => 0S2B
 		숫자입력 => 9 7 5 
 				 => 1S 2B
 		숫자입력 => 9 5 7
 				 => 3S 0B
 		
 		축하합니다! 
 		당신은 4번째만에 맞혔군요!
 		
 	
 	
 	
 	
 	난수 생성은 Set을 이용하고 이후 비교하는 것은 List에 넣어서 활용한다.
 */


public class BaseBallTest {

	public static void main(String[] args) {
		
		HashSet<Integer> pc = new HashSet<>();
		
		while(pc.size() < 3){
			int ran = (int)(Math.random() * 9 + 1);
			pc.add(ran);
		}
		
		ArrayList<Integer> pcList = new ArrayList<>(pc);
		
		Collections.shuffle(pcList);
		
		Scanner s = new Scanner(System.in);
		int input = 0;
		int count = 0;
		int st = 0, ba =0;
		while(st < 3){
			count++;
			st = 0;
			ba = 0;
			System.out.print("숫자입력 >");
			input = s.nextInt();
			int thi = input % 10;
			input /= 10;
			int sec = input % 10;
			input /= 10;
			int fir = input % 10;
			
			if(pcList.get(0) == fir) st++;
			if(pcList.get(1) == sec) st++;
			if(pcList.get(2) == thi) st++;
			
			if(pcList.get(0) == sec || pcList.get(0) == thi) ba++;
			if(pcList.get(1) == fir || pcList.get(1) == thi) ba++;
			if(pcList.get(2) == sec || pcList.get(2) == fir) ba++;
			
			System.out.println(st + "S " + ba + "B");
			if(st >= 3){
				System.out.println("축하합니다");
				System.out.println("당신은 " + count + "번째만에 성공했습니다.");
			}
			
			
		}
		
		

	}

}
