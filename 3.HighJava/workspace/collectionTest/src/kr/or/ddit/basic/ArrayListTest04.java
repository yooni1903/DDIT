package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

//문제 2) 문제 1에서 별명의 길이가 같은 것을 입력할 수 있을 경우를 처리하시오.

public class ArrayListTest04 {

	public static void main(String[] args) {
/*		Scanner s = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<>();
		
		for(int i = 1; i <= 5; i++){
			System.out.println(i + "번째 별명을 입력하세요 (길이가 같아도 됨)");
			String input = s.nextLine();
			list.add(input);
		}
		
		int max = 0;
		for(int i = 0; i < list.size(); i++){
			if(list.get(max).length() < list.get(i).length()){
				max = i;
			}
		}
		System.out.println();
		System.out.println("길이가 가장 긴 별명들");
		for(int i = 0; i < list.size(); i++){
			if(list.get(max).length() == list.get(i).length()){
				System.out.println(list.get(i));
				
			}
		}*/
		
		
		
		
		
		
		//-------------------------------선생님 답안 --------------------------------------------
				Scanner scan =  new Scanner(System.in);
				
				ArrayList<String> aliasList = new ArrayList<>();
				
				System.out.println("서로 다른 길이의 별명을 5번 입력하세요");
				for(int i = 1; i <=5; i++){
					System.out.println(i + "번째 별명 : ");
					String alias = scan.next();
					aliasList.add(alias);
				}
				
				// 제일 긴 별명의 길이가 저장될 변수를 선언하고 이 변수는 List의 첫번째 데이터의 길이로 초기화한다.
				int maxLength = aliasList.get(0).length();
				
				for(int i = 1; i < aliasList.size(); i++){
					if(maxLength < aliasList.get(i).length()){
						maxLength = aliasList.get(i).length();
					}
				}
				
				System.out.println("제일 긴 별명들...");
				for(int i = 0; i < aliasList.size(); i++){
					if(maxLength == aliasList.get(i).length()){
						System.out.println(aliasList.get(i));
					}
				}

	}

}
