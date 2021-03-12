package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class BaseBallTestSem {
	
	ArrayList<Integer> numList; 		//난수가 저장될 List
	ArrayList<Integer> userList;		// 사용자가 입력한 값이 저장될 List
		
	int strike, ball;
	
	Scanner scan = new Scanner(System.in);
	
	public void makeNum(){
		Set<Integer> numSet = new HashSet<>();
		
		//1~9 사이의 난수 3개 만들기
		while(numSet.size() < 3){
			numSet.add((int)(Math.random() * 9 +1));
		}
		
		// 만들어진 난수를 List에 저장하기
		numList = new ArrayList<>(numSet);
		
		// List의 데이터를 섞어준다.
		Collections.shuffle(numList);
			
	}
	
	// 사용자로부터 3개의 정수를 입력받아 List에 저장하는 메서드
	// 입력한 값을 서로 중복되지 않는다.
	public void inputNum(){
		int n1, n2, n3; //입력한 정수 값이 저장될 변수
		
		do{
			System.out.print("숫자 입력 ==>");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			
			if(n1 == n2 || n1 == n3 || n2 == n3){
				System.out.println("중복되는 숫자는 입력할 수 없습니다.");
				System.out.println("다시 입력하세요.");
			}
			
		}while(n1 == n2 || n1 == n3 || n2 == n3);
		
		// 입력한 데이터를 List에 저장한다.
		userList =  new ArrayList<>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	// 스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	public void ballCount(){
		strike = 0;
		ball = 0;		// 스트라이크와 볼의 개수 초기화
		
		for(int i = 0; i < numList.size(); i++){
			for(int j = 0; j < userList.size(); j++){
				
				if(numList.get(i) == userList.get(j)){	// 값을 비교
					if(i == j){
						strike++;
					}else{
						ball++;
					}
				}
			}
		}
		
		// 볼 카운트 결과를 출력한다.
		System.out.println(userList.get(0) + ", " +
		 userList.get(1) + ", " + userList.get(2) + " ==> " + strike + "S " + ball + "B");
		 
	}

	// 게임 진행하는 메서드
	public void gameStart(){
		System.out.println("게임 시작!");
		
		// 난수를 만들어주는 메서드 호출
		makeNum();
		
//		System.out.println("확인용 : " + numList);
		
		int cnt = 0; 	// 몇 번만에 맞혔는지 저장하는 변수
		
		do{
			cnt++;
			
			// 사용자 입력 메서드 호출
			inputNum();
			
			//볼카운트 메서드 호출
			ballCount();
		}while(strike != 3); 	// 3 스트라이크가 될 때까지 반복한다.
		System.out.println();
		System.out.println("축하합니다!");
		System.out.println("당신은 " + cnt + "번째만에 맞혔습니다.");
	}
	
	
	public static void main(String[] args) {
		BaseBallTestSem baseball = new BaseBallTestSem();
		
		baseball.gameStart();

	}

}




